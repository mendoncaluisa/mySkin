package com.mySkin.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mySkin.dtos.ProductDTO;
import com.mySkin.util.Factory;
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
public class ProductResourceIT {

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

        ProductDTO dto = Factory.createProductDTO();
        String dtoJson = objectMapper.writeValueAsString(dto);
        System.out.println(dtoJson);
        String nameExpected = dto.getName();
        String descriptionExpected = dto.getDescription();

        ResultActions result =
                mockMvc.perform( //esse objeto mockMvc é o que faz as requisições
                        put("/product/{id}",existingId) //o {id} é substituído pelo existingId
                                .header("Authorization","Bearer " + token)
                                .content(dtoJson) //o update recebe um json com os dados que vou atualizar e é nesse mét0do que passamos o json
                                .contentType(MediaType.APPLICATION_JSON) //isso é o que estou mandando
                                .accept(MediaType.APPLICATION_JSON) //isso é o que estou retornando
                );

        result.andExpect(status().isOk()); //verifica se o status da requisição deu certo
        result.andExpect(jsonPath("$.id").value(existingId));
        result.andExpect(jsonPath("$.name").value(nameExpected));
        result.andExpect(jsonPath("$.description").value(descriptionExpected));
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {

        ProductDTO dto = Factory.createProductDTO();
        String dtoJson = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(
                        put("/product/{id}",nonExistingId)
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
                mockMvc.perform( //esse objeto mockMvc é o que faz as requisições
                        delete("/product/{id}",existingId)
                                .header("Authorization","Bearer " + token)//o {id} é substituído pelo existingId
                );

        result.andExpect(status().isNoContent()); //verifica se o status da requisição deu certo
    }

    @Test
    public void deleteShouldReturnNoFoundWhenIdDoesNotExists() throws Exception {


        ResultActions result =
                mockMvc.perform(
                        delete("/product/{id}",nonExistingId)
                                .header("Authorization","Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isNotFound());

    }

    @Test
    public void findByIdShouldReturnProductWhenIdExists() throws Exception {

        ResultActions result =
                mockMvc.perform(
                        get("/product/{id}", existingId)
                                .header("Authorization","Bearer " + token)
                                .accept(MediaType.APPLICATION_JSON)
                );

        result.andExpect(status().isOk());
        String resultJson = result.andReturn().getResponse().getContentAsString();
        System.out.println(resultJson);

        ProductDTO dto = objectMapper.readValue(resultJson, ProductDTO.class);

        Assertions.assertEquals(existingId, dto.getId());

    }

}
