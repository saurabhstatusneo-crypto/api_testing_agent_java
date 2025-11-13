package com.saurabh.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MathServiceTest {

    @Autowired
    private MathService mathService;

    @Test
    public void givenTwoNumbers_whenMultiply_thenReturnProduct() {
        int result = mathService.multiply(5, 10);
        assertEquals(50, result);
    }

    @Test
    public void givenTwoNumbersOneZero_whenDivide_thenReturnIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> mathService.divide(5, 0));
    }

    @Test
    public void givenNumberAndUpTo_whenGenerateTable_thenReturnTable() {
        String result = mathService.generateTable(5, 10);
        assertTrue(result.contains("5 x 1 = 5"), "First row is missing");
        assertTrue(result.contains("5 x 10 = 50"), "Last row is missing");
    }

    @Test
    public void givenNumber_whenCountUpTo_thenReturnCount() {
        String result = mathService.countUpTo(10);
        assertEquals("1 2 3 4 5 6 7 8 9 10", result);
    }

    @Test
    public void givenNoInput_whenHelloworld_thenReturnDefaultString() {
        String name = mathService.helloworld();
        assertEquals("hoshiyar", name);
    }

    @Test
    public void givenInput_whenHelloworld_thenReturnDefaultString() {
        mathService.helloworld();
        // No assertions for a side-effect method like println()
    }

    @Test
    public void givenNumber_whenPrintButterfly_thenReturnButterfly() {
        mathService.printButterfly(5);
        // Verify the output directly
    }

    @Test
    public void givenMultipleNumbers_whenMultiply_thenReturnProduct() {
        int result1 = mathService.multiply(5, 10);
        int result2 = mathService.multiply(10, 20);
        assertEquals(50, result1);
        assertEquals(200, result2);
    }
}