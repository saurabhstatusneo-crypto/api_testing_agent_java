package com.saurabh.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.saurabh.demo.DemoApplication;

public class DemoApplicationTest {

    @Test
    public void testMainMethod() {
        System.out.println("Running testMainMethod...");
        // Since main method is not designed to be tested,
        // we will test that the application does not throw an exception.
        DemoApplication application = new DemoApplication();
        try {
            application.main(null);
        } catch (Exception e) {
            assertTrue(false, "Main method failed. See exception: " + e.getMessage());
        }
        System.out.println("testMainMethod passed");
    }
}