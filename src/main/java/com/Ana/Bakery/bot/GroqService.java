package com.Ana.Bakery.bot;


import com.Ana.Bakery.ingrediente.ingredienteRepository.IngredienteRepository;
import com.Ana.Bakery.producto.productoRepository.ProductoBaseRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Primary
@Service
public class GroqService implements LlmService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final IngredienteRepository ingredienteRepository;
    private final ProductoBaseRepository productoBaseRepository;

    @Value("${groq.api.key}")
    private String apiKey;

    @Value("${groq.model}")
    private String model;

    public GroqService(IngredienteRepository ingredienteRepository, ProductoBaseRepository productoBaseRepository) {
        this.ingredienteRepository = ingredienteRepository;
        this.productoBaseRepository = productoBaseRepository;
    }


    @Override
    public String generarRespuesta(String mensajeUsuario, List<Map<String, String>> historial) {

        if (apiKey == null || apiKey.isBlank()) {
            return "Falta configurar la API Key de Groq.";
        }
        String url = "https://api.groq.com/openai/v1/chat/completions";

        List<String> tartas = productoBaseRepository.findAll()
                .stream()
                .map(p -> p.getNombre())
                .toList();


        List<String> ingredientes = ingredienteRepository.findAll()
                .stream()
                .map(i -> i.getNombre())
                .toList();

        String catalogoTartas = String.join(", ", tartas);
        String catalogoIngredientes = String.join(", ", ingredientes);

        List<Map<String, String>> messages = new ArrayList<>();

        messages.add(Map.of(
                "role", "system",
                "content", """
                Te llamas AnaBot y eres el asistente virtual de Ana's Bakery, una tienda de tartas personalizadas.

                REGLAS OBLIGATORIAS:
                - Responde siempre en español.
                - Responde breve y claro.
                - Máximo 2 frases.
                - No inventes tartas, productos, ingredientes ni precios.
                - Si el usuario pregunta qué tartas hay disponibles, responde SOLO con las tartas de la sección TARTAS DISPONIBLES.
                - Si el usuario pregunta por ingredientes, responde SOLO con los ingredientes de la sección INGREDIENTES DISPONIBLES.
                - No confundas ingredientes con tartas.
                - Si no tienes información suficiente, dilo claramente.
                - Solo puedes hablar de Ana's Bakery, tartas, ingredientes, pedidos, precios orientativos y recomendaciones.

                TARTAS DISPONIBLES:
                """ + catalogoTartas + """

                INGREDIENTES DISPONIBLES:
                """ + catalogoIngredientes
        ));

        if (historial != null && !historial.isEmpty()) {
            List<Map<String, String>> historialLimitado = historial.stream()
                    .skip(Math.max(0, historial.size() - 6))
                    .toList();

            for (Map<String, String> msg : historialLimitado) {
                String role = msg.get("role");
                String content = msg.get("content");

                if (role != null && content != null) {
                    messages.add(Map.of(
                            "role", role,
                            "content", content
                    ));
                }
            }
        }

        messages.add(Map.of("role", "user", "content", mensajeUsuario));

        Map<String, Object> body = Map.of(
                "model", model,
                "messages", messages,
                "temperature", 0.2,
                "max_tokens", 120,
                "top_p", 0.8
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, request, Map.class);

        Map<String, Object> responseBody = responseEntity.getBody();

        if (responseBody == null || !responseBody.containsKey("choices")) {
            return "Ahora mismo no puedo generar una respuesta. Inténtalo de nuevo.";
        }

        List<?> choices = (List<?>) responseBody.get("choices");

        if (choices.isEmpty()) {
            return "No he podido generar una respuesta.";
        }

        Map<?, ?> firstChoice = (Map<?, ?>) choices.get(0);
        Map<?, ?> message = (Map<?, ?>) firstChoice.get("message");

        return message.get("content").toString().trim();
    }
}

