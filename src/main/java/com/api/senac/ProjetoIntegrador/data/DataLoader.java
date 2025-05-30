package com.api.senac.ProjetoIntegrador.data;

import com.api.senac.ProjetoIntegrador.model.Produto;
import com.api.senac.ProjetoIntegrador.data.ProdutoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader {

    private final ProdutoRepository produtoRepository;

    public DataLoader(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostConstruct
    public void carregarDados() {
        if (produtoRepository.count() == 0) {
            List<Produto> produtos = Arrays.asList(
                    new Produto("Sofá Cinza", "Sofá de 3 lugares, tecido suede, cor cinza.", 1800.00, "Sofás", "sofa-cinza.jpg", "Disponível"),
                    new Produto("Sofá Verde", "Sofá moderno, cor verde, com encosto reclinável.", 1900.00, "Sofás", "sofa-verde.jpg", "Disponível"),
                    new Produto("Sofá de Couro", "Sofá de couro legítimo, 2 lugares, na cor marrom.", 2500.00, "Sofás", "sofa-de-couro.jpg", "Disponível"),

                    new Produto("Cadeira Estofada Branca", "Cadeira confortável, acabamento branco.", 350.00, "Cadeiras", "cadeira-estofada-branca.jpg", "Disponível"),
                    new Produto("Cadeira Estofada Marrom", "Cadeira confortável, acabamento marrom.", 350.00, "Cadeiras", "cadeira-estofada-marrom.jpg", "Disponível"),
                    new Produto("Cadeira Marrom", "Cadeira simples, estrutura metálica, cor marrom.", 250.00, "Cadeiras", "cadeira-marrom.jpg", "Disponível"),

                    new Produto("Geladeira Branca", "Geladeira 400L, cor branca, frost free.", 3200.00, "Eletrodomésticos", "geladeira-branca.jpg", "Disponível"),
                    new Produto("Geladeira Pequena", "Mini geladeira, ideal para ambientes compactos.", 1500.00, "Eletrodomésticos", "geladeira-pequena.jpg", "Disponível"),
                    new Produto("Geladeira Preta", "Geladeira moderna, acabamento preto fosco.", 3500.00, "Eletrodomésticos", "geladeira-preta.jpg", "Disponível"),

                    new Produto("Microondas Clássico Branco", "Microondas 30L, acabamento branco.", 600.00, "Eletrodomésticos", "microondas-classico-branco.jpg", "Disponível"),
                    new Produto("Microondas Clássico", "Microondas 30L, acabamento prata.", 620.00, "Eletrodomésticos", "microondas-classico.jpg", "Disponível"),
                    new Produto("Microondas Rosa", "Microondas retrô, acabamento rosa.", 650.00, "Eletrodomésticos", "microondas-rosa.jpg", "Disponível")
            );

            produtoRepository.saveAll(produtos);
            System.out.println("🗄️ Dados de produtos carregados no banco.");
        } else {
            System.out.println("✔️ Dados já existentes no banco.");
        }
    }
}
