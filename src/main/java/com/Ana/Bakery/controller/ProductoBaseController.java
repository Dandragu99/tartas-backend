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
    @PostMapping
    public ProductoBase create(@RequestBody ProductoBase producto){
        return productoBaseRepository.save(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoBase> update(@PathVariable Long id, @RequestBody ProductoBase productoActualizado){

        return productoBaseRepository.findById(id)
                .map(producto -> {

                    producto.setNombre(productoActualizado.getNombre());
                    producto.setDescripcion(productoActualizado.getDescripcion());
                    producto.setPrecioBase(productoActualizado.getPrecioBase());
                    producto.setImgPaso1(productoActualizado.getImgPaso1());
                    producto.setImgPaso2(productoActualizado.getImgPaso2());
                    producto.setImgPaso3(productoActualizado.getImgPaso3());

                    ProductoBase actualizado = productoBaseRepository.save(producto);

                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
            public ResponseEntity<?> delete(@PathVariable Long id){

        return productoBaseRepository.findById(id)
                .map(producto -> {
                    productoBaseRepository.delete(producto);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}