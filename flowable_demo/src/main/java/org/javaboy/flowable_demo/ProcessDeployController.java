package org.javaboy.flowable_demo;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */

/**
 * 这个是我自定义的流程部署接口
 */
@RestController
public class ProcessDeployController {
    //RepositoryService 这个实体类可以用来操作 ACT_RE_XXX 这种表
    @Autowired
    RepositoryService repositoryService;

    /**
     * 这个就是我的流程部署接口，流程部署将来要上传一个文件，这个文件就是流程的 XML 文件
     *
     * @param file
     * @param tenantId 这个是租户 id，用来区分这个流程是属于哪一个租户的
     * @return
     */
    @PostMapping("/deploy")
    public RespBean deploy(MultipartFile[] files) throws IOException {
        DeploymentBuilder builder = repositoryService
                //开始流程部署的构建
                .createDeployment()
                .name("JAVABOY的工作流")//ACT_RE_DEPLOYMENT 表中的 NAME_ 属性
                .category("我的流程分类")//ACT_RE_DEPLOYMENT 表中的 CATEGORY_ 属性
                .key("我的自定义的工作流的 KEY");
//                .tenantId(tenantId)
        //也可以用这个方法代替 addInputStream，但是注意，这个需要我们自己先去解析 IO 流程，将 XML 文件解析为一个字符串，然后就可以调用这个方法进行部署了
//                .addString()
        //设置文件的输入流程，将来通过这个输入流自动去读取 XML 文件
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            builder.addInputStream(file.getOriginalFilename(), file.getInputStream());
        }
        //完成项目的部署
        Deployment deployment = builder.deploy();
        return RespBean.ok("部署成功", deployment.getId());
    }
}
