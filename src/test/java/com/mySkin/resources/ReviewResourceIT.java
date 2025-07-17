package com.mySkin.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mySkin.dtos.ReviewDTO;
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
public class ReviewResourceIT {

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
        ReviewDTO dto = new ReviewDTO();
        dto.setPositive("Positivo atualizado");
        dto.setNegative("Negativo atualizado");
        dto.setRate(4.5f);
        dto.setUser_id(1L); // ajuste conforme necessário
        dto.setProduct_id(1L); // ajuste conforme necessário
        String dtoJson = objectMapper.writeValueAsString(dto);
        String positiveExpected = dto.getPositive();
        String negativeExpected = dto.getNegative();

        ResultActions result =
                mockMvc.perform(
                        put("/review/{id}",existingId)
                                .header("Authorization","Bearer " + token)
                                .content(dtoJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(existingId));
        result.andExpect(jsonPath("$.positive").value(positiveExpected));
        result.andExpect(jsonPath("$.negative").value(negativeExpected));
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        ReviewDTO dto = new ReviewDTO();
        dto.setPositive("Positivo atualizado");
        dto.setNegative("Negativo atualizado");
        dto.setRate(4.5f);
        dto.setUser_id(1L); // ajuste conforme necessário
        dto.setProduct_id(1L); // ajuste conforme necessário
        String dtoJson = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(
                        put("/review/{id}",nonExistingId)
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
                        delete("/review/{id}",existingId)
                                .header("Authorization","Bearer " + token)
                );

        result.andExpect(status().isNoContent());
    }

    @Test
    public void deleteShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {
        ResultActions result =
                mockMvc.perform(
                        delete("/review/{id}",nonExistingId)
                                .header("Authorization","Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isNotFound());
    }

    @Test
    public void findByIdShouldReturnReviewWhenIdExists() throws Exception {
        ResultActions result =
                mockMvc.perform(
                        get("/review/{id}", existingId)
                                .header("Authorization","Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isOk());
        String resultJson = result.andReturn().getResponse().getContentAsString();
        System.out.println(resultJson);

        ReviewDTO dto = objectMapper.readValue(resultJson, ReviewDTO.class);

        Assertions.assertEquals(existingId, dto.getId());
    }
} 