package com.mySkin.services;

import com.mySkin.dtos.IngredientDTO;
import com.mySkin.entities.Ingredient;
import com.mySkin.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Transactional(readOnly = true)
    public Page<IngredientDTO> findAll(Pageable pageable) {
        Page<Ingredient> list = ingredientRepository.findAll(pageable);
        return list.map(p -> new IngredientDTO(p));
    }

    @Transactional(readOnly = true)
    public IngredientDTO findById(Long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        IngredientDTO ingredientDTO =  new IngredientDTO(ingredient.get());

        return ingredientDTO;
    }


    @Transactional
    public IngredientDTO insert(IngredientDTO ingredientDTO) {
        Ingredient entity = new Ingredient();

        entity.setComedogenico(ingredientDTO.isComedogenico());
        entity.setEstrutura(ingredientDTO.getEstrutura());
        entity.setDescricao(ingredientDTO.getDescricao());
        entity.setNome(ingredientDTO.getNome());
        entity.setFuncao(ingredientDTO.getFuncao());
        entity.setOrigem(ingredientDTO.getOrigem());
        entity.setFuncaoQuimica(ingredientDTO.getFuncaoQuimica());
        entity.setSeguro(ingredientDTO.isSeguro());
        entity.setPotIrritante(ingredientDTO.isPotIrritante());
        entity.setLigacaoQuimica(ingredientDTO.getLigacaoQuimica());

        entity = ingredientRepository.save(entity);

        return new IngredientDTO(entity);

    }


    @Transactional
    public IngredientDTO update(IngredientDTO ingredientDTO, Long id) {
        Ingredient entity = ingredientRepository.getReferenceById(id);

        entity.setComedogenico(ingredientDTO.isComedogenico());
        entity.setEstrutura(ingredientDTO.getEstrutura());
        entity.setDescricao(ingredientDTO.getDescricao());
        entity.setNome(ingredientDTO.getNome());
        entity.setFuncao(ingredientDTO.getFuncao());
        entity.setOrigem(ingredientDTO.getOrigem());
        entity.setFuncaoQuimica(ingredientDTO.getFuncaoQuimica());
        entity.setSeguro(ingredientDTO.isSeguro());
        entity.setPotIrritante(ingredientDTO.isPotIrritante());
        entity.setLigacaoQuimica(ingredientDTO.getLigacaoQuimica());

        entity = ingredientRepository.save(entity);

        return new IngredientDTO(entity);

    }

    @Transactional
    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllRooms() {
        ingredientRepository.deleteAll();
    }
}
