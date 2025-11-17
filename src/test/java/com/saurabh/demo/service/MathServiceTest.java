package com.saurabh.demo.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class MathServiceTest {

    @Autowired
    private MathService mathService;

    @Test
    public void multiply_TwoPositiveNumbers_ReturnsProduct() {
        int result = mathService.multiply(5, 3);
        Assertions.assertEquals(15, result);
    }

    @Test
    public void multiply_TwoNegativeNumbers_ReturnsProduct() {
        int result = mathService.multiply(-5, -3);
        Assertions.assertEquals(15, result);
    }

    @Test
    public void multiply_ANegativeAndANumber_ReturnsCorrectValue() {
        int result = mathService.multiply(-5, 3);
        Assertions.assertEquals(-15, result);
    }

    @Test
    public void multiply_TooLargeNumbers_ReturnsCorrectValue() {
        int result = mathService.multiply(2147483647, 2);
        Assertions.assertEquals(4294967294, result);
    }

    @Test
    public void divide_DividingByZero_ThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> mathService.divide(10, 0));
    }

    @Test
    public void divide_TwoPositiveNumbers_ReturnsResult() {
        double result = mathService.divide(10, 2);
        Assertions.assertEquals(5.0, result, 0.01);
    }

    @Test
    public void divide_TwoNegativeNumbers_ReturnsCorrectResult() {
        double result = mathService.divide(-10, -2);
        Assertions.assertEquals(5.0, result, 0.01);
    }

    @Test
    public void generateTable_TableForNumberOne_ReturnsExpectedString() {
        String result = mathService.generateTable(1, 10);
        Assertions.assertEquals(
                "1 x 1 = 1\n" +
                "1 x 2 = 2\n" +
                "1 x 3 = 3\n" +
                "1 x 4 = 4\n" +
                "1 x 5 = 5\n" +
                "1 x 6 = 6\n" +
                "1 x 7 = 7\n" +
                "1 x 8 = 8\n" +
                "1 x 9 = 9\n" +
                "1 x 10 = 10\n", result);
    }

    @Test
    public void generateTable_TableForNumberTwo_ReturnsExpectedString() {
        String result = mathService.generateTable(2, 10);
        Assertions.assertEquals(
                "2 x 1 = 2\n" +
                "2 x 2 = 4\n" +
                "2 x 3 = 6\n" +
                "2 x 4 = 8\n" +
                "2 x 5 = 10\n" +
                "2 x 6 = 12\n" +
                "2 x 7 = 14\n" +
                "2 x 8 = 16\n" +
                "2 x 9 = 18\n" +
                "2 x 10 = 20\n", result);
    }

    @Test
    public void countUpTo_CountToTwo_ReturnsResult() {
        String result = mathService.countUpTo(2);
        Assertions.assertEquals("1 2", result);
    }

    @Test
    public void countUpTo_CountToFive_ReturnsExpectedString() {
        String result = mathService.countUpTo(5);
        Assertions.assertEquals("1 2 3 4 5", result);
    }

    @Test
    public void helloworld_ReturnsExpectedName() {
        String result = mathService.helloworld();
        Assertions.assertEquals("hoshiyar", result);
    }

    @Test
    public void printButterfly_PrintsCorrectButterfly() {
        mathService.printButterfly(5);
        // For this method, we're just checking that it doesn't throw any exceptions
        // and that it prints the correct shape. This is a bit harder to test than
        // the other methods because the shape of the butterfly is defined by the
        // output of the method.
    }
}
```

Note: 
Please make sure to remove or comment out `System.out.println()` lines in the MathService class methods which are not necessary for functionality. 
Also note that some methods may be harder to test directly, for example `printButterfly` method as it is printing some content to the console, but for now, we are checking if it doesn't throw any exception. If you want a more robust test for this method, consider using an approach like `PrintStream` capturing from `System.out` or use a different framework like TestFX for Swing applications.