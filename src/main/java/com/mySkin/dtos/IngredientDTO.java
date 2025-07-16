package com.mySkin.dtos;

import com.mySkin.entities.Ingredient;
import com.mySkin.entities.Product;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;


@Data
public class IngredientDTO extends RepresentationModel<IngredientDTO> {

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

    public IngredientDTO() {
    }
    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.nome = ingredient.getNome();
        this.descricao = ingredient.getDescricao();
        this.funcao = ingredient.getFuncao();
        this.origem = ingredient.getOrigem();
        this.seguro = ingredient.isSeguro();
        this.comedogenico = ingredient.isComedogenico();
        this.ligacaoQuimica = ingredient.getLigacaoQuimica();
        this.funcaoQuimica = ingredient.getFuncaoQuimica();
        this.estrutura = ingredient.getEstrutura();
        this.potIrritante = ingredient.isPotIrritante();
    }

    public IngredientDTO(Long id, String nome, String descricao, String funcao, String origem, boolean seguro, boolean comedogenico, boolean potIrritante, String estrutura, String funcaoQuimica, String ligacaoQuimica) {
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

    public IngredientDTO(IngredientDTO ingredientDTO) {
        this.id = ingredientDTO.id;
        this.nome = ingredientDTO.nome;
        this.descricao = ingredientDTO.descricao;
        this.funcao = ingredientDTO.funcao;
        this.origem = ingredientDTO.origem;
        this.seguro = ingredientDTO.seguro;
        this.comedogenico = ingredientDTO.comedogenico;
        this.potIrritante = ingredientDTO.isPotIrritante();
        this.estrutura = ingredientDTO.estrutura;
        this.funcaoQuimica = ingredientDTO.funcaoQuimica;
        this.ligacaoQuimica = ingredientDTO.ligacaoQuimica;
    }
}
