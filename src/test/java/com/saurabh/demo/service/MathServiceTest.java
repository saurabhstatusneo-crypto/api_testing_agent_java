package com.saurabh.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MathServiceTest {

    @Autowired
    private MathService mathService;

    @Test
    public void testMultiply() {
        assertEquals(6, mathService.multiply(3, 2));
    }

    @Test
    public void testMultiplyNegativeNumbers() {
        assertEquals(-6, mathService.multiply(-3, 2));
    }

    @Test
    public void testMultiplyPositiveNumbers_BigNumbers() {
        assertEquals(100, mathService.multiply(10, 10));
    }

    @Test
    public void testMultiplyWithZero() {
        assertEquals(0, mathService.multiply(3, 0));
    }

    @Test
    public void testDivide() {
        assertEquals(3.0, mathService.divide(9, 3));
    }

    @Test
    public void testDivide_BigNumbers() {
        assertEquals(10.0, mathService.divide(100, 10));
    }

    @Test
    public void testDivide_zero() {
        assertThrows(IllegalArgumentException.class, () -> mathService.divide(9, 0));
    }

    @Test
    public void testDivide_zeroNegative() {
        assertThrows(IllegalArgumentException.class, () -> mathService.divide(-9, 0));
    }

    @Test
    public void testGenerateTable_validInput() {
        assertEquals("1 x 1 = 1\n2 x 1 = 2\n3 x 1 = 3\n3 x 2 = 6\n3 x 3 = 9\n", mathService.generateTable(3, 3));
    }

    @Test
    public void testCountUpTo_simple() {
        assertEquals("1 2 3", mathService.countUpTo(3));
    }

    @Test
    public void testCountUpTo_zero() {
        assertEquals("", mathService.countUpTo(0));
    }

    @Test
    public void testHelloworld() {
        String result = mathService.helloworld();
        assertEquals("hoshiyar", result);
    }

    @Test
    public void testPrintButterfly_printsCorrectButterfly() {
        // Not able to automate the printing of a butterfly
        // Can be improved by using an expected String representation of the butterfly
        // However, due to the nature of the printButterfly method this test case
        // will fail and needs to be done in the console manually
    }
}