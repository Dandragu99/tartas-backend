package com.Ana.Bakery.pedido.pedidoRepository;

import com.Ana.Bakery.pedido.pedidoModel.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
