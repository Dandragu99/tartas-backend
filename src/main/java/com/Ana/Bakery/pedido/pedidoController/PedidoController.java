package com.Ana.Bakery.pedido.pedidoController;

import com.Ana.Bakery.pedido.dto.CrearPedidoCarritoDTO;
import com.Ana.Bakery.pedido.dto.CrearPedidoDTO;
import com.Ana.Bakery.pedido.dto.PedidoDTO;
import com.Ana.Bakery.pedido.pedidoModel.Pedido;
import com.Ana.Bakery.pedido.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping(path = "/all")
    public List<PedidoDTO> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> getById(@PathVariable Long id){
        return service.findById(id);
    }
    @GetMapping("/usuario/{usuarioId}")
    public List<PedidoDTO> getByUsuario(@PathVariable Long usuarioId) {
        return service.findByUsuarioId(usuarioId);
    }

    @PostMapping
    public PedidoDTO create(@RequestBody CrearPedidoDTO dto) {
        return service.crearPedido(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @RequestBody Pedido pedidoActualizado){
        return service.updateById(id, pedidoActualizado);
    }

    @PostMapping("/carrito")
    public List<PedidoDTO> createFromCart(@RequestBody CrearPedidoCarritoDTO dto) {
        return service.crearPedidoDesdeCarrito(dto);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<PedidoDTO> updateEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return service.updateEstado(id, body.get("estado"));
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
