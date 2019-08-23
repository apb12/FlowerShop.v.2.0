<#import "parts/common.ftl" as c>
<#import "parts/log.ftl" as l>

<@c.page>
<div>Правка статуса пользователей</div>
    <div>
        <form action="/user" method="post">
            <input type="text" name="username"value="${user.username}">
            <#list roles as role>
            <div>
                <label><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("cheked","")}>${role}</label>

            </div>
                </#list>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="userId"value="${user.id}">

        <button class="btn btn-primary" type="submit">Правка</button>
        </form>

</div>
</@c.page>