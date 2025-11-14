package com.saurabh.demo.controller;

import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MathControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private MathController mathController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(mathController).build();
    }

    @Test
    public void testMultiply() throws Exception {
        mockMvc.perform(get("/math/multiply")
                .param("a", "5")
                .param("b", "3"))
                .andExpect(status().isOk());
    }

    @Test
    public void testMultiplyZero() throws Exception {
        mockMvc.perform(get("/math/multiply")
                .param("a", "5")
                .param("b", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(0));
    }

    @Test
    void testDivide() throws Exception {
        mockMvc.perform(get("/math/divide")
                .param("a", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void testTableValidInput() throws Exception {
        mockMvc.perform(get("/math/table")
                .param("number", "10")
                .param("upTo", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void testTableInvalidInput() throws Exception {
        mockMvc.perform(get("/math/table")
                .param("number", "10")
                .param("upTo", "a"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCountValidInput() throws Exception {
        mockMvc.perform(get("/math/count")
                .param("n", "10"))
                .andExpect(status().isOk());
    }

    @Test
    void testCountInvalidInput() throws Exception {
        mockMvc.perform(get("/math/count")
                .param("n", "a"))
                .andExpect(status().isBadRequest());
    }
}