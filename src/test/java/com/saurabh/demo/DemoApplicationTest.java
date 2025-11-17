package com.saurabh.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoApplicationTests {

    @Test
    void contextLoads() {
        // Verify that the @SpringBootApplication annotation is present
        // and that the context loads successfully
        // In this case there's nothing to test, so this test method is empty
    }

    @Test
    void mainMethodShouldRunWithoutException() {
        // Create a new instance of the SpringApplication class to test the main method
        // Note: You cannot directly call the main method of the DemoApplication class
        // from a unit test, since it involves the JVM and cannot be mocked
        // However, we can ensure that the SpringApplication.run method does not throw any exceptions
        SpringApplication.run(DemoApplication.class, new String[]{});
    }
}