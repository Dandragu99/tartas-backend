package com.Ana.Bakery.bot;

import com.Ana.Bakery.ingrediente.ingredienteRepository.IngredienteRepository;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;


public class OllamaLocalService implements LlmService{

    private final RestTemplate restTemplate = new RestTemplate();
    private final IngredienteRepository ingredienteRepository;

    public OllamaLocalService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public String generarRespuesta(String mensajeUsuario, List<Map<String, String>> historial) {

        String url = "http://localhost:11434/api/chat";

        List<String> ingredientes = ingredienteRepository.findAll()
                .stream()
                .limit(10)
                .map(i -> i.getNombre())
                .toList();

        String catalogo = String.join(", ", ingredientes);

        List<Map<String, String>> messages = new ArrayList<>();

        messages.add(Map.of(
                "role", "system",
                "content", """
                        Eres un asistente de una tienda de tartas.

                        REGLAS:
                        - Solo puedes usar ingredientes del catálogo
                        - No inventes ingredientes
                        - Responde de forma breve (máx 1 frase)
                        - Sugiere combinaciones del catálogo

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
                        "num_ctx", 1024,
                        "num_predict", 80,
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