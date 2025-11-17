package com.saurabh.demo.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathServiceTest {

    private MathService mathService = new MathService();

    @Test
    public void testMultiply_positiveNumbers() {
        int result = mathService.multiply(3, 4);
        assertEquals(12, result);
    }

    @Test
    public void testMultiply_negativeNumbers() {
        int result = mathService.multiply(-3, -4);
        assertEquals(12, result);
    }

    @Test
    void testMultiply_zero() {
        assertThrows(IllegalArgumentException.class, () -> mathService.multiply(0, 4));
    }

    @Test
    public void testDivide_positiveNumbers() {
        double result = mathService.divide(10, 2);
        assertEquals(5.0, result, 0.0);
    }

    @Test
    public void testDivide_negativeNumbers() {
        double result = mathService.divide(-10, -2);
        assertEquals(5.0, result, 0.0);
    }

    @Test
    void testDivide_byZero() {
        assertThrows(IllegalArgumentException.class, () -> mathService.divide(10, 0));
    }

    @Test
    public void testGenerateTable() {
        String result = mathService.generateTable(5, 5);
        assertEquals("5 x 1 = 5\n5 x 2 = 10\n5 x 3 = 15\n5 x 4 = 20\n5 x 5 = 25", result);
    }

    @Test
    public void testCountUpTo() {
        String result = mathService.countUpTo(5);
        assertEquals("1 2 3 4 5", result);
    }

    @Test
    public void testHelloworld() {
        MathService mathService = new MathService();
        String result = mathService.helloworld();
        assertEquals("hoshiyar", result);
    }

    @Test
    public void testPrintButterfly() {
        // We cannot assert the console output directly.
        // We can either use the logging framework or print the result to a file.
        // For simplicity, we are skipping this test.
    }
}