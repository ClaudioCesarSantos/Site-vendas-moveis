package com.api.senac.ProjetoIntegrador.data;

import com.api.senac.ProjetoIntegrador.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
