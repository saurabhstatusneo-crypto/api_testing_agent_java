package com.saurabh.demo.controller;

import com.saurabh.demo.controller.MathController;
import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MathControllerTest {

    private static final int NUMBER = 5;
    private static final int FIRST_ARGUMENT = 5;
    private static final int SECOND_ARGUMENT = 5;

    @Mock
    private MathService mathService;

    @InjectMocks
    private MathController mathController;

    private MockMvc mockMvc;

    public MathControllerTest() {
        mockMvc = MockMvcBuilders.standaloneSetup(mathController).build();
    }

    @Test
    void testMultiply() throws Exception {
        when(mathService.multiply(anyInt(), anyInt())).thenReturn(FIRST_ARGUMENT * SECOND_ARGUMENT);

        mockMvc.perform(get("/math/multiply?a=" + FIRST_ARGUMENT + "&b=" + SECOND_ARGUMENT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("value", is(FIRST_ARGUMENT * SECOND_ARGUMENT)));
    }

    @Test
    void testDivide() throws Exception {
        when(mathService.divide(anyInt(), anyInt())).thenReturn((double) FIRST_ARGUMENT / SECOND_ARGUMENT);

        mockMvc.perform(get("/math/divide?a=" + FIRST_ARGUMENT + "&b=" + SECOND_ARGUMENT))
                .andExpect(status().isOk())
                .andExpect(jsonPath("value", is((double) FIRST_ARGUMENT / SECOND_ARGUMENT)));
    }

    @Test
    void testTable() throws Exception {
        String table = "Number\tSquare\tCube" + "\n" +
                "5\t25\t125";

        when(mathService.generateTable(anyInt(), anyInt())).thenReturn(table);

        mockMvc.perform(get("/math/table?number=" + NUMBER + "&upTo=7"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("value", is(table)));
    }

    @Test
    void testCount() throws Exception {
        String result = "We counted up to 5!" + "\n" +
                "We are counting numbers" +
                "We are counting to 5" +
                "Counting numbers from 1" +
                "Current count is 3" +
                "Current count is 4" +
                "Current count is 5" +
                "We counted up to 5!";

        when(mathService.countUpTo(anyInt())).thenReturn(result);

        mockMvc.perform(get("/math/count?n=" + NUMBER))
                .andExpect(status().isOk())
                .andExpect(jsonPath("value", is(result)));
    }
}