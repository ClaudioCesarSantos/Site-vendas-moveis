package com.api.senac.ProjetoIntegrador.controller;

import com.api.senac.ProjetoIntegrador.model.ItemPedido;
import com.api.senac.ProjetoIntegrador.model.Pedido;
import com.api.senac.ProjetoIntegrador.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido criarPedido(@RequestBody PedidoRequest request) {
        Pedido pedido = new Pedido();
        pedido.setNomeCliente(request.getNomeCliente());
        pedido.setEmail(request.getEmail());
        pedido.setTelefone(request.getTelefone());
        pedido.setMetodoPagamento(request.getMetodoPagamento());
        pedido.setTotal(request.getTotal());

        return pedidoService.criarPedido(pedido, request.getItens());
    }
}
