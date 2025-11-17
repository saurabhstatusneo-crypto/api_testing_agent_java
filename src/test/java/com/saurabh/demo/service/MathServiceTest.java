package com.saurabh.demo.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathServiceTest {

    private MathService mathService = new MathService();

    @Test
    public void testMultiply() {
        assertEquals(4, mathService.multiply(2, 2));
        assertEquals(12, mathService.multiply(3, 4));
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, mathService.divide(4, 2));
        assertEquals(0.75, mathService.divide(3, 4));
    }

    @Test
    public void testDivideByZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mathService.divide(4, 0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    public void testGenerateTable() {
        assertEquals("1 x 1 = 1\n2 x 1 = 2\n3 x 1 = 3\n4 x 1 = 4", mathService.generateTable(1, 4).trim());
        assertEquals("1 x 2 = 2\n2 x 2 = 4\n3 x 2 = 6\n4 x 2 = 8", mathService.generateTable(2, 4).trim());
    }

    @Test
    public void testGenerateTableEmptyResult() {
        assertEquals("", mathService.generateTable(1, 0).trim());
    }

    @Test
    public void testCountUpTo() {
        assertEquals("1 2 3 4 5", mathService.countUpTo(5).trim());
        assertEquals("1 2 3", mathService.countUpTo(3).trim());
    }

    @Test
    public void testCountUpToEmptyResult() {
        assertEquals("", mathService.countUpTo(0).trim());
    }

    @Test
    public void testHelloworld() {
        assertEquals("hoshiyar", mathService.helloworld());
    }

    @Test
    public void testPrintButterfly() {
        mathService.printButterfly(5);
        mathService.printButterfly(3);
    }

}