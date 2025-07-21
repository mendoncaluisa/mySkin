package com.mySkin.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mySkin.dtos.IngredientDTO;
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
public class IngredientResourceIT {

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
        IngredientDTO dto = new IngredientDTO();
        dto.setNome("Ingrediente Atualizado");
        dto.setDescricao("Descrição atualizada");
        dto.setFuncao("Função atualizada");
        dto.setOrigem("Origem atualizada");
        dto.setSeguro(true);
        dto.setComedogenico(false);
        dto.setPotIrritante(false);
        dto.setEstrutura("Estrutura atualizada");
        dto.setFuncaoQuimica("Função química atualizada");
        dto.setLigacaoQuimica("Ligação química atualizada");
        String dtoJson = objectMapper.writeValueAsString(dto);
        String nomeExpected = dto.getNome();
        String descricaoExpected = dto.getDescricao();

        ResultActions result =
                mockMvc.perform(
                        put("/ingredient/{id}",existingId)
                                .header("Authorization","Bearer " + token)
                                .content(dtoJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(existingId));
        result.andExpect(jsonPath("$.nome").value(nomeExpected));
        result.andExpect(jsonPath("$.descricao").value(descricaoExpected));
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        IngredientDTO dto = new IngredientDTO();
        dto.setNome("Ingrediente Atualizado");
        dto.setDescricao("Descrição atualizada");
        dto.setFuncao("Função atualizada");
        dto.setOrigem("Origem atualizada");
        dto.setSeguro(true);
        dto.setComedogenico(false);
        dto.setPotIrritante(false);
        dto.setEstrutura("Estrutura atualizada");
        dto.setFuncaoQuimica("Função química atualizada");
        dto.setLigacaoQuimica("Ligação química atualizada");
        String dtoJson = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(
                        put("/ingredient/{id}",nonExistingId)
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
                        delete("/ingredient/{id}",existingId)
                                .header("Authorization","Bearer " + token)
                );

        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        ResultActions result =
                mockMvc.perform(
                        delete("/ingredient/{id}",nonExistingId)
                                .header("Authorization","Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isNotFound());
    }

    @Test
    public void findByIdShouldReturnIngredientWhenIdExists() throws Exception {
        ResultActions result =
                mockMvc.perform(
                        get("/ingredient/{id}", existingId)
                                .header("Authorization","Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isOk());
        String resultJson = result.andReturn().getResponse().getContentAsString();
        System.out.println(resultJson);

        IngredientDTO dto = objectMapper.readValue(resultJson, IngredientDTO.class);

        Assertions.assertEquals(existingId, dto.getId());
    }
} 