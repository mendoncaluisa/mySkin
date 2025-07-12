package com.mySkin.entities;

import com.mySkin.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column (unique = true)
    @Getter @Setter
    private String username;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private LocalDate birthDate;

    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_skin")
    private Skin skin = new Skin();



    public User() {
    }

    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.birthDate = userDTO.getBirthDate();


    }

    public User(Long id, String username, String name, String email, String password, LocalDate birthDate) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;

    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", roles=" + roles +
                ", skin=" + skin +
                '}';
    }
}
