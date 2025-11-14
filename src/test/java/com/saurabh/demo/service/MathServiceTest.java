package com.saurabh.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MathService.class)
public class MathServiceTest {

    @Autowired
    private MathService mathService;

    @Test
    public void testMultiply() {
        int result = mathService.multiply(5, 3);
        assertEquals(15, result);
    }

    @Test
    public void testDivide() {
        double result = mathService.divide(20, 4);
        assertEquals(5.0, result, 0.0);
    }

    @Test
    public void testDivideByZero() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mathService.divide(20, 0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    public void testGenerateTable() {
        String result = mathService.generateTable(5, 5);
        assertTrue(result.contains("5 x 1 = 5"));
        assertTrue(result.contains("5 x 2 = 10"));
        assertTrue(result.contains("5 x 3 = 15"));
        assertTrue(result.contains("5 x 4 = 20"));
        assertTrue(result.contains("5 x 5 = 25"));
    }

    @Test
    public void testCountUpTo() {
        String result = mathService.countUpTo(5);
        assertEquals("1 2 3 4 5", result);
    }

    @Test
    public void testHelloworld() {
        String result = mathService.helloworld();
        assertNotNull(result);
    }

    // printButterfly method cannot be tested using assertEquals or assertion methods because it prints
    // the output. The following test will check if the method call doesn't throw an exception
    @Test
    public void testPrintButterfly() {
        mathService.printButterfly(5);
    }
}