package com.mySkin.dtos;

import com.mySkin.entities.Characteristic;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class CharacteristicDTO extends RepresentationModel<CharacteristicDTO> {

    private Long id;

    private String description;

    public CharacteristicDTO(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public CharacteristicDTO() {}

    public CharacteristicDTO(Characteristic characteristic) {
        this.id = characteristic.getId();
        this.description = characteristic.getDescription();
    }

    public CharacteristicDTO(CharacteristicDTO characteristicDTO) {
        this.id = characteristicDTO.getId();
        this.description = characteristicDTO.getDescription();
    }
}
