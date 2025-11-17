package com.saurabh.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void testSpringApplicationRun() {
        SpringApplication.run(DemoApplication.class, new String[0]);
        // As the main method of the DemoApplication class is public static void main(String[] args),
        // there's no way to test it directly. But we can verify that it runs successfully.
    }

    @Test
    public void testMainMethodNoArgs() {
        assertThrows(Exception.class, () -> SpringApplication.run(DemoApplication.class, null));
    }

    @Test
    public void testMainMethodSingleArg() {
        assertThrows(Exception.class, () -> SpringApplication.run(DemoApplication.class, new String[] {"Single Arg"}));
    }

    @Test
    public void testMainMethodMultipleArgs() {
        assertThrows(Exception.class, () -> SpringApplication.run(DemoApplication.class, new String[] {"Arg1", "Arg2", "Arg3"}));
    }
}