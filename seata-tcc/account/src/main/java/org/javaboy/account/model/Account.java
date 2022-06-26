package org.javaboy.account.model;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
public class Account {
    private Integer id;
    private String userId;
    private Double money;
    private Double freezeMoney;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", money=" + money +
                ", freezeMoney=" + freezeMoney +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(Double freezeMoney) {
        this.freezeMoney = freezeMoney;
    }
}
