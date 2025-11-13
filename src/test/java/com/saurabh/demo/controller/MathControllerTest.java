package com.saurabh.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MathControllerTest {

    @InjectMocks
    private MathController mathController;

    @Mock
    private MathService mathService;

    // 1. Multiplication
    @Test
    public void testMultiplyPositiveNumbers() {
        // when
        Mockito.when(mathService.multiply(4, 5)).thenReturn(20);
        int a = 4;
        int b = 5;
        // then
        int result = mathController.multiply(a, b);
        assertEquals(a * b, result);
    }

    @Test
    public void testMultiplyNegativeNumbers() {
        // when
        Mockito.when(mathService.multiply(-4, 5)).thenReturn(-20);
        int a = -4;
        int b = 5;
        // then
        int result = mathController.multiply(a, b);
        assertEquals(a * b, result);
    }

    @Test
    public void testMultiplyZero() {
        // when
        Mockito.when(mathService.multiply(0, 5)).thenReturn(0);
        int a = 0;
        int b = 5;
        // then
        int result = mathController.multiply(a, b);
        assertEquals(a * b, result);
    }

    // 2. Division
    @Test
    public void testDividePositiveNumbers() {
        // when
        Mockito.when(mathService.divide(12, 3)).thenReturn(4.0);
        int a = 12;
        int b = 3;
        // then
        double result = mathController.divide(a, b);
        assertEquals(4.0, result, 0.01);
    }

    @Test
    public void testDivideNegativeNumbers() {
        // when
        Mockito.when(mathService.divide(-12, 3)).thenReturn(-4.0);
        int a = -12;
        int b = 3;
        // then
        double result = mathController.divide(a, b);
        assertEquals(-4.0, result, 0.01);
    }

    @Test
    public void testDivideByZero() {
        // when
        try {
            mathController.divide(12, 0);
            assert false : "Expected MathException when dividing by zero";
        } catch (ArithmeticException e) {
            // expected
        }
    }

    // 3. Table
    @Test
    public void testTable() {
        // when
        Mockito.when(mathService.generateTable(5, 10)).thenReturn("Table of 5: 5 10 15 20 25");
        int number = 5;
        int upTo = 10;
        // then
        String result = mathController.table(number, upTo);
        assertEquals("Table of 5: 5 10 15 20 25", result);
    }

    // 4. Counting
    @Test
    public void testCountPositiveNumber() {
        // when
        Mockito.when(mathService.countUpTo(10)).thenReturn("Starting from 1, count up to 10 is 10");
        int n = 10;
        // then
        String result = mathController.count(n);
        assertEquals("Starting from 1, count up to 10 is 10", result);
    }

    @Test
    public void testCountZero() {
        // when
        Mockito.when(mathService.countUpTo(0)).thenReturn("Starting from 1, count up to 0 is 0");
        int n = 0;
        // then
        String result = mathController.count(n);
        assertEquals("Starting from 1, count up to 0 is 0", result);
    }

    @Test
    public void testCountNegativeNumber() {
        // when
        try {
            mathController.count(-3);
            assert false : "Expected IllegalArgumentException when counting a negative number";
        } catch (IllegalArgumentException e) {
            // expected
        }
    }
}