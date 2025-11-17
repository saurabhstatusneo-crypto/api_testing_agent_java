package com.saurabh.demo.controller;

import com.saurabh.demo.service.MathService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
public class MathControllerTest {

    @Mock
    private MathService mathService;

    @InjectMocks
    private MathController mathController;

    @BeforeEach
    public void setUp() {
        // Initialize the controller with a mocked service
        mathController = new MathController(mathService);
        // Disable Spring's autowiring to avoid unnecessary dependencies
        ((MathController) mathController).mathService = mathService;
    }

    @Test
    public void testMultiply() {
        // Arrange
        int number1 = 5;
        int number2 = 3;
        int expectedResult = 15;

        when(mathService.multiply(anyInt(), anyInt())).thenReturn(expectedResult);

        // Act
        ResponseEntity<Integer> response = ResponseEntity.ok(mathController.multiply(number1, number2));

        // Assert
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testDivide() {
        // Arrange
        int number1 = 8;
        int number2 = 2;
        double expectedResult = 4.0;

        when(mathService.divide(anyInt(), anyInt())).thenReturn(expectedResult);

        // Act
        ResponseEntity<Double> response = ResponseEntity.ok(mathController.divide(number1, number2));

        // Assert
        assertEquals(expectedResult, response.getBody(), 0.01);
    }

    @Test
    public void testTable() {
        // Arrange
        int number = 2;
        int upTo = 5;
        String expectedResult = "2 x 1 = 2\n2 x 2 = 4\n2 x 3 = 6\n2 x 4 = 8\n2 x 5 = 10";

        when(mathService.generateTable(anyInt(), anyInt())).thenReturn(expectedResult);

        // Act
        ResponseEntity<String> response = ResponseEntity.ok(mathController.table(number, upTo));

        // Assert
        assertEquals(expectedResult, response.getBody());
    }

    @Test
    public void testCount() {
        // Arrange
        int n = 5;
        String expectedResult = "1. 0 is not a positive number\n2. 1 is a positive number\n3. 2 is a positive number\n4. 3 is a positive number\n5. 4 is a positive number\n6. 5 is a positive number";

        when(mathService.countUpTo(anyInt())).thenReturn(expectedResult);

        // Act
        ResponseEntity<String> response = ResponseEntity.ok(mathController.count(n));

        // Assert
        assertEquals(expectedResult, response.getBody());
    }
}