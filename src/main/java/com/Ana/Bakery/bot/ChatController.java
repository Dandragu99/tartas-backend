package com.Ana.Bakery.bot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final LlmService llmService;

    public ChatController(LlmService llmService) {
        this.llmService = llmService;
    }

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody Map<String, Object> body) {

        String mensaje = (String) body.get("mensaje");

        @SuppressWarnings("unchecked")
        List<Map<String, String>> historial = (List<Map<String, String>>) body.get("historial");


        if (mensaje == null || mensaje.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo 'mensaje' es obligatorio");
        }

        String respuesta = llmService.generarRespuesta(mensaje, historial);

        return ResponseEntity.ok(respuesta);
    }
}