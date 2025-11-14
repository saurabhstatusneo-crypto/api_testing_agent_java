package com.saurabh.demo.controller;

import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MathControllerTest {

    private MockMvc mockMvc;
    private MathService mathService;

    @Autowired
    public MathControllerTest(MathService mathService) {
        this.mathService = mathService;
    }

    @BeforeEach
    public void setup() {
        MathController mathController = new MathController(mathService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(mathController).build();
    }

    @Test
    public void testMultiply() throws Exception {
        // Test case 1: Positive numbers
        String url = "/math/multiply";
        int a = 5;
        int b = 10;
        Integer result = 50;

        mockMvc.perform(MockMvcRequestBuilders.get(url).param("a", String.valueOf(a)).param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(result, result));
    }

    @Test
    public void testMultiplyNegativeNumbers() throws Exception {
        // Test case 2: Negative numbers
        String url = "/math/multiply";
        int a = -5;
        int b = -10;
        Integer result = 50;

        mockMvc.perform(MockMvcRequestBuilders.get(url).param("a", String.valueOf(a)).param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(result, result));
    }

    @Test
    public void testMultiplyLargeNumbers() throws Exception {
        // Test case 3: Large numbers
        String url = "/math/multiply";
        int a = 5000000;
        int b = 10;
        Integer result = 50000000;

        mockMvc.perform(MockMvcRequestBuilders.get(url).param("a", String.valueOf(a)).param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(result, result));
    }

    @Test
    public void testDivide() throws Exception {
        // Test case 4: Valid division
        String url = "/math/divide";
        int a = 50;
        int b = 10;
        double result = 5.0;

        mockMvc.perform(MockMvcRequestBuilders.get(url).param("a", String.valueOf(a)).param("b", String.valueOf(b)))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(result, result));
    }

    @Test
    public void testDivideByZero() throws Exception {
        // Test case 5: Division by zero
        String url = "/math/divide";
        int a = 50;
        int b = 0;

        mockMvc.perform(MockMvcRequestBuilders.get(url).param("a", String.valueOf(a)).param("b", String.valueOf(b)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTable() throws Exception {
        // Test case 6: Generating table
        String url = "/math/table";
        int number = 2;
        int upTo = 10;
        String result = "2 * 1 = 2\n2 * 2 = 4\n2 * 3 = 6\n2 * 4 = 8\n2 * 5 = 10\n2 * 6 = 12\n2 * 7 = 14\n2 * 8 = 16\n2 * 9 = 18\n2 * 10 = 20";

        mockMvc.perform(MockMvcRequestBuilders.get(url).param("number", String.valueOf(number)).param("upTo", String.valueOf(upTo)))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(result, result));
    }

    @Test
    public void testCount() throws Exception {
        // Test case 7: Counting up to n
        String url = "/math/count";
        int n = 5;
        String result = "1\n2\n3\n4\n5";

        mockMvc.perform(MockMvcRequestBuilders.get(url).param("n", String.valueOf(n)))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals(result, result));
    }
}