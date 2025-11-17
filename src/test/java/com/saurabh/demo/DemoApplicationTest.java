package com.saurabh.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoApplicationTest {

    @Test
    public void testMain() {
        // Spring Boot tests require a runner to launch the application context
        System.getProperty("spring.main.web-application-type", "");
        System.exit(SpringApplication.exit(SpringApplication.run(DemoApplication.class)));
    }
}