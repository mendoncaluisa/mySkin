package com.mySkin.entities;

import com.mySkin.dtos.IngredientDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private String funcao;

    private String origem;

    private boolean seguro;

    private boolean comedogenico;

    private boolean potIrritante;

    private String estrutura;

    private String funcaoQuimica;

    private String ligacaoQuimica;

    public Ingredient(IngredientDTO ingredientDTO) {
        this.nome = ingredientDTO.getNome();
        this.descricao = ingredientDTO.getDescricao();
        this.funcao = ingredientDTO.getFuncao();
        this.origem = ingredientDTO.getOrigem();
        this.seguro = ingredientDTO.isSeguro();
        this.comedogenico = ingredientDTO.isComedogenico();
        this.potIrritante = isPotIrritante();
        this.estrutura = ingredientDTO.getEstrutura();
        this.funcaoQuimica = ingredientDTO.getFuncaoQuimica();
        this.ligacaoQuimica = ingredientDTO.getLigacaoQuimica();
    }

    public Ingredient(Long id, String nome, String descricao, String funcao, String origem, boolean seguro, boolean comedogenico, boolean potIrritante, String estrutura, String funcaoQuimica, String ligacaoQuimica) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.funcao = funcao;
        this.origem = origem;
        this.seguro = seguro;
        this.comedogenico = comedogenico;
        this.potIrritante = potIrritante;
        this.estrutura = estrutura;
        this.funcaoQuimica = funcaoQuimica;
        this.ligacaoQuimica = ligacaoQuimica;
    }

    public Ingredient() {
    }
}
