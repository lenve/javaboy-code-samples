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
@Entity
@Table(name = "t_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;
    private String province;
    private String city;
    private String area;
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sid",referencedColumnName = "sid")
    private School school;
    @Column(insertable = false,updatable = false)
    private Integer sid;
}
