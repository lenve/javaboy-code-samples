package org.javaboy.scheduling.dao;

import org.javaboy.scheduling.model.SysJob;
import org.springframework.data.jpa.repository.JpaRepository;

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
public interface SysJobRepository extends JpaRepository<SysJob,Integer> {
    List<SysJob> findAllByStatus(Integer status);
}
