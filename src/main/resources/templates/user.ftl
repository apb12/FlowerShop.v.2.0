<#import "parts/common.ftl" as c>
<#import "parts/log.ftl" as l>
<@c.page>
<div align="center"> Список пользователей</div>
<#list usrlist as u>
    <div>
        <b>${u.username}</b>
        <b> <#list u.roles as role>${role} <#sep> , </#list></b>
    <a href="/user/${u.id}">Правка</a>

</#list>
</div>
</@c.page>
