package com.saurabh.demo.controller;

import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MathControllerTest {

    @Mock
    private MathService mathService;

    @InjectMocks
    private MathController mathController;

    @Test
    public void testMultiply() {
        // Arrange
        int a = 5;
        int b = 3;
        int result = 15;
        when(mathService.multiply(a, b)).thenReturn(result);

        // Act
        int actualResult = mathController.multiply(a, b);

        // Assert
        assertEquals(result, actualResult);
    }

    @Test
    public void testDivide() {
        // Arrange
        int a = 10;
        int b = 2;
        double result = 5.0;
        when(mathService.divide(a, b)).thenReturn(result);

        // Act
        double actualResult = mathController.divide(a, b);

        // Assert
        assertEquals(result, actualResult);
    }

    @Test
    public void testTable() {
        // Arrange
        int number = 3;
        int upTo = 10;
        String result = "3 * 1 = 3\n" +
                "3 * 2 = 6\n" +
                "3 * 3 = 9\n" +
                "3 * 4 = 12\n" +
                "3 * 5 = 15\n" +
                "3 * 6 = 18\n" +
                "3 * 7 = 21\n" +
                "3 * 8 = 24\n" +
                "3 * 9 = 27\n" +
                "3 * 10 = 30";
        when(mathService.generateTable(number, upTo)).thenReturn(result);

        // Act
        String actualResult = mathController.table(number, upTo);

        // Assert
        assertEquals(result, actualResult);
    }

    @Test
    public void testCount() {
        // Arrange
        int n = 5;
        String result = "1\n" +
                "2\n" +
                "3\n" +
                "4\n" +
                "5";
        when(mathService.countUpTo(n)).thenReturn(result);

        // Act
        String actualResult = mathController.count(n);

        // Assert
        assertEquals(result, actualResult);
    }

    @Test
    public void testDefaultValueOfUpToInTable() {
        // Arrange
        int number = 3;
        int defaultUpTo = 10;
        String expected = "3 * 1 = 3\n" +
                "3 * 2 = 6\n" +
                "3 * 3 = 9\n" +
                "3 * 4 = 12\n" +
                "3 * 5 = 15\n" +
                "3 * 6 = 18\n" +
                "3 * 7 = 21\n" +
                "3 * 8 = 24\n" +
                "3 * 9 = 27\n" +
                "3 * 10 = 30";
        String result = "3 * 1 = 3\n" +
                "3 * 2 = 6\n" +
                "3 * 3 = 9\n" +
                "3 * 4 = 12\n" +
                "3 * 5 = 15\n" +
                "3 * 6 = 18\n" +
                "3 * 7 = 21\n" +
                "3 * 8 = 24\n" +
                "3 * 9 = 27\n" +
                "3 * 10 = 30";
        when(mathService.generateTable(number, defaultUpTo)).thenReturn(result);

        // Act
        String actualResult = mathController.table(number, defaultUpTo);

        // Assert
        assertEquals(result, actualResult);
    }
}