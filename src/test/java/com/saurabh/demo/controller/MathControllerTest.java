package com.saurabh.demo.controller;

import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(MockitoExtension.class)
public class MathControllerTest {

    @Mock
    private MathService mathService;

    @InjectMocks
    private MathController mathController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(mathController).build();
    }

    @Test
    void testMultiply() throws Exception {
        when(mathService.multiply(10, 20)).thenReturn(200);
        mockMvc.perform(get("/math/multiply?a=10&b=20"))
                .andExpect(status().isOk())
                .andExpect(content().string("200"));
    }

    @Test
    void testDivide() throws Exception {
        when(mathService.divide(20, 4)).thenReturn(5.0);
        mockMvc.perform(get("/math/divide?a=20&b=4"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }

    @Test
    void testTableWithDefaultUpTo() throws Exception {
        when(mathService.generateTable(10, 10)).thenReturn("First 10 numbers for 10:");
        mockMvc.perform(get("/math/table?number=10"))
                .andExpect(status().isOk())
                .andExpect(content().string("First 10 numbers for 10:"));
    }

    @Test
    void testTableWithUpToProvided() throws Exception {
        when(mathService.generateTable(10, 20)).thenReturn("First 20 numbers for 10:");
        mockMvc.perform(get("/math/table?number=10&upTo=20"))
                .andExpect(status().isOk())
                .andExpect(content().string("First 20 numbers for 10:"));
    }

    @Test
    void testCount() throws Exception {
        when(mathService.countUpTo(10)).thenReturn("Count up to 10 is 10!");
        mockMvc.perform(get("/math/count?n=10"))
                .andExpect(status().isOk())
                .andExpect(content().string("Count up to 10 is 10!"));
    }
}