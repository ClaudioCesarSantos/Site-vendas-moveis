package com.api.senac.ProjetoIntegrador.service;

import com.api.senac.ProjetoIntegrador.model.Produto;
import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    Produto salvarProduto(Produto produto);
    List<Produto> listarProdutos();
    Produto atualizarProduto(Long id, Produto produto);
    void deletarProduto(Long id);
    public Produto buscarPorId(Long id);
    public List<Produto> listarPorCategoria(String categoria);

}
