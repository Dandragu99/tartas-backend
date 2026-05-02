package com.Ana.Bakery.pedido.pedidoController;

import com.Ana.Bakery.pedido.dto.CrearPedidoDTO;
import com.Ana.Bakery.pedido.dto.PedidoDTO;
import com.Ana.Bakery.pedido.pedidoModel.Pedido;
import com.Ana.Bakery.pedido.pedidoRepository.PedidoRepository;
import com.Ana.Bakery.pedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping(path = "/all")
    public @ResponseBody List<PedidoDTO> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    public PedidoDTO create(@RequestBody CrearPedidoDTO dto) {
        return service.crearPedido(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @RequestBody Pedido pedidoActualizado){
        return service.updateById(id, pedidoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        boolean eliminado = service.deletById(id);

        if (eliminado){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
