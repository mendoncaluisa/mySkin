package com.mySkin.dtos;

import com.mySkin.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDTO {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String name;

    @NotBlank(message = "Campo obrigatório")
    private String username;

    @Email(message = "Favor, informar um e-mail válido")
    private String email;

    private LocalDate birthDate;

    private Set<CharacteristicDTO> characteristics = new HashSet<>();

    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String username, String email, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.birthDate = birthDate;
    }

    public UserDTO (User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.birthDate = entity.getBirthDate();
        this.roles = entity.getRoles().stream().map(RoleDTO::new).collect(Collectors.toSet()); //mapeia cada um dos roles para um novo dto
        this.characteristics = entity.getCharacteristic().stream().map(CharacteristicDTO::new).collect(Collectors.toSet()); //mapeia cada um dos roles para um novo dto
    }

}
