package com.saurabh.demo.controller;

import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MathControllerTest {

    @InjectMocks
    private MathController mathController;

    @Mock
    private MathService mathService;

    private MockMvc mockMvc;

    @Autowired
    public MathControllerTest() {
        mockMvc = MockMvcBuilders.standaloneSetup(mathController).build();
    }

    @Test
    public void testMultiply() throws Exception {
        // Given
        int a = 5;
        int b = 10;
        given(mathService.multiply(a, b)).willReturn(50);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/math/multiply?a=" + a + "&b=" + b))
                .andExpect(status().isOk())
                .andExpect(content().string("50"));
    }

    @Test
    @Disabled("Disabled due to division by zero issue")
    public void testDivide() throws Exception {
        // Given
        int a = 20;
        int b = 4;
        given(mathService.divide(a, b)).willReturn(5.0);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/math/divide?a=" + a + "&b=" + b))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }

    @Test
    @Disabled("Disabled due to division by zero issue")
    public void testDivideByZero() throws Exception {
        // Given
        int a = 20;
        int b = 0;
        given(mathService.divide(a, b)).willReturn(-1.0); // Returning a value to indicate division by zero exception was caught

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/math/divide?a=" + a + "&b=" + b))
                .andExpect(status().isOk())
                .andExpect(content().string("-1.0"));
    }

    @Test
    public void testTable() throws Exception {
        // Given
        int number = 5;
        int upTo = 15;
        given(mathService.generateTable(number, upTo)).willReturn("5*1=5\n5*2=10\n5*3=15\n5*4=20\n5*5=25\n5*6=30\n5*7=35\n5*8=40\n5*9=45\n5*10=50\n5*11=55\n5*12=60\n5*13=65\n5*14=70");

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/math/table?number=" + number + "&upTo=" + upTo))
                .andExpect(status().isOk())
                .andExpect(content().string("5*1=5\n5*2=10\n5*3=15\n5*4=20\n5*5=25\n5*6=30\n5*7=35\n5*8=40\n5*9=45\n5*10=50\n5*11=55\n5*12=60\n5*13=65\n5*14=70"));
    }

    @Test
    public void testCount() throws Exception {
        // Given
        int n = 10;
        given(mathService.countUpTo(n)).willReturn("1, 2, 3, 4, 5, 6, 7, 8, 9, 10");

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/math/count?" + "n=" + n))
                .andExpect(status().isOk())
                .andExpect(content().string("1, 2, 3, 4, 5, 6, 7, 8, 9, 10"));
    }
}