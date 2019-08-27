<#import "parts/common.ftl" as c>
<#import "parts/log.ftl" as l>

<@c.page>
<#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
<div class="alert alert-danger" role="alert">
   ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
</div>
</#if>
<@l.login "/login" />
<div><a href="/registration">Регистрация</a></div>
</@c.page>
