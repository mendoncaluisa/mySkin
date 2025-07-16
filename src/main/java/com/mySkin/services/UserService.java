package com.mySkin.services;

import com.mySkin.dtos.*;
import com.mySkin.entities.Characteristic;
import com.mySkin.entities.Role;
import com.mySkin.entities.User;
import com.mySkin.repository.CharacteristicRepository;
import com.mySkin.repository.RoleRepository;
import com.mySkin.repository.UserRepository;
import com.mySkin.resources.ProductResource;
import com.mySkin.resources.UserResource;
import com.mySkin.services.exceptions.DatabaseException;
import com.mySkin.services.exceptions.ResourceNotFound;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CharacteristicRepository characteristicRepository;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> list = userRepository.findAll(pageable);
        return list.map(u -> new UserDTO(u)
                .add(linkTo(methodOn(UserResource.class).findAll(null)).withSelfRel())
                .add(linkTo(methodOn(UserResource.class).findById(u.getId())).withRel("One user")));
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> opt = userRepository.findById(id);
        User user = opt.orElseThrow(() -> new ResourceNotFound("Usuário não encontrado"));

        return new UserDTO(user)
                .add(linkTo(methodOn(UserResource.class).findById(user.getId())).withSelfRel())
                .add(linkTo(methodOn(UserResource.class).findAll(null)).withRel("All Users"))
                .add(linkTo(methodOn(UserResource.class).update(user.getId(), new UserDTO(user))).withRel("Update"))
                .add(linkTo(methodOn(UserResource.class).delete(user.getId())).withRel("Delete"));
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(
                passwordEncoder.encode(dto.getPassword()));
        User user = userRepository.save(entity);

        return new UserDTO(user)
                .add(linkTo(methodOn(UserResource.class).findById(user.getId())).withRel("One User"))
                .add(linkTo(methodOn(UserResource.class).findAll(null)).withRel("All Users"))
                .add(linkTo(methodOn(UserResource.class).update(user.getId(), new UserDTO(user))).withRel("Update"))
                .add(linkTo(methodOn(UserResource.class).delete(user.getId())).withRel("Delete"));
    }
    
    private void copyDtoToEntity(UserInsertDTO dto, User entity) {
        entity.setUsername(dto.getUsername());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        // Não precisamos definir roles aqui, pois o UserInsertDTO não tem roles
    }
    
    private void copyDtoToEntity(UserDTO dto, User entity) {
        entity.setUsername(dto.getUsername());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());

        entity.getCharacteristic().clear();
        for (CharacteristicDTO characteristic : dto.getCharacteristics()) {
            Characteristic c = characteristicRepository.getReferenceById(characteristic.getId());
            entity.getCharacteristic().add(c);
        }

        entity.getRoles().clear();
        for (RoleDTO role : dto.getRoles()) {
            Role r = roleRepository.getReferenceById(role.getId());
            entity.getRoles().add(r);
        }
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User user = userRepository.getReferenceById(id);
            copyDtoToEntity(dto, user);

            user = userRepository.save(user);
            return new UserDTO(user)
                    .add(linkTo(methodOn(UserResource.class).findById(user.getId())).withRel("One User"))
                    .add(linkTo(methodOn(UserResource.class).delete(null)).withRel("Delete Users"));

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFound("Usuário com id: " + id + " não encontrado");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFound("Usuário não encontrado");
        }
        try {
            userRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    @Transactional
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }

}
