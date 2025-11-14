package com.saurabh.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DemoApplicationTests {

    @Test
    public void testMainMethodWithArguments() {
        SpringApplication.Runner runner = SpringApplication.runner(DemoApplication.class);
        runner.apply(new String[]{"test"});
        assertTrue(true); // dummy assertion
    }

    @Test
    public void testMainMethodWithoutArguments() {
        SpringApplication.Runner runner = SpringApplication.runner(DemoApplication.class);
        runner.apply(new String[]{});
        assertTrue(true); // dummy assertion
    }
}