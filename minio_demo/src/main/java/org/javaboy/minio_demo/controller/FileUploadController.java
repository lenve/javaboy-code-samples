package org.javaboy.minio_demo.controller;

import org.javaboy.minio_demo.config.UploadResponse;
import org.javaboy.minio_demo.config.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@RestController
public class FileUploadController {
    @Autowired
    MinioUtil minioUtil;

    @PostMapping("/upload")
    public String fileUpload(MultipartFile file) throws Exception {
        UploadResponse bucket01 = minioUtil.uploadFile(file, "bucket01");
        System.out.println("bucket01.getMinIoUrl() = " + bucket01.getMinIoUrl());
        System.out.println("bucket01.getNginxUrl() = " + bucket01.getNginxUrl());
        return bucket01.getMinIoUrl();
    }
}
