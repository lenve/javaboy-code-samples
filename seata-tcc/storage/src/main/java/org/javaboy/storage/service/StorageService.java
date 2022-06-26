package org.javaboy.storage.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.spring.annotation.GlobalTransactional;
import org.javaboy.storage.mapper.StorageMapper;
import org.javaboy.storage.model.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.net.Inet4Address;

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

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    @Autowired
    StorageMapper storageMapper;

    /**
     * 预扣库存
     *
     * @param productId
     * @param count
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean prepareDeduct(String productId, Integer count) {
        Storage storage = storageMapper.getStorageByProductId(productId);
        if (storage == null) {
            throw new RuntimeException("商品不存在");
        }
        if (storage.getCount() < count) {
            throw new RuntimeException("库存不足，预扣库存失败");
        }
        storage.setFreezeCount(storage.getFreezeCount() + count);
        storage.setCount(storage.getCount() - count);
        int i = storageMapper.updateStorage(storage);
        logger.info("{} 商品库存冻结 {} 个", productId, count);
        return i == 1;
    }

    /**
     * 扣库存
     *
     * @param actionContext
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean commitDeduct(BusinessActionContext actionContext) {
        String productId = (String) actionContext.getActionContext("productId");
        Integer count = (Integer) actionContext.getActionContext("count");
        Storage storage = storageMapper.getStorageByProductId(productId);
        if (storage.getFreezeCount() < count) {
            throw new RuntimeException("库存不足，扣库存失败");
        }
        storage.setFreezeCount(storage.getFreezeCount() - count);
        int i = storageMapper.updateStorage(storage);
        logger.info("{} 商品库存扣除 {} 个", productId, count);
        return i == 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean rollbackDeduct(BusinessActionContext actionContext) {
        String productId = (String) actionContext.getActionContext("productId");
        Integer count = (Integer) actionContext.getActionContext("count");
        Storage storage = storageMapper.getStorageByProductId(productId);
        if (storage.getFreezeCount() >= count) {
            storage.setFreezeCount(storage.getFreezeCount() - count);
            storage.setCount(storage.getCount() + count);
            int i = storageMapper.updateStorage(storage);
            logger.info("{} 商品释放库存 {} 个", productId, count);
            return i == 1;
        }
        //说明 prepare 阶段就没有冻结
        return true;
    }
}
