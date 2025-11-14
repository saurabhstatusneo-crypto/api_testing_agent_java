package com.saurabh.demo.controller;

import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
public class MathControllerTest {

    @Mock
    private MathService mathService;

    @InjectMocks
    private MathController mathController;

    private MockMvc mockMvc;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(mathController).build();
    }

    @Test
    public void givenValidInput_multiply_shouldReturnCorrectResult() throws Exception {
        int a = 5;
        int b = 5;
        int expResult = 25;
        when(mathService.multiply(a, b)).thenReturn(expResult);
        String actual = mockMvc.perform(MockMvcRequestBuilders.get("/math/multiply?a=" + a + "&b=" + b))
                .andReturn().getResponse().getContentAsString();
        assertEquals(String.valueOf(expResult), actual);
        verify(mathService).multiply(a, b);
    }

    @Test
    public void givenValidInput_divide_shouldReturnCorrectResult() throws Exception {
        int a = 10;
        int b = 2;
        double expResult = 5;
        when(mathService.divide(a, b)).thenReturn(expResult);
        String actual = mockMvc.perform(MockMvcRequestBuilders.get("/math/divide?a=" + a + "&b=" + b))
                .andReturn().getResponse().getContentAsString();
        assertEquals(String.valueOf(expResult), actual);
        verify(mathService).divide(a, b);
    }

    @Test
    public void givenValidInput_table_shouldReturnCorrectResult() throws Exception {
        int number = 5;
        int upTo = 10;
        String expResult = "5 * 1 = 5\n5 * 2 = 10\n5 * 3 = 15\n5 * 4 = 20\n5 * 5 = 25\n5 * 6 = 30\n5 * 7 = 35\n5 * 8 = 40\n5 * 9 = 45\n5 * 10 = 50";
        when(mathService.generateTable(number, upTo)).thenReturn(expResult);
        String actual = mockMvc.perform(MockMvcRequestBuilders.get("/math/table?number=" + number + "&upTo=" + upTo))
                .andReturn().getResponse().getContentAsString();
        assertEquals(expResult, actual);
        verify(mathService).generateTable(number, upTo);
    }

    @Test
    public void givenValidInput_count_shouldReturnCorrectResult() throws Exception {
        int n = 5;
        String expResult = "1\n2\n3\n4\n5";
        when(mathService.countUpTo(n)).thenReturn(expResult);
        String actual = mockMvc.perform(MockMvcRequestBuilders.get("/math/count?n=" + n))
                .andReturn().getResponse().getContentAsString();
        assertEquals(expResult, actual);
        verify(mathService).countUpTo(n);
    }
}