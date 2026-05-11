package com.Ana.Bakery.bot;


import com.Ana.Bakery.ingrediente.categoriaIngrediente.CategoriaIngrediente;
import com.Ana.Bakery.ingrediente.ingredienteModel.Ingrediente;
import com.Ana.Bakery.ingrediente.ingredienteRepository.IngredienteRepository;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;


public class OllamaLocalService implements LlmService{

    private final RestTemplate restTemplate = new RestTemplate();
    private final IngredienteRepository ingredienteRepository;

    public OllamaLocalService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public String generarRespuesta(String mensajeUsuario, List<Map<String, String>> historial) {

        String url = "http://localhost:11434/api/chat";

        List<Ingrediente> ingredientes = ingredienteRepository.findAll();
        Map<CategoriaIngrediente, List<String>> agrupados =
                ingredientes.stream()
                        .collect(Collectors.groupingBy(
                                Ingrediente::getTipo,
                                Collectors.mapping(Ingrediente::getNombre, Collectors.toList())
                        ));

        StringBuilder catalogoBuilder = new StringBuilder();

        agrupados.forEach((tipo, lista) -> {
            catalogoBuilder.append("- ").append(tipo.name()).append(": ")
                    .append(String.join(", ", lista))
                    .append("\n");
        });

        String catalogo = catalogoBuilder.toString();

        List<Map<String, String>> messages = new ArrayList<>();

        messages.add(Map.of(
                "role", "system",
                "content", """
                        Eres un asistente de una tienda de tartas.

                        REGLAS:
                        - No inventes ingredientes
                        - Responde de forma breve (máximo 15 palabras)
                        - Sugiere combinaciones del catálogo siempre
                        - SOLO puedes usar ingredientes EXACTAMENTE como aparecen en el catálogo
                        - Si el usuario pide algo fuera del catálogo, di "No disponible"
                        - NO inventes nombres ni variantes
                        - Responde usando SOLO ingredientes del catálogo en una combinación válida (1 bizcocho + 1 relleno + 1 cobertura opcional + extras opcionales)
                        - No alucines, contesta de forma simple y relacionado con la tienda de tartas y el catálogo
                        INTERPRETACIÓN:
                        - "tarta de queso" significa usar Cobertura Cheesecake
                        - "tarta de chocolate" significa usar ingredientes de chocolate
                        - "tarta de limón" significa usar ingredientes de limón

                        FORMATO:
                        - Respuesta simple

                        CATÁLOGO DISPONIBLE:
                        """ + catalogo
        ));

        if (historial != null && !historial.isEmpty()) {
            List<Map<String, String>> historialLimitado = historial.stream()
                    .skip(Math.max(0, historial.size() - 6))
                    .toList();

            for (Map<String, String> msg : historialLimitado) {
                messages.add(Map.of(
                        "role", msg.get("role"),
                        "content", msg.get("content")
                ));
            }
        }

        messages.add(Map.of("role", "user", "content", mensajeUsuario));

        Map<String, Object> body = Map.of(
                "model", "phi3:mini",
                "messages", messages,
                "stream", false,
                "keep_alive", -1,
                "options", Map.of(
                        "stop", List.of("\n\n"),
                        "num_ctx", 1024,
                        "num_predict", 150,
                        "temperature", 0.2,
                        "top_p", 0.8,
                        "repeat_penalty", 1.2,
                        "num_thread", 8
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, request, Map.class);

        Map<String, Object> responseBody = responseEntity.getBody();

        if (responseBody == null || !responseBody.containsKey("message")) {
            return "Error al generar respuesta";
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> messageObj = (Map<String, Object>) responseBody.get("message");

        return (String) messageObj.get("content");
    }
}