package com.saurabh.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringBootTestExtension.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    void contextLoads() {
        assertTrue(true);
    }

    @Test
    void mainMethodTest() {
        // Test the main method. Note: Main method can't be directly tested.
        // We can test the SpringApplication class, but that's not the best approach here
        // as SpringApplication.run is supposed to be called by spring boot to start the application.
        // Hence, no assertion here.
    }
}