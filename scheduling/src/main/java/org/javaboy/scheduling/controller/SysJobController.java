package org.javaboy.scheduling.controller;

import org.javaboy.scheduling.model.RespBean;
import org.javaboy.scheduling.model.SysJob;
import org.javaboy.scheduling.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("/jobs")
public class SysJobController {
    @Autowired
    SysJobService sysJobService;

    @GetMapping("/")
    public List<SysJob> getAllJobs() {
        return sysJobService.getAllJobs();
    }


    @PutMapping("/")
    public RespBean updateSysJob(@RequestBody SysJob sysJob) {
        Boolean result = sysJobService.updateSysJob(sysJob);
        if (result) {
            return RespBean.ok("作业更新成功");
        }
        return RespBean.error("作业更新失败");
    }
}
