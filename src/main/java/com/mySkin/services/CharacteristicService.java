package com.mySkin.services;

import com.mySkin.dtos.CharacteristicDTO;
import com.mySkin.dtos.IngredientDTO;
import com.mySkin.dtos.ReviewDTO;
import com.mySkin.entities.*;
import com.mySkin.repository.CharacteristicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CharacteristicService {

    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Transactional(readOnly = true)
    public Page<CharacteristicDTO> findAll(Pageable pageable) {
        Page<Characteristic> list = characteristicRepository.findAll(pageable);
        return list.map(s -> new CharacteristicDTO(s));
    }

    @Transactional(readOnly = true)
    public CharacteristicDTO findById(Long id) {
        Optional<Characteristic> characteristic = characteristicRepository.findById(id);
        CharacteristicDTO characteristicDTO =  new CharacteristicDTO(characteristic.get());

        return characteristicDTO;
    }

    @Transactional
    public CharacteristicDTO insert(CharacteristicDTO characteristicDTO) {
        Characteristic entity = new Characteristic();

        entity.setDescription(characteristicDTO.getDescription());
        characteristicRepository.save(entity);

        return new CharacteristicDTO(entity);

    }


    @Transactional
    public CharacteristicDTO update(CharacteristicDTO characteristicDTO, Long id) {
        Characteristic entity = characteristicRepository.getReferenceById(id);

        entity.setDescription(characteristicDTO.getDescription());
        entity = characteristicRepository.save(entity);

        return new CharacteristicDTO(entity);
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
