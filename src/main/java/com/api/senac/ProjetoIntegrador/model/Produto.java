package com.api.senac.ProjetoIntegrador.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    private Double preco;
    private String categoria;
    private String imagem;
    private String status;
    
    public Produto(String nome, String descricao, Double preco, String categoria, String imagem, String status) {
        this.nome      = nome;
        this.descricao = descricao;
        this.preco     = preco;
        this.categoria = categoria;
        this.imagem    = imagem;
        this.status    = status;
    }



}
