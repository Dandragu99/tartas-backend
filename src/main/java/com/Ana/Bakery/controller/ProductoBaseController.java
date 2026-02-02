package com.Ana.Bakery.controller;

import com.Ana.Bakery.model.ProductoBase;
import com.Ana.Bakery.repository.ProductoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos-base")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoBaseController {

    @Autowired
    private ProductoBaseRepository productoBaseRepository;

    @GetMapping
    public List<ProductoBase> getAll() {
        return productoBaseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoBase> getById(@PathVariable Long id){
        return productoBaseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}