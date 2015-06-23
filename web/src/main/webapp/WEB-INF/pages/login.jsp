<%--
  Created by IntelliJ IDEA.
  User: Александр
  Date: 20.02.2015
  Time: 0:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<div class="login_form">
  <h1>Авторизация</h1>

  <div id="login-tab-content">
    <form class="login-form" action="/login" method="post">
      <#if error??>
        <div class="alert alert-danger" role="alert">Неверные имя пользователя и пароль</div>
      </#if>
      <input type="text" class="form-control" name="username" id="username" autocomplete="off"
             placeholder="Логин">
      <input type="password" class="form-control" name="password" id="password" autocomplete="off"
             placeholder="Пароль">

      <input type="submit" class="button" value="Войти">
    </form>
</div>
</div>
</body>
</html>
