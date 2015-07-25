<#macro main title="ITIS Activity Application" customScripts=[] customStyles=[] offDefaultStyles=false disableBg=false>
<!DOCTYPE html>
<html>
<head>
<#--Rate.me site-->
<#--ver.: 2.0.0 "Pink Pony"-->
<#--web-dev & dezign: vk.com/ramiru-->
    <meta charset="utf-8"/>
    <title>${title?string}</title>
    <#list customStyles as style>
        <link href="${style}" rel="stylesheet"/>
    </#list>
    <#if !offDefaultStyles>
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="/resources/css/jquery-ui/jquery-ui.min.css" rel="stylesheet" type="text/css"/>
        <link href="/resources/css/style.min.css" rel="stylesheet" type="text/css">
    </#if>
    <script src="/resources/js/jquery-2.1.1.min.js"></script>

</head>
<body>
    <@m_body/>

    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/jquery-ui.min.js"></script>
    <script src="/resources/js/functions.js"></script>

    <#list customScripts as script>
        <script src="${script}"></script>
    </#list>
</body>
</html>
</#macro>