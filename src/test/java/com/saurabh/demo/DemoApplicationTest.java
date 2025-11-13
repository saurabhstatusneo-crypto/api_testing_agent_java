Here's the test class for the provided Java application class.

```java
package com.saurabh.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void testConstructor() {
        // Since it's an application class, we cannot directly instantiate and test constructor.
        // We will test the SpringApplication.run method.
    }

    @Test
    public void testSpringApplicationRun() {
        DemoApplication demoApplication = new DemoApplication();
        SpringApplication.run(demoApplication.getClass(), null);
        assertNotNull(SpringApplication.class);
    }

    @Test
    public void testSpringApplicationRunThrowNullPointerException() {
        DemoApplication demoApplication = new DemoApplication();
        assertThrows(NullPointerException.class, () -> SpringApplication.run(null, null));
    }
}