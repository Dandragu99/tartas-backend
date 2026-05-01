package com.Ana.Bakery.pedido.service;

import com.Ana.Bakery.pedido.dto.CrearPedidoDTO;
import com.Ana.Bakery.pedido.dto.PedidoDTO;
import com.Ana.Bakery.pedido.pedidoModel.Pedido;
import com.Ana.Bakery.pedido.pedidoRepository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<PedidoDTO> findAll(){
        return repository.findAll().stream().map(pedido -> new PedidoDTO(
                pedido.getId(),
                pedido.getUsuario().getId(),
                pedido.getUsuario().getNombreCompleto(),
                pedido.getProductoBase().getNombre(),
                pedido.getIngredientesSeleccionados().stream().map(ingrediente -> ingrediente.getNombre()).toList(),
                pedido.getEstado(),
                pedido.getPrecioTotal(),
                pedido.getFechaEntrega()
        )).toList();
    }

    public Optional<Pedido> find(Long id){
        return repository.findById(id);
    }

/*    public PedidoDTO crearPedido(CrearPedidoDTO dto) {
        return repository.save();
    }
*/
}
