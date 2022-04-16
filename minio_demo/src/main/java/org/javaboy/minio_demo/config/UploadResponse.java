package org.javaboy.minio_demo.config;

/**
 * @author 江南一点雨
 * @微信公众号 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.javaboy.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
public class UploadResponse {
    private String minIoUrl;

    private String nginxUrl;

    public UploadResponse() {
    }

    public UploadResponse(String minIoUrl, String nginxUrl) {
        this.minIoUrl = minIoUrl;
        this.nginxUrl = nginxUrl;
    }

    public String getMinIoUrl() {
        return minIoUrl;
    }

    public void setMinIoUrl(String minIoUrl) {
        this.minIoUrl = minIoUrl;
    }

    public String getNginxUrl() {
        return nginxUrl;
    }

    public void setNginxUrl(String nginxUrl) {
        this.nginxUrl = nginxUrl;
    }
}
