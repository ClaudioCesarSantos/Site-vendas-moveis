package com.api.senac.ProjetoIntegrador.service;

import com.api.senac.ProjetoIntegrador.data.ProdutoRepository;
import com.api.senac.ProjetoIntegrador.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
    }


    @Override
    public Produto atualizarProduto(Long id, Produto produto) {
        return produtoRepository.findById(id)
            .map(existente -> {
                existente.setNome(produto.getNome());
                existente.setDescricao(produto.getDescricao());
                existente.setPreco(produto.getPreco());
                existente.setCategoria(produto.getCategoria());
                existente.setImagem(produto.getImagem());
                return produtoRepository.save(existente);
            })
            .orElse(null);
    }

    @Override
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
    
    @Override
    public List<Produto> listarPorCategoria(String categoria) {
        return produtoRepository.findByCategoria(categoria);
    }
}
