package com.Ana.Bakery.pedido.service;

import com.Ana.Bakery.ingrediente.ingredienteModel.Ingrediente;
import com.Ana.Bakery.ingrediente.ingredienteRepository.IngredienteRepository;
import com.Ana.Bakery.pedido.dto.CrearPedidoCarritoDTO;
import com.Ana.Bakery.pedido.dto.CrearPedidoDTO;
import com.Ana.Bakery.pedido.dto.PedidoDTO;
import com.Ana.Bakery.pedido.estadoPedido.EstadoPedido;
import com.Ana.Bakery.pedido.mapper.PedidoMapper;
import com.Ana.Bakery.pedido.pedidoModel.Pedido;
import com.Ana.Bakery.pedido.pedidoRepository.PedidoRepository;
import com.Ana.Bakery.producto.productoModel.ProductoBase;
import com.Ana.Bakery.producto.productoRepository.ProductoBaseRepository;
import com.Ana.Bakery.usuario.usuarioModel.Usuario;
import com.Ana.Bakery.usuario.usuarioRepository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    @Autowired
    private PedidoMapper mapper;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProductoBaseRepository productoRepository;
    @Autowired
    private IngredienteRepository ingredienteRepository;


    public List<PedidoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }


    public PedidoDTO crearPedido(CrearPedidoDTO dto) {
        Pedido p = new Pedido();
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        ProductoBase producto = productoRepository.findById(dto.getProductoBaseId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        List<Ingrediente> ingredientes = ingredienteRepository.findAllById(dto.getIngredientesIds());

        p.setUsuario(usuario);
        p.setProductoBase(producto);
        p.setIngredientesSeleccionados(ingredientes);
        p.setNotasAlergias(dto.getNotasAlergias());
        p.setFechaEntrega(dto.getFechaEntrega());
        p.setEstado(EstadoPedido.PENDIENTE);

        Pedido pedidoGuardado = repository.save(p);
        return mapper.toDto(pedidoGuardado);
    }

    public ResponseEntity<PedidoDTO> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public List<PedidoDTO> findByUsuarioId(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public ResponseEntity<PedidoDTO> updateById( Long id, Pedido pedidoActualizado){
        return repository.findById(id)
                .map(pedido -> {

                    pedido.setEstado(pedidoActualizado.getEstado());
                    pedido.setFechaEntrega(pedidoActualizado.getFechaEntrega());
                    pedido.setPrecioTotal(pedidoActualizado.getPrecioTotal());
                    pedido.setNotasAlergias(pedidoActualizado.getNotasAlergias());
                    pedido.setIngredientesSeleccionados(pedidoActualizado.getIngredientesSeleccionados());

                    Pedido pedidoGuardado = repository.save(pedido);
                    return ResponseEntity.ok(mapper.toDto(pedidoGuardado));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    public ResponseEntity<PedidoDTO> updateEstado(Long id, String estado) {
        return repository.findById(id)
                .map(pedido -> {
                    pedido.setEstado(EstadoPedido.valueOf(estado));
                    return ResponseEntity.ok(mapper.toDto(repository.save(pedido)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public boolean deletById(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<PedidoDTO> crearPedidoDesdeCarrito(CrearPedidoCarritoDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return dto.getItems().stream().map(item -> {
            Pedido p = new Pedido();
            ProductoBase producto = productoRepository.findById(item.getProductoBaseId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
            List<Ingrediente> ingredientes = ingredienteRepository.findAllById(item.getIngredientesIds());

            p.setUsuario(usuario);
            p.setProductoBase(producto);
            p.setIngredientesSeleccionados(ingredientes);
            p.setNotasAlergias(item.getAlergias());
            p.setPrecioTotal(item.getPrecioUnitario() * item.getCantidad());
            p.setEstado(EstadoPedido.PENDIENTE);
            p.setFechaEntrega(LocalDateTime.now().plusDays(3));

            return mapper.toDto(repository.save(p));
        }).toList();
    }
}
