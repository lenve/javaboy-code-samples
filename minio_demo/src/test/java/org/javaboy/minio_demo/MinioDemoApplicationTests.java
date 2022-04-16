package org.javaboy.minio_demo;

import io.minio.messages.Bucket;
import org.javaboy.minio_demo.config.MinioUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MinioDemoApplicationTests {

    @Autowired
    MinioUtil minioUtil;

    @Test
    void contextLoads() throws Exception {
        List<Bucket> allBuckets = minioUtil.getAllBuckets();
        for (Bucket bucket : allBuckets) {
            System.out.println("bucket.name() = " + bucket.name());
        }
        minioUtil.createBucket("bucket02");
        minioUtil.removeBucket("bucket01");

    }

}
