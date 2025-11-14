package com.saurabh.demo.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MathServiceTest {

    @Autowired
    private MathService mathService;

    @Test
    public void testMultiply() {
        assertEquals(4, mathService.multiply(2, 2));
        assertEquals(6, mathService.multiply(3, 2));
        assertEquals(-6, mathService.multiply(-3, 2));
    }

    @Test
    public void testDivide_Normal() {
        assertEquals(2.5, mathService.divide(5, 2), 0.1);
        assertEquals(1.5, mathService.divide(6, 4), 0.1);
    }

    @Test
    public void testDivide_Zero() {
        assertThrows(IllegalArgumentException.class, () -> mathService.divide(10, 0));
    }

    @Test
    public void testGenerateTable() {
        String expected = "2 x 1 = 2\n" +
                "2 x 2 = 4\n" +
                "2 x 3 = 6\n" +
                "2 x 4 = 8\n" +
                "2 x 5 = 10\n";
        assertEquals(expected, mathService.generateTable(2, 5));
    }

    @Test
    public void testCountUpTo() {
        assertEquals("1 2 3 4 55", mathService.countUpTo(5));
        assertEquals("", mathService.countUpTo(0));
    }

    @Test
    public void testHelloworld() {
        assertEquals("hoshiyar", mathService.helloworld());
    }

    @Test
    void testPrintButterfly() {
        mathService.printButterfly(3);
    }
}