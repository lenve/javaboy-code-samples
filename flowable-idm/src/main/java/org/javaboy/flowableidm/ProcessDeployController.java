package org.javaboy.flowableidm;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.javaboy.flowableidm.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

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
public class ProcessDeployController {

    @Autowired
    RepositoryService repositoryService;

    @PostMapping("/deploy")
    public RespBean deploy(MultipartFile[] files) throws IOException {
        System.out.println(new Date());
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment()
                .category("javaboy的工作流分类")
                .name("javaboy的工作流名称")
                .key("javaboy的工作流key666");
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            deploymentBuilder.addInputStream(file.getOriginalFilename(), file.getInputStream());
        }
        Deployment deployment = deploymentBuilder
                .deploy();
        return RespBean.ok("部署成功", deployment.getId());
    }
}
