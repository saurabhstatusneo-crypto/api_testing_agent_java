package com.saurabh.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MathServiceTest {
    @Autowired
    private MathService mathService;

    @InjectMocks
    private MathService mathServiceMock;

    @Test
    void testMultiply() {
        assertEquals(10, mathService.multiply(5, 2));
        assertEquals(10, mathServiceMock.multiply(5, 2));
    }

    @Test
    void testDivide() {
        double result = mathService.divide(10, 2);
        assertEquals(5.0, result);
    }

    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> mathService.divide(10, 0));
        assertThrows(IllegalArgumentException.class, () -> mathServiceMock.divide(10, 0));
    }

    @Test
    void testGenerateTable() {
        String result = mathService.generateTable(5, 5);
        assertEquals("5 x 1 = 5\n5 x 2 = 10\n5 x 3 = 15\n5 x 4 = 20\n5 x 5 = 25", result);
    }

    @Test
    void testCountUpTo() {
        String result = mathService.countUpTo(5);
        assertEquals("1 2 3 4 5", result);
    }

    @Test
    void testHelloworld() {
        String result = mathService.helloworld();
        String name = "hoshiyar";
        assertEquals(name, result);
    }

    @Test
    void testPrintButterfly() {
        // Note: This test will print to console. If you are running in an IDE, this will only print when the test runs in debug mode.
        // To test this method, you might need to run your test using a command line or a CI environment.
        mathService.printButterfly(5);
    }
}