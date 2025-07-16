package com.mySkin.services;

import com.mySkin.dtos.CharacteristicDTO;
import com.mySkin.dtos.IngredientDTO;
import com.mySkin.dtos.ReviewDTO;
import com.mySkin.entities.*;
import com.mySkin.repository.CharacteristicRepository;
import com.mySkin.resources.CharacteristicResource;
import com.mySkin.resources.ProductResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

@Service
public class CharacteristicService {

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Transactional(readOnly = true)
    public Page<CharacteristicDTO> findAll(Pageable pageable) {
        Page<Characteristic> list = characteristicRepository.findAll(pageable);
        return list.map(s -> new CharacteristicDTO(s)
                .add(linkTo(methodOn(CharacteristicResource.class).findAll(null)).withSelfRel())
                .add(linkTo(methodOn(CharacteristicResource.class).findById(s.getId())).withRel("One characteristic")));
    }

    @Transactional(readOnly = true)
    public CharacteristicDTO findById(Long id) {
        Optional<Characteristic> characteristic = characteristicRepository.findById(id);
        CharacteristicDTO characteristicDTO =  new CharacteristicDTO(characteristic.get());

        return characteristicDTO
                .add(linkTo(methodOn(CharacteristicResource.class).findById(characteristicDTO.getId())).withSelfRel())
                .add(linkTo(methodOn(CharacteristicResource.class).findAll(null)).withRel("All characteristics"))
                .add(linkTo(methodOn(CharacteristicResource.class).update(characteristicDTO.getId(), new CharacteristicDTO(characteristicDTO))).withRel("Update characteristic"))
                .add(linkTo(methodOn(CharacteristicResource.class).delete(characteristicDTO.getId())).withRel("Delete characteristic"));
    }

    @Transactional
    public CharacteristicDTO insert(CharacteristicDTO characteristicDTO) {
        Characteristic entity = new Characteristic();

        entity.setDescription(characteristicDTO.getDescription());
        characteristicRepository.save(entity);

        return new CharacteristicDTO(entity)
                .add(linkTo(methodOn(CharacteristicResource.class).findById(entity.getId())).withRel("One characteristic"))
                .add(linkTo(methodOn(CharacteristicResource.class).findAll(null)).withRel("All characteristic"))
                .add(linkTo(methodOn(CharacteristicResource.class).update(entity.getId(), new CharacteristicDTO(entity))).withRel("Update characteristic"))
                .add(linkTo(methodOn(CharacteristicResource.class).delete(entity.getId())).withRel("Delete characteristic"));

    }


    @Transactional
    public CharacteristicDTO update(CharacteristicDTO characteristicDTO, Long id) {
        Characteristic entity = characteristicRepository.getReferenceById(id);

        entity.setDescription(characteristicDTO.getDescription());
        entity = characteristicRepository.save(entity);

        return new CharacteristicDTO(entity)
                .add(linkTo(methodOn(CharacteristicResource.class).findById(entity.getId())).withRel("One characteristic"))
                .add(linkTo(methodOn(CharacteristicResource.class).delete(entity.getId())).withRel("Delete characteristic"));

    }

    @Transactional
    public void delete(Long id) {
        characteristicRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllCharacteristics() {
        characteristicRepository.deleteAll();
    }


}
