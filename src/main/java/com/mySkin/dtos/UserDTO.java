package com.mySkin.dtos;

import com.mySkin.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    @NotBlank(message = "Campo obrigatório")
    private String name;

    @Getter @Setter
    @NotBlank(message = "Campo obrigatório")
    private String username;

    @Getter @Setter
    @Email(message = "Favor, informar um e-mail válido")
    private String email;

    @Getter @Setter
    private LocalDate birthDate;

    @Getter @Setter
    SkinDTO skin = new SkinDTO();

    @Getter @Setter
    Set<RoleDTO> roles = new HashSet<>();

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
        entity.getRoles().
                forEach(
                        role -> this.roles.add(new RoleDTO(role)));
        //todo: por skinDTO
    }

}
