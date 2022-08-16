package org.javaboy.flowable02.model;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
public class RespBean {
    private Integer status;
    private String msg;
    private Object data;

    public static RespBean ok(String msg, Object data) {
        return new RespBean(200, msg, data);
    }


    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }


    public static RespBean error(String msg, Object data) {
        return new RespBean(500, msg, data);
    }


    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    private RespBean() {
    }

    private RespBean(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
