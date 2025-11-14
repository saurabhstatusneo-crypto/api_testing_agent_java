package com.saurabh.demo.controller;

import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MathControllerTest {

    @Mock
    private MathService mathService;

    @injectMocks
    private MathController mathController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(mathController).build();
    }

    @Test
    void testMultiplyReturnsProperResult() throws Exception {
        when(mathService.multiply(2, 3)).thenReturn(6);

        mockMvc.perform(get("/math/multiply?a=2&b=3"))
                .andExpect(status().isOk())
                .andExpect(content().string("6"));
    }

    @Test
    void testDivideReturnsProperResult() throws Exception {
        when(mathService.divide(10, 2)).thenReturn(5);

        mockMvc.perform(get("/math/divide?a=10&b=2"))
                .andExpect(status().isOk())
                .andExpect(content().string("5.0"));
    }

    @Test
    void testTableReturnsProperResult() throws Exception {
        when(mathService.generateTable(9, 10)).thenReturn("1 x 9 = 9\n2 x 9 = 18\n3 x 9 = 27\n4 x 9 = 36\n5 x 9 = 45\n6 x 9 = 54\n7 x 9 = 63\n8 x 9 = 72\n9 x 9 = 81\n10 x 9 = 90");

        mockMvc.perform(get("/math/table?number=9&upTo=10"))
                .andExpect(status().isOk())
                .andExpect(content().string("1 x 9 = 9\n2 x 9 = 18\n3 x 9 = 27\n4 x 9 = 36\n5 x 9 = 45\n6 x 9 = 54\n7 x 9 = 63\n8 x 9 = 72\n9 x 9 = 81\n10 x 9 = 90"));
    }

    @Test
    void testCountReturnsProperResult() throws Exception {
        when(mathService.countUpTo(5)).thenReturn("1, 2, 3, 4, 5");

        mockMvc.perform(get("/math/count?n=5"))
                .andExpect(status().isOk())
                .andExpect(content().string("1, 2, 3, 4, 5"));
    }

    @Nested
    class EdgeCases {

        @Test
        void testMultiplyZero() throws Exception {
            when(mathService.multiply(0, 0)).thenReturn(0);

            mockMvc.perform(get("/math/multiply?a=0&b=0"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("0"));
        }

        @Test
        void testMultiplyOne() throws Exception {
            mockMvc.perform(get("/math/multiply?a=0&b=0"))
                    .andExpect(status().isOk())
                    .andExpect(content().isEmpty());
        }

        @Test
        void testDivideByZero() throws Exception {
            mockMvc.perform(get("/math/divide?a=10&b=0"))
                    .andExpect(status().isInternalServerError());
        }
    }
}