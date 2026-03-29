package com.Ana.Bakery.bot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final OllamaService ollamaService;

    public ChatController(OllamaService ollamaService) {
        this.ollamaService = ollamaService;
    }

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody Map<String, Object> requestBody) {

        String mensaje = (String) requestBody.get("mensaje");

        @SuppressWarnings("unchecked")
        List<String> historial = (List<String>) requestBody.get("historial");

        if (mensaje == null || mensaje.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo 'mensaje' es obligatorio");
        }

        String respuesta = ollamaService.generarRespuesta(mensaje, historial != null ? historial : List.of());

        return ResponseEntity.ok(respuesta);
    }
}



/*
* Prompt del bot:
* Eres un asistente de una tienda de tartas personalizadas.

Tu objetivo es ayudar al cliente a elegir ingredientes.

Reglas:
- Solo puedes recomendar ingredientes disponibles
- Usa este catálogo:
[LISTA DE INGREDIENTES]
* List<Ingrediente> ingredientes = ingredienteRepository.findAll();
- No inventes ingredientes
- Sugiere combinaciones coherentes

Usuario: "quiero algo de chocolate"
*
* */
