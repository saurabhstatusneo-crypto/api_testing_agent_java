package com.saurabh.demo.test;

import io.github.bonigarcia=wdspringintegrationtest.annotation.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MathServiceTest {

    @Autowired
    @InjectMocks
    private MathService mathService;

    @Test
    public void testMultiply() {
        assertEquals(10, mathService.multiply(2, 5));
    }

    @Test
    public void testMultiplyNegativeNumbers() {
        assertEquals(-10, mathService.multiply(-2, 5));
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, mathService.divide(4, 2));
    }

    @Test
    public void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> mathService.divide(4, 0));
    }

    @Test
    public void testGenerateTable() {
        assertEquals("1 x 1 = 1\n2 x 1 = 2\n3 x 1 = 3\n4 x 1 = 4\n5 x 1 = 5\n6 x 1 = 6\n7 x 1 = 7\n8 x 1 = 8\n9 x 1 = 9\n10 x 1 = 10", mathService.generateTable(1, 10));
    }

    @Test
    public void testCountUpTo() {
        assertEquals("1 2 3 4 5", mathService.countUpTo(5));
    }

    @Test
    public void testHelloworld() {
        assertEquals("hoshiyar", mathService.helloworld());
    }

    @Test
    public void testPrintButterfly() {
        mathService.printButterfly(5);
    }

    @Test
    void testHelloworldReturn() {
        assertEquals("hoshiyar",mathService.helloworld());

    }
}