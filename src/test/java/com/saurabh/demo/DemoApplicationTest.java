package com.saurabh.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoApplicationTest {

    @Test
    public void testMainClassDoesNotThrowException() {
        // Test the main application context creation
        assertDoesNotThrow(DemoApplication::main);
    }

    @Test
    public void testMainClassThrowsException() {
        // Test the main application context creation with null args
        assertThrows(NullPointerException.class, () -> DemoApplication.main(null));
    }

    @Test
    public void testSpringApplication() {
        // Test SpringApplication.run()
        assertDoesNotThrow(() -> SpringApplication.run(DemoApplication.class, null));
    }
}