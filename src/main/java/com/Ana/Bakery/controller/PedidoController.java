package com.Ana.Bakery.controller;

import com.Ana.Bakery.model.Pedido;
import com.Ana.Bakery.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public List<Pedido> getAll(){
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id){

        return pedidoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pedido create(@RequestBody Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody Pedido pedidoActualizado){

        return pedidoRepository.findById(id)
                .map(pedido -> {

                    pedido.setEstado(pedidoActualizado.getEstado());
                    pedido.setFechaEntrega(pedidoActualizado.getFechaEntrega());
                    pedido.setPrecioTotal(pedidoActualizado.getPrecioTotal());
                    pedido.setNotasAlergias(pedidoActualizado.getNotasAlergias());
                    pedido.setIngredientesSeleccionados(pedidoActualizado.getIngredientesSeleccionados());

                    return ResponseEntity.ok(pedidoRepository.save(pedido));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        return pedidoRepository.findById(id)
                .map(pedido -> {
                    pedidoRepository.delete(pedido);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
