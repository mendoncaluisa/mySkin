package com.mySkin.dtos;

import com.mySkin.entities.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

public class RoleDTO extends RepresentationModel<RoleDTO> {

    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String authority;

    public RoleDTO() {}

    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDTO(Role role) {
        this.id = role.getId();
        this.authority = role.getAuthority();
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                '}';
    }
}
