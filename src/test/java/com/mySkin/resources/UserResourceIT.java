package com.mySkin.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mySkin.dtos.UserDTO;
import com.mySkin.util.TokenUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserResourceIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TokenUtil tokenUtil;
    private String username;
    private String password;
    private String token;

    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        nonExistingId = 2000L;

        username = "Poliana";
        password = "123456";
        token = tokenUtil.obtainAccessToken(mockMvc,username,password);
    }

    @Test
    public void updateShouldReturnDtoWhenIdExists() throws Exception{
        UserDTO dto = new UserDTO();
        dto.setUsername("usuarioAtualizado");
        dto.setName("Nome Atualizado");
        dto.setEmail("atualizado@email.com");
        // Adicione outros campos obrigatórios do seu UserDTO aqui
        String dtoJson = objectMapper.writeValueAsString(dto);
        String usernameExpected = dto.getUsername();
        String nameExpected = dto.getName();

        ResultActions result =
                mockMvc.perform(
                        put("/user/{id}",existingId)
                                .header("Authorization","Bearer " + token)
                                .content(dtoJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(existingId));
        result.andExpect(jsonPath("$.username").value(usernameExpected));
        result.andExpect(jsonPath("$.name").value(nameExpected));
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        UserDTO dto = new UserDTO();
        dto.setUsername("usuarioAtualizado");
        dto.setName("Nome Atualizado");
        dto.setEmail("atualizado@email.com");
        // Adicione outros campos obrigatórios do seu UserDTO aqui
        String dtoJson = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(
                        put("/user/{id}",nonExistingId)
                                .header("Authorization","Bearer " + token)
                                .content(dtoJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isNotFound());
    }

    @Test
    public void deleteShouldReturnNoContentWhenIdExists() throws Exception {
        ResultActions result =
                mockMvc.perform(
                        delete("/user/{id}",existingId)
                                .header("Authorization","Bearer " + token)
                );

        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        ResultActions result =
                mockMvc.perform(
                        delete("/user/{id}",nonExistingId)
                                .header("Authorization","Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isNotFound());
    }

    @Test
    public void findByIdShouldReturnUserWhenIdExists() throws Exception {
        ResultActions result =
                mockMvc.perform(
                        get("/user/{id}", existingId)
                                .header("Authorization","Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isOk());
        String resultJson = result.andReturn().getResponse().getContentAsString();
        System.out.println(resultJson);

        UserDTO dto = objectMapper.readValue(resultJson, UserDTO.class);

        Assertions.assertEquals(existingId, dto.getId());
    }
} 