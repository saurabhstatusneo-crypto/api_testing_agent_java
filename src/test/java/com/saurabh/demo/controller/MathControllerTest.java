package com.saurabh.demo.controller;

import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MathController.class)
public class MathControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MathController mathController;

    @Test
    public void testMultiply() throws Exception {
        // Test multiplication
        mockMvc.perform(get("/math/multiply?a=5&b=10"))
                .andExpect(jsonPath("$.result", is(50)));
    }

    @Test
    public void testDivide() throws Exception {
        // Test division
        mockMvc.perform(get("/math/divide?a=20&b=4"))
                .andExpect(jsonPath("$.result", is(5.0)));
    }

    @Test
    public void testTable() throws Exception {
        // Test table generator
        mockMvc.perform(get("/math/table?number=5&upTo=10"))
                .andExpect(jsonPath("$.table", is("5*0=0\n5*1=5\n5*2=10\n5*3=15\n5*4=20\n5*5=25\n5*6=30\n5*7=35\n5*8=40\n5*9=45\n5*10=50")));
    }

    @Test
    public void testCount() throws Exception {
        // Test counting
        mockMvc.perform(get("/math/count?n=5"))
                .andExpect(jsonPath("$.count", is("1, 2, 3, 4, 5")));
    }
}