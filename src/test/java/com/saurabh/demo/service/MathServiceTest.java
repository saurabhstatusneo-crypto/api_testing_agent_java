package com.saurabh.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MathServiceTest {

    @Autowired
    private MathService mathService;

    @Test
    void testMultiply() {
        assertEquals(6, mathService.multiply(2, 3));
    }

    @Test
    void testMultiplyZero() {
        assertEquals(0, mathService.multiply(0, 10));
    }

    @Test
    void testMultiplyNegative() {
        assertEquals(-6, mathService.multiply(-2, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, mathService.divide(4, 2), 0.01);
    }

    @Test
    void testDivideZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mathService.divide(4, 0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    void testDivideNegative() {
        assertEquals(-2.0, mathService.divide(-4, 2), 0.01);
    }

    @Test
    void testGenerateTable() {
        String table = mathService.generateTable(5, 5);
        assertEquals("5 x 1 = 5\n5 x 2 = 10\n5 x 3 = 15\n5 x 4 = 20\n5 x 5 = 25", table);
    }

    @Test
    void testGenerateTableUpToZero() {
        String table = mathService.generateTable(5, 0);
        assertEquals("", table);
    }

    @Test
    void testCountUpTo() {
        assertEquals("1 2 3 4 5", mathService.countUpTo(5));
    }

    @Test
    void testCountUpToZero() {
        assertEquals("", mathService.countUpTo(0));
    }

    @Test
    void testHelloworld() {
        assertEquals("hoshiyar", mathService.helloworld());
    }

    @Test
    void testPrintButterfly() {
        mathService.printButterfly(3);
    }

}