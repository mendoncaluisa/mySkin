package com.mySkin.services;

import com.mySkin.dtos.CharacteristicDTO;
import com.mySkin.dtos.RoleDTO;
import com.mySkin.dtos.UserDTO;
import com.mySkin.dtos.UserInsertDTO;
import com.mySkin.entities.Characteristic;
import com.mySkin.entities.Role;
import com.mySkin.entities.User;
import com.mySkin.repository.CharacteristicRepository;
import com.mySkin.repository.RoleRepository;
import com.mySkin.repository.UserRepository;
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
        return list.map(u -> new UserDTO(u));
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> opt = userRepository.findById(id);
        User user = opt.orElseThrow(() -> new ResourceNotFound("Usuário não encontrado"));

        return new UserDTO(user);
    }

    @Transactional(readOnly = true)
    public User findEntityById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFound("Usuário não encontrado"));
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity.setPassword(
                passwordEncoder.encode(dto.getPassword()));
        User newUser = userRepository.save(entity);

        return new UserDTO(newUser);
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
            User entity = userRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);

            entity = userRepository.save(entity);
            return new UserDTO(entity);
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
