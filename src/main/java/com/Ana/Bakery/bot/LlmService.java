package com.Ana.Bakery.bot;

import java.util.List;
import java.util.Map;

public interface LlmService {
    String generarRespuesta(String mensajeUsuari, List<Map<String,String>> historial);
}
