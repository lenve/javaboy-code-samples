package org.javaboy.jpa_one_to_many;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@Data
@Table(name = "t_student")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sid;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cid")
    private Clazz clazz;
    @Column(insertable = false,updatable = false)
    private Integer cid;
}
