package com.api.senac.ProjetoIntegrador.service;

import com.api.senac.ProjetoIntegrador.model.ItemPedido;
import com.api.senac.ProjetoIntegrador.model.Pedido;
import com.api.senac.ProjetoIntegrador.model.Produto;
import com.api.senac.ProjetoIntegrador.data.ItemPedidoRepository;
import com.api.senac.ProjetoIntegrador.data.PedidoRepository;
import com.api.senac.ProjetoIntegrador.data.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido criarPedido(Pedido pedido, List<ItemPedido> itens) {
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        for (ItemPedido item : itens) {
            Produto produto = produtoRepository.findById(item.getProduto().getId()).orElseThrow();
            item.setPedido(pedidoSalvo);
            item.setProduto(produto);
            itemPedidoRepository.save(item);

            // Opcional: atualizar status do produto
            produto.setStatus("Vendido");
            produtoRepository.save(produto);
        }

        return pedidoSalvo;
    }
}
