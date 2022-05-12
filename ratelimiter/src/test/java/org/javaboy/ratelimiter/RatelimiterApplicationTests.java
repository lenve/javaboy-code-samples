package org.javaboy.ratelimiter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class RatelimiterApplicationTests {

    @Test
    void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 10; i++) {
            String s = restTemplate.getForObject("http://localhost:8080/hello", String.class);
            System.out.println(s);
        }
    }

}
