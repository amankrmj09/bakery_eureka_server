package com.shah_s;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "eureka.client.register-with-eureka=false",
        "eureka.client.fetch-registry=false"
})
class BakeryEurekaServerApplicationTests {

    @Test
    void contextLoads() {
        // Test that Spring context loads successfully
    }
}
