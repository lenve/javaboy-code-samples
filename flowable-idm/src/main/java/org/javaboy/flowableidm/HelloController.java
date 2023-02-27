package org.javaboy.flowableidm;

import org.flowable.cmmn.engine.configurator.impl.process.DefaultProcessInstanceService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private RuntimeService runtimeService;

    @GetMapping("/test01")
    public void test01() {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("UserTaskDemo");
        logger.info("id:{},activityId:{}", pi.getId(), pi.getActivityId());
    }
}
