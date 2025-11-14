package com.saurabh.demo.service;

import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class MathServiceTest {

    private MathService mathService = new MathService();

    @Test
    void testMultiply() {
        int expectedResult = 6;
        int actualResult = mathService.multiply(2, 3);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void testMultiplyZero() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                mathService.multiply(1, 0));
        Assertions.assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    void testDivide() {
        double expectedResult = 1.0;
        double actualResult = mathService.divide(2, 2);
        Assertions.assertEquals(expectedResult, actualResult, 0.0001);
    }

    @Test
    void testDivideZero() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                mathService.divide(2, 0));
        Assertions.assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    void testGenerateTable() {
        String expectedResult = "1 x 1 = 1\n" +
                "1 x 2 = 2\n" +
                "1 x 3 = 3\n" +
                "1 x 4 = 4\n" +
                "1 x 5 = 5\n";
        String actualResult = mathService.generateTable(1, 5);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void testCountUpTo() {
        String expectedResult = "1";
        String actualResult = mathService.countUpTo(1);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void testHelloworld() {
        String actualResult = mathService.helloworld();
        Assertions.assertEquals("hoshiyar", actualResult);
    }

    @Test
    void testPrintButterfly() {
        java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
        java.io.PrintStream originalOut = System.out;
        System.setOut(new java.io.PrintStream(bos));
        mathService.printButterfly(3);
        System.out.flush();
        String actualResult = bos.toString().trim();
        System.setOut(originalOut);
        String expectedResult = "* \n*" +
                " ** \n" +
                "*****\n" +
                "***\n" +
                "* \n";
        Assertions.assertEquals(expectedResult, actualResult);
    }
}