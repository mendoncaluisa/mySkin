package com.mySkin.dtos;

import com.mySkin.entities.Characteristic;
import lombok.Data;

@Data
public class CharacteristicDTO {

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

}
