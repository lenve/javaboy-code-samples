package org.javaboy.data_scope;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class DataScopeApplicationTests {

    @Test
    void test01() {
        String format = String.format("aaa%sbbb%s", "1", "2");
        System.out.println("format = " + format);
    }

    @Test
    void contextLoads() {
        FastAutoGenerator.create("jdbc:mysql:///test06?serverTimezone=Asia/Shanghai&useSSL=false", "root", "123")
                .globalConfig(builder -> {
                    builder.author("javaboy") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/sang/blog/javaboy-code-samples/data_scope/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("org.javaboy.data_scope") // 设置父包名
//                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/sang/blog/javaboy-code-samples/data_scope/src/main/resources/mapper/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_user_role","sys_user","sys_role_dept","sys_role","sys_dept") // 设置需要生成的表名
                            .addTablePrefix("sys_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

}
