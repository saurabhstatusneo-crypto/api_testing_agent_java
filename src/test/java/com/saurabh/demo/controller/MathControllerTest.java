package com.saurabh.demo.controller;

import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MathControllerTest {

    @Mock
    private MathService mathService;

    @InjectMocks
    private MathController mathController;

    @Test
    public void testMultiply() {
        when(mathService.multiply(anyInt(), anyInt())).thenReturn(10);
        int a = 2;
        int b = 5;
        int result = mathController.multiply(a, b);
        assertEquals(10, result);
    }

    @Test
    public void testDivide() {
        when(mathService.divide(anyInt(), anyInt())).thenReturn(2.0);
        int a = 4;
        int b = 2;
        double result = mathController.divide(a, b);
        assertEquals(2.0, result);
    }

    @Test
    public void testTable() {
        when(mathService.generateTable(anyInt(), anyInt())).thenReturn("Table of numbers");
        int number = 5;
        int upTo = 10;
        String result = mathController.table(number, upTo);
        assertEquals("Table of numbers", result);
    }

    @Test
    public void testCount() {
        when(mathService.countUpTo(anyInt())).thenReturn("Counting numbers");
        int n = 10;
        String result = mathController.count(n);
        assertEquals("Counting numbers", result);
    }
}