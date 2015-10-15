<#include "mainTemplate.ftl">
<#macro m_body>
<div class="login_form">
<#--Auth-->
    <h1>Авторизация</h1>

    <div id="login-tab-content">
        <form class="login-form" action="/web/login" method="post">
            <#if error??>
                <div class="error">
                    <i class="icon mdi-alert-error"></i>
                    Неверные имя пользователя и пароль
                </div>
            </#if>
            <input type="text" class="form-control floating-label" name="username" id="username"
                   placeholder="Логин">
            <input type="password" class="form-control floating-label" name="password" id="password"
                   placeholder="Пароль">

            <input type="submit" class="button" value="Войти">
        </form>
    </div>
</div>


</#macro>
<@main title="Авторизация"
disableBg=true
/>