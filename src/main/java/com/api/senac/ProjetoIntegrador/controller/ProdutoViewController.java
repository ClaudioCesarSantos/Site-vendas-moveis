package com.api.senac.ProjetoIntegrador.controller;

import com.api.senac.ProjetoIntegrador.model.Produto;
import com.api.senac.ProjetoIntegrador.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Controller
public class ProdutoViewController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produtos")
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoService.listarProdutos();
        model.addAttribute("produtos", produtos);
        return "produtos"; 
    }
    
    @GetMapping("/detalhes/{id}")
    public String detalhesProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id);

        if (produto == null) {
            return "redirect:/produtos"; // Redireciona se não encontrar
        }

        model.addAttribute("produto", produto);
        return "detalhes";
    }
    
    @GetMapping("/produtos/categoria/{categoria}")
    public String listarPorCategoria(@PathVariable String categoria, Model model) {
        model.addAttribute("produtos", produtoService.listarPorCategoria(categoria));
        model.addAttribute("categoria", categoria);
        return "produtos";
    }  
    
     @GetMapping("/carrinho")
    public String verCarrinho() {
        return "carrinho";
    }

    @GetMapping("/checkout")
    public String verCheckout() {
        return "checkout";
    }


}
