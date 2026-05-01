package com.Ana.Bakery.ingrediente.ingredienteController;

import com.Ana.Bakery.ingrediente.ingredienteModel.Ingrediente;
import com.Ana.Bakery.ingrediente.ingredienteRepository.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PostMapping
    public Ingrediente create(@RequestBody Ingrediente ingrediente){
        return ingredienteRepository.save(ingrediente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> update(@PathVariable Long id, @RequestBody Ingrediente ingredienteActualizado){

        return ingredienteRepository.findById(id)
                .map(ingrediente -> {

                    ingrediente.setNombre(ingredienteActualizado.getNombre());
                    ingrediente.setTipo(ingredienteActualizado.getTipo());
                    ingrediente.setPrecioAdicional(ingredienteActualizado.getPrecioAdicional());
                    ingrediente.setDisponible(ingredienteActualizado.getDisponible());

                    return ResponseEntity.ok(ingredienteRepository.save(ingrediente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        return ingredienteRepository.findById(id)
                .map(ingrediente -> {
                    ingredienteRepository.delete(ingrediente);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
