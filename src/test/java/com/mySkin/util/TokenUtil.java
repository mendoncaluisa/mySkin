package com.mySkin.util;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

//essa classe inteira faz o login pra mim e pega o token gerado
@Component
public class TokenUtil {

    public String obtainAccessToken(MockMvc mockMvc, String username, String password) throws Exception {
        String jsonBody = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);

        ResultActions result = mockMvc
                .perform(post("/api/auth/login")
                        .content(jsonBody)
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
}