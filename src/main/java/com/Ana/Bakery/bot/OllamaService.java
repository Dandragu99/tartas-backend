package com.Ana.Bakery.bot;

import com.Ana.Bakery.repository.IngredienteRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class OllamaService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final IngredienteRepository ingredienteRepository;

    public OllamaService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    public String generarRespuesta(String mensajeUsuario, List<String> historial) {
        String url = "http://localhost:11434/api/chat";

        // Catálogo dinámico
        List<String> ingredientes = ingredienteRepository.findAll()
                .stream()
                .map(i -> i.getNombre() + " (" + i.getDisponible() + " disponibles)")
                .toList();

        String catalogo = String.join("\n", ingredientes);

        List<Map<String, String>> messages = new ArrayList<>();

        // System prompt fuerte
        messages.add(Map.of(
                "role", "system",
                "content", """
                Eres un asistente experto de una tienda de tartas personalizadas.
                Reglas estrictas:
                - Solo recomienda ingredientes que aparezcan en el catálogo.
                - Nunca inventes ingredientes que no existan.
                - Sugiere combinaciones ricas y coherentes.
                Catálogo actual:
                """ + catalogo
        ));

        // Historial (mejora la conversación)
        if (historial != null) {
            for (String msg : historial) {
                messages.add(Map.of("role", "user", "content", msg));
            }
        }

        messages.add(Map.of("role", "user", "content", mensajeUsuario));

        Map<String, Object> body = Map.of(
                "model", "phi3",                    // prueba también "gemma2:2b"
                "messages", messages,
                "stream", false,
                "keep_alive", -1,                   // ← muy importante
                "options", Map.of(
                        "num_ctx", 4096,
                        "temperature", 0.7
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, request, Map.class);

        Map<String, Object> responseBody = responseEntity.getBody();
        if (responseBody == null || !responseBody.containsKey("message")) {
            return "Lo siento, hubo un error al procesar la respuesta.";
        }

        @SuppressWarnings("unchecked")
        Map<String, Object> messageObj = (Map<String, Object>) responseBody.get("message");
        return (String) messageObj.get("content");
    }
}