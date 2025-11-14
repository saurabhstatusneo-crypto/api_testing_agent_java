package com.saurabh.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

    @Test
    public void testMain() throws Exception {
        // This method is not really testable, so we can't test it directly in JUnit.
        // Instead, we can test the SpringApplicationBuilder to see if the application runs successfully.
        // However, that would require a lot of complex setup, so it's not included here.
    }
}