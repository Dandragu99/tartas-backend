package com.Ana.Bakery.controller;

import com.Ana.Bakery.model.Ingrediente;
import com.Ana.Bakery.repository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
@CrossOrigin(origins = "http://localhost:4200")
public class IngredienteController {
    @Autowired
    private IngredienteRepository ingredienteRepository;
    @GetMapping
    public List<Ingrediente> getAll() {
        return ingredienteRepository.findAll();
    }
}
