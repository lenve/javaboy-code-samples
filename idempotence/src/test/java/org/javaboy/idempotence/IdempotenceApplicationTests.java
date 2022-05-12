package org.javaboy.idempotence;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class IdempotenceApplicationTests {

    @Test
    void contextLoads() {
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.add("Authorization", "123");
        //HttpEntity
        HttpEntity<String> requestEntity = new HttpEntity<String>("ffffff", requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        //post
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/hello", requestEntity, String.class);
        System.out.println(responseEntity.getBody());
    }

}
