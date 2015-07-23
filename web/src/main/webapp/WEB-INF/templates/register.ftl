<!DOCTYPE html>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Registration</title>
</head>
<body>
    <@form.form commandName="regForm" id="regForm"  autocomplete="off" action="/register" method="POST">
        <p>ФИО</p>

        <p><@form.errors path="name"/></p>

        <p><@form.input path="name" id="name" autocomplete="off" placeholder="ФИО" cssClass="form-control"/></p>

        <p>Логин</p>

        <p><@form.errors path="login"/></p>

        <p><@form.input path="login" id="login" autocomplete="off" placeholder="Логин" cssClass="form-control"/></p>

        <div id="generate_login" class="dbl btn-link">Сгенерировать логин</div>

        <p>Группа</p>

        <p><@form.errors path="academicGroup"/></p>

        <p><@form.input path="academicGroup" id="group"  autocomplete="off" placeholder="Группа" cssClass="form-control"/></p>


        <p>Ваш пароль</p>

        <p><@form.errors path="password"/></p>

        <p><@form.password path="password" id="password" autocomplete="off" cssClass="form-control"/></p>


        <div class="form-group">

            <button id="submit" name="submit" class="edit_action">Зарегистрироваться</button>

        </div>
    </@form.form>
</body>