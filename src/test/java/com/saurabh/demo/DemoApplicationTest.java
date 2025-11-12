package com.saurabh.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class DemoApplicationTest {

    @Test
    void testMain() {
        DemoApplication.main(new String[]{});
    }

    @Test
    void testApplicationClass() {
        Class<?> clazz = DemoApplication.class;
        assertTrue(clazz.isAnnotationPresent(SpringBootApplication.class));
    }
}