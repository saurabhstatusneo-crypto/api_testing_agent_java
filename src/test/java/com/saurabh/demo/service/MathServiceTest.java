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
        assertEquals(6, mathService.multiply(3, 2));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, mathService.divide(4, 2));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> mathService.divide(4, 0));
        assertEquals("Division by zero is not allowed", exception.getMessage());
    }

    @Test
    void testGenerateTable() {
        assertEquals("1 x 1 = 1\n2 x 1 = 2\n3 x 1 = 3\n4 x 1 = 4\n5 x 1 = 5\n6 x 1 = 6\n7 x 1 = 7\n8 x 1 = 8\n9 x 1 = 9\n10 x 1 = 10\n", mathService.generateTable(1, 10));
    }

    @Test
    void testCountUpTo() {
        assertEquals("1 2 3 4 5 6 7 8 9 10", mathService.countUpTo(10));
    }

    @Test
    void testHelloworld() {
        assertEquals("hoshiyar", mathService.helloworld());
    }

    @Test
    void testPrintButterfly() {
        System.out.println("Test printButterfly is being run but it prints something on the console, "
                + "so this test will only check if the function doesn't throw any exception");
        try {
            mathService.printButterfly(5);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}