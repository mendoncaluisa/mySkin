package com.mySkin.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mySkin.dtos.CharacteristicDTO;
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
public class CharacteristicResourceIT {

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
        CharacteristicDTO dto = new CharacteristicDTO();
        dto.setDescription("Característica Atualizada");
        String dtoJson = objectMapper.writeValueAsString(dto);
        String descriptionExpected = dto.getDescription();

        ResultActions result =
                mockMvc.perform(
                        put("/characteristic/{id}",existingId)
                                .header("Authorization","Bearer " + token)
                                .content(dtoJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(existingId));
        result.andExpect(jsonPath("$.description").value(descriptionExpected));
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        CharacteristicDTO dto = new CharacteristicDTO();
        dto.setDescription("Característica Atualizada");
        String dtoJson = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(
                        put("/characteristic/{id}",nonExistingId)
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
                        delete("/characteristic/{id}",existingId)
                                .header("Authorization","Bearer " + token)
                );

        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        ResultActions result =
                mockMvc.perform(
                        delete("/characteristic/{id}",nonExistingId)
                                .header("Authorization","Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isNotFound());
    }

    @Test
    public void findByIdShouldReturnCharacteristicWhenIdExists() throws Exception {
        ResultActions result =
                mockMvc.perform(
                        get("/characteristic/{id}", existingId)
                                .header("Authorization","Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isOk());
        String resultJson = result.andReturn().getResponse().getContentAsString();
        System.out.println(resultJson);

        CharacteristicDTO dto = objectMapper.readValue(resultJson, CharacteristicDTO.class);

        Assertions.assertEquals(existingId, dto.getId());
    }
} 