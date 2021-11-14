<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: sang
  Date: 2021/11/6
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登录成功</h1>

<hr>
<shiro:guest>
    欢迎【游客】访问，当用户未登录时，显示这个标签中的内容:guest
</shiro:guest>
<hr>
<shiro:user>
    用户登录之后展示该标签中的内容，无论用户是通过用户名/密码登录还是通过 RememberMe 登录:user <br>
    <shiro:principal/>
    <br>
    <a href="/sd/hello">hello</a>
</shiro:user>
<hr>
<shiro:authenticated>
    用户通过用户名/密码的方式登录之后展示该标签中的内容:authenticated
</shiro:authenticated>
<hr>

<shiro:notAuthenticated>
    用户没有通过用户名/密码的方式登录，就会展示这个标签中的内容:notAuthenticated
</shiro:notAuthenticated>
<hr>
<shiro:lacksRole name="admin">
    用户不具备admin
</shiro:lacksRole>
<hr>
<shiro:hasRole name="admin">
    <a href="/sd/admin">admin</a>
    用户具备 admin角色
</shiro:hasRole>
</body>
</html>
