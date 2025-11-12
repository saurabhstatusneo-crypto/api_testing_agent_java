package com.saurabh.demo.controller;

import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MathControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MathService mathService;

    @Mock
    private Constructor<?> testClassConstructor;

    @Mock
    private Method testMethod;

    @Autowired
    private MathController mathController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mathController).build();
    }

    @Test
    void shouldReturnResultForMultiplication() throws Exception {
        // Arrange
        int a = 5;
        int b = 3;
        when(mathService.multiply(a, b)).thenReturn(15);
        // Act
        String response = mockMvc.perform(get("/math/multiply?a=" + a + "&b=" + b))
                .andExpect(status().isOk())
                .andExpect(content().string("15"))
                .andReturn().getResponse().getContentAsString();
        // Assert
        assertEquals("15", response);
    }

    @Test
    void shouldReturnResultForDivision() throws Exception {
        // Arrange
        int a = 15;
        int b = 3;
        when(mathService.divide(a, b)).thenReturn(5.0);
        // Act
        String response = mockMvc.perform(get("/math/divide?a=" + a + "&b=" + b))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"))
                .andReturn().getResponse().getContentAsString();
        // Assert
        assertEquals("5.0", response);
    }

    @Test
    void shouldReturnTableForNumber() throws Exception {
        // Arrange
        int a = 5;
        when(mathService.generateTable(a, 10)).thenReturn("5, 10, 15, 20, 25, 30, 35, 40, 45, 50");
        // Act
        String response = mockMvc.perform(get("/math/table?number=" + a + "&upTo=10"))
                .andExpect(status().isOk())
                .andExpect(content().string("5, 10, 15, 20, 25, 30, 35, 40, 45, 50"))
                .andReturn().getResponse().getContentAsString();
        // Assert
        assertEquals("5, 10, 15, 20, 25, 30, 35, 40, 45, 50", response);
    }

    @Test
    void shouldCount() throws Exception {
        // Arrange
        int num = 10;
        when(mathService.countUpTo(num)).thenReturn("0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10");
        // Act
        String response = mockMvc.perform(get("/math/count?n=" + num))
                .andExpect(status().isOk())
                .andExpect(content().string("0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10"))
                .andReturn().getResponse().getContentAsString();
        // Assert
        assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10", response);
    }

    @Test
    void shouldReturn400WhenNumberIsZero() throws Exception {
        // Arrange
        int a = 0;
        // Act and Assert
        mockMvc.perform(get("/math/multiply?a=" + a + "&b=1"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/math/divide?a=" + a + "&b=1"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/math/table?number=" + a + "&upTo=10"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/math/count?n=" + a))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenBIsZero() throws Exception {
        // Arrange
        int a = 5;
        // Act and Assert
        mockMvc.perform(get("/math/multiply?a=" + a + "&b=0"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/math/divide?a=" + a + "&b=0"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/math/table?number=" + a + "&upTo=10"))
                .andExpect(status().isInternalServerError());

        mockMvc.perform(get("/math/count?n=" + a))
                .andExpect(status().isInternalServerError());
    }

}