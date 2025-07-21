package com.mySkin.entities;

import com.mySkin.dtos.CharacteristicDTO;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "characteristic")
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public Characteristic(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Characteristic() {
    }

    public Characteristic(CharacteristicDTO characteristicDTO) {
        this.id = characteristicDTO.getId();
        this.description = characteristicDTO.getDescription();
    }
}
