package org.javaboy.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.xml.soap.MimeHeader;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailApplicationTests {

    @Autowired
    JavaMailSender javaMailSender;

    @Test
    public void sendSimpleMail() {
        //1.构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        //2.设置邮件主题
        message.setSubject("这是一封测试邮件");
        //3.设置邮件发送者
        message.setFrom("1510161612@qq.com");
        //4. 设置邮件接收者，可以有多个接收者
        message.setTo("2506762755@qq.com");
        //5.设置邮件抄送人，可以有多个抄送人
        message.setCc("371820637@qq.com");
        //6.设置隐秘抄送人，可以有多个
        message.setBcc("1406134098@qq.com");
        //7.设置邮件发送日期
        message.setSentDate(new Date());
        //8. 设置邮件的正文
        message.setText("这是测试邮件的正文");
        //9. 发送邮件
        javaMailSender.send(message);
    }


    @Test
    public void sendAttachFileMail() throws MessagingException {
        //1. 构建邮件对象，注意，这里要通过 javaMailSender 来获取一个复杂邮件对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //2. MimeMessageHelper 是一个邮件配置的辅助工具类，true 表示构建一个 multipart message 类型的邮件
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        //3. 针对工具类，配置邮件发送的基本信息
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("1510161612@qq.com");
        helper.setTo("2506762755@qq.com");
        helper.setCc("371820637@qq.com");
        helper.setBcc("1406134098@qq.com");
        helper.setSentDate(new Date());
        helper.setText("这是测试邮件的正文");
        //4. 添加邮件附件
        helper.addAttachment("javaboy.jpg",new File("C:\\Users\\sang\\Downloads\\javaboy.png"));
        //5. 发送邮件
        javaMailSender.send(mimeMessage);
    }

    @Test
    public void sendImgResMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("1510161612@qq.com");
        helper.setTo("2506762755@qq.com");
        helper.setCc("371820637@qq.com");
        helper.setBcc("1406134098@qq.com");
        helper.setSentDate(new Date());
        helper.setText("<p>hello 大家好，这是一封测试邮件，这封邮件包含两种图片，分别如下</p><p>第一张图片：</p><img src='cid:p01'/><p>第二张图片：</p><img src='cid:p02'/>",true);
        helper.addInline("p01",new FileSystemResource(new File("C:\\Users\\sang\\Downloads\\javaboy.png")));
        helper.addInline("p02",new FileSystemResource(new File("C:\\Users\\sang\\Downloads\\javaboy2.png")));
        javaMailSender.send(mimeMessage);
    }

    @Test
    public void sendFreemarkerMail() throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("1510161612@qq.com");
        helper.setTo("2506762755@qq.com");
        helper.setCc("371820637@qq.com");
        helper.setBcc("1406134098@qq.com");
        helper.setSentDate(new Date());
        //构建 Freemarker 的基本配置
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
        // 配置模板位置
        ClassLoader loader = MailApplication.class.getClassLoader();
        configuration.setClassLoaderForTemplateLoading(loader, "templates");
        //加载模板
        Template template = configuration.getTemplate("mail.ftl");
        User user = new User();
        user.setUsername("javaboy");
        user.setNum(1);
        user.setSalary((double) 99999);
        StringWriter out = new StringWriter();
        //模板渲染，渲染的结果将被保存到 out 中 ，将out 中的 html 字符串发送即可
        template.process(user, out);
        helper.setText(out.toString(),true);
        javaMailSender.send(mimeMessage);
    }

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void sendThymeleafMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject("这是一封测试邮件");
        helper.setFrom("1510161612@qq.com");
        helper.setTo("2506762755@qq.com");
        helper.setCc("371820637@qq.com");
        helper.setBcc("1406134098@qq.com");
        helper.setSentDate(new Date());
        Context context = new Context();
        context.setVariable("username", "javaboy");
        context.setVariable("num","000001");
        context.setVariable("salary", "99999");
        String process = templateEngine.process("mail.html", context);
        helper.setText(process,true);
        javaMailSender.send(mimeMessage);
    }
}
