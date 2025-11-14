package com.saurabh.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoApplicationTest {

    @Test
    void mainShouldRunWithoutException() {
        try {
            SpringApplication.run(DemoApplication.class, new String[]{});
        } catch (Exception e) {
            assertEquals(true, false, "main method should not throw an exception");
        }
    }

    @Test
    void mainShouldRunWithCorrectClass() {
        SpringApplication.run(DemoApplication.class, new String[]{});
    }
}