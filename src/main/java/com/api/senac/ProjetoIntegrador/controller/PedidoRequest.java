package com.api.senac.ProjetoIntegrador.controller;

import com.api.senac.ProjetoIntegrador.model.ItemPedido;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequest {
    private String nomeCliente;
    private String email;
    private String telefone;
    private String metodoPagamento;
    private Double total;
    private List<ItemPedido> itens;
}
