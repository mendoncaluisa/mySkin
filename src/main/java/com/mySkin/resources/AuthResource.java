package com.mySkin.resources;

import com.mySkin.config.JwtUtil;
import com.mySkin.entities.User;
import com.mySkin.entities.Role;
import com.mySkin.entities.Characteristic;
import com.mySkin.repository.UserRepository;
import com.mySkin.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthResource {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public static class RegisterRequest {
        public String username;
        public String password;
        public String name;
        public String email;
        public Set<Characteristic> characteristic;
        // getters e setters podem ser adicionados se quiser
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Auth endpoint funcionando!");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        System.out.println("=== REGISTER CALLED ===");
        try {
            String username = request.username;
            String password = request.password;
            String name = request.name;
            String email = request.email;
            Set<Characteristic> characteristic = request.characteristic;
            
            System.out.println("Username: " + username);
            System.out.println("Name: " + name);
            System.out.println("Email: " + email);
            System.out.println("Characteristic: " + characteristic);
            
            if (username == null || password == null || name == null || email == null) {
                return ResponseEntity.badRequest().body("Todos os campos são obrigatórios");
            }
            
            if (userRepository.findByUsername(username).isPresent()) {
                return ResponseEntity.badRequest().body("Username já existe");
            }
            if (userRepository.findByEmail(email).isPresent()) {
                return ResponseEntity.badRequest().body("Email já existe");
            }
            
            // Buscar ou criar o papel USER
            Role userRole = roleRepository.findByAuthority("ROLE_USER")
                    .orElseGet(() -> {
                        Role newRole = new Role();
                        newRole.setAuthority("ROLE_USER");
                        return roleRepository.save(newRole);
                    });
            
            User user = new User();
            user.setUsername(username);
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            user.getRoles().add(userRole); // Adicionar o papel ao usuário
            if (characteristic != null) {
                user.getCharacteristic().addAll(characteristic);
            }
            userRepository.save(user);
            
            return ResponseEntity.ok("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro no register: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody java.util.Map<String, String> request) {
        System.out.println("=== LOGIN CALLED ===");
        try {
            System.out.println("Request: " + request);
            
            String username = request.get("username");
            String password = request.get("password");
            
            System.out.println("Username: " + username);
            System.out.println("Password: " + (password != null ? "***" : "null"));
            
            if (username == null || password == null) {
                return ResponseEntity.badRequest().body("Username e senha são obrigatórios");
            }
            
            User user = userRepository.findByUsernameWithRoles(username)
                    .orElse(null);
            if (user == null) {
                return ResponseEntity.status(401).body("Usuário ou senha inválidos");
            }
            if (user.getPassword() == null) {
                return ResponseEntity.status(500).body("Senha do usuário não está definida");
            }
            if (!passwordEncoder.matches(password, user.getPassword())) {
                return ResponseEntity.status(401).body("Usuário ou senha inválidos");
            }
            
            String token = jwtUtil.generateToken(user.getUsername(), "ROLE_USER");
            java.util.Map<String, String> response = java.util.Map.of("access_token", token);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("Erro no login: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro interno: " + e.getMessage());
        }
    }
} 