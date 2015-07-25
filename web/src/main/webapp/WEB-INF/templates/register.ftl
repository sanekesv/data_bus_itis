<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]>
<#include "mainTemplate.ftl">
<#macro m_body>
<div class="content">
    <div class="row row1">
        <h1>Регистрация</h1>

        <fieldset class="edit_form">
            <div class="col-md-6">
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
            </div>

        <div class="col-md-6">
            <section class="xls">
                <h3 class="blue">Регистрация студентов списком</h3>

                <p class="exm">Пример:</p>
                <img src="/resources/images/example_users_xls.png">
                <small class="pull-left">Загрузите .xls/.xlsx файл</small>
                <form action="/register/file" enctype="multipart/form-data" method="post">
                    <div class="fileform">
                        <div class="selectbutton">Выбрать файл...</div>
                        <div class="fileformlabel" id="fileformlabel"></div>
                        <input type="file" name="users" class="upload" id="users" accept=".xls,.xlsx"
                               onchange="getName(this.value,'fileformlabel');"/>
                    </div>
                    <div class="save load_file">
                        <button id="load" name="submit" class="edit_action">Загрузить</button>
                    </div>
                </form>
                <#if uploadError??>
                    <span class="text text-danger">Ошибка формата файла, попробуйте еще раз</span>
                </#if>
                <#if saveError??>
                    <span class="text text-danger">Ошибка в структуре файла, попробуйте еще раз</span>
                </#if>
            </section>
            <@sec.authorize access="hasRole('ROLE_ADMIN')">
                <section class="xls">
                    <h3 class="blue">Скачать список студентов с паролями</h3>

                    <div class="save download">
                        <a href="/download/users">
                            <button id="download" name="submit" class="edit_action">Скачать список</button>
                        </a>
                    </div>
                </section>
                <section class="xls">
                    <h3 class="blue">Скачать список с рейтингом</h3>

                    <div style="color:red;" id="rangeError"></div>
                    <select id="reportType">
                        <option value="-1">---</option>
                        <#list reportTypes as reportType>
                            <option value="${reportType.text}">${reportType.displayName}</option>
                        </#list>
                    </select>

                    <div id="reportParams" class="hide">
                        <div id="semestrRange">
                            <small>семестр</small>
                            <select id="semestrNumber">
                                <option value="3">1 + 2 семестр</option>
                                <option value="1">1-ый семестр</option>
                                <option value="2">2-ой семестр</option>
                            </select>
                            <small>учебный год</small>
                            <select id="year">
                                <#if years?? && years?has_content>
                                    <#list years as year>
                                        <option value="${year?c}">${year?c}/${(year + 1)?c}</option>
                                    </#list>
                                </#if>
                            </select>

                            <div class="save download">
                                <a href="#">
                                    <button id="downloadRatingBySemestrRange" name="submit" class="edit_action">Скачать
                                        список
                                    </button>
                                </a>
                            </div>
                        </div>
                        <div id="customRange">
                            <small>с</small>
                            <input class="form-control" name="date" id="fromDate" required/>
                            <small>по</small>
                            <input class="form-control" name="date" id="toDate" required/>
                            <a href="#">
                                <button id="downloadRatingByCustomRange" name="submit" class="edit_action">Скачать список
                                </button>
                            </a>
                        </div>
                    </div>
                </section>
                <a href="/user/delete">Delete all</a>

            </div>
            </@sec.authorize>
        </fieldset>

    </div>
</div>
</#macro>
<@main
title="Регистрация"
customScripts= [
"/resources/js/register.js"
]
/>