package com.saurabh.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MathServiceTest {

    @InjectMocks
    private MathService mathService;

    @Test
    public void testMultiply() {
        assertEquals(6, mathService.multiply(2, 3));
        assertEquals(-12, mathService.multiply(-4, 3));
        assertEquals(7, mathService.multiply(7, 1));
        assertEquals(-12, mathService.multiply(-6, 2));
    }

    @Test
    public void testMultiplyByZeroShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mathService.multiply(12, 0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    public void testDivide() {
        assertEquals(6.0d, mathService.divide(12, 2), 1e-6);
        assertEquals(12.0d, mathService.divide(48, 4), 1e-6);
        assertEquals(-3.0d, mathService.divide(-12, 4), 1e-6);
    }

    @Test
    public void testDivideByZeroShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mathService.divide(12, 0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    public void testGenerateTable() {
        String expected = "1 x 1 = 1\n" +
                "1 x 2 = 2\n" +
                "1 x 3 = 3\n" +
                "1 x 4 = 4\n" +
                "1 x 5 = 5";

        assertEquals(expected, mathService.generateTable(1, 5));
    }

    @Test
    public void testCountUpTo() {
        String expected = "1 2 3 4 5 6 7 8 9 10";
        assertEquals(expected, mathService.countUpTo(10));
    }

    @Test
    public void testHelloworld() {
        String expected = "hoshiyar";
        String actual = mathService.helloworld();
        assertEquals(expected, actual);
    }

    @Test
    public void testPrintButterflyEven() {
        mathService.printButterfly(5);
    }

    @Test
    public void testPrintButterflyOdd() {
        mathService.printButterfly(7);
    }
}