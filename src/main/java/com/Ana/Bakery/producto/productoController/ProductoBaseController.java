package com.Ana.Bakery.producto.productoController;

import com.Ana.Bakery.ingrediente.ingredienteModel.Ingrediente;
import com.Ana.Bakery.producto.productoBaseDTO.ProductoBaseDTO;
import com.Ana.Bakery.producto.productoBaseService.ProductoBaseService;
import com.Ana.Bakery.producto.productoModel.ProductoBase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos-base")
@RequiredArgsConstructor
public class ProductoBaseController {

    private final ProductoBaseService productoBaseService;

    @GetMapping
    public List<ProductoBaseDTO> getAll() {
        return productoBaseService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoBaseDTO> getById(@PathVariable Long id) {
        return productoBaseService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/ingredientes")
    public ResponseEntity<List<Ingrediente>> getIngredientes(@PathVariable Long id) {
        List<Ingrediente> ingredientes = productoBaseService.getIngredientes(id);
        return ingredientes.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(ingredientes);
    }

    @PostMapping
    public ProductoBaseDTO create(@RequestBody ProductoBase producto) {
        return productoBaseService.create(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoBaseDTO> update(@PathVariable Long id, @RequestBody ProductoBase productoActualizado) {
        return productoBaseService.update(id, productoActualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return productoBaseService.delete(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}