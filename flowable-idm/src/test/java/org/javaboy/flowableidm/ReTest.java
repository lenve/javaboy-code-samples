package org.javaboy.flowableidm;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@SpringBootTest
public class ReTest {
    @Autowired
    RepositoryService repositoryService;

    private static final Logger logger = LoggerFactory.getLogger(ReTest.class);

    @Test
    void test09() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition pd : list) {
            repositoryService.activateProcessDefinitionById(pd.getId(), true, null);
        }
    }

    @Test
    void test08() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition pd : list) {
            repositoryService.suspendProcessDefinitionById(pd.getId(), true, null);
        }
    }

    @Test
    void test07() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition pd : list) {
            repositoryService.activateProcessDefinitionById(pd.getId());
        }
    }

    @Test
    void test06() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition pd : list) {
            repositoryService.suspendProcessDefinitionById(pd.getId());
        }
    }

    @Test
    void test05() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
        for (ProcessDefinition processDefinition : list) {
            String id = processDefinition.getId();
            boolean suspended = repositoryService.isProcessDefinitionSuspended(id);
            if (suspended) {
                logger.info("流程定义 {} 已挂起",processDefinition.getName());
            }else{
                logger.info("流程定义 {} 未挂起",processDefinition.getName());
            }
        }
    }

    @Test
    void test04() {
        List<ProcessDefinition> list = repositoryService.createNativeProcessDefinitionQuery()
                .sql("SELECT RES.* from ACT_RE_PROCDEF RES WHERE RES.KEY_ = #{key} order by RES.ID_ asc")
                .parameter("key", "javaboy_submit_an_expense_account").list();
        for (ProcessDefinition pd : list) {
            logger.info("id:{};key:{};version:{};", pd.getId(), pd.getKey(), pd.getVersion());
        }
    }

    @Test
    void test03() {
        Deployment deployment = repositoryService.createNativeDeploymentQuery().sql("SELECT RES.* from ACT_RE_DEPLOYMENT RES WHERE RES.KEY_ = #{key} and RES.DEPLOY_TIME_ = (select max(DEPLOY_TIME_) from ACT_RE_DEPLOYMENT where KEY_ = RES.KEY_) order by RES.ID_ asc").parameter("key", "javaboy的工作流key").singleResult();
        logger.info("id:{}；key:{}", deployment.getId(), deployment.getKey());
    }

    @Test
    void test02() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey("javaboy_submit_an_expense_account").list();
        for (ProcessDefinition pd : list) {
            logger.info("id:{};key:{};version:{};", pd.getId(), pd.getKey(), pd.getVersion());
        }
    }

    @Test
    void test01() throws IOException {
        Deployment deployment = repositoryService.createDeploymentQuery().deploymentKey("javaboy的工作流key").latest().singleResult();
        logger.info("id:{}；key:{}", deployment.getId(), deployment.getKey());
    }
}
