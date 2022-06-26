package org.javaboy.storage.service;

import org.javaboy.storage.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Service
public class StorageService {
    @Autowired
    StorageMapper storageMapper;

    public boolean deduct(String productId, Integer count) {
        System.out.println("productId = " + productId);
        System.out.println("count = " + count);
        int c = storageMapper.getCountByProductId(productId);
        if (c < count) {
            throw new RuntimeException("库存不足，扣库存失败");
        }
        int deduct = storageMapper.deduct(productId, count);
        return deduct == 1;
    }
}
