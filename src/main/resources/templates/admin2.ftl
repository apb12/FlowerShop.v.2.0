<#import "parts/common.ftl" as c>
<#import "parts/log.ftl" as l>
<@c.page>
<div>Заказы к выполнению</div>
<#list evidence1 as e1>
    <div>
        <b>${e1.id}</b>
        <b>${e1.date}</b>
        <span>${e1.total}</span>
        <i>${e1.status}</i>
        <form action="/admin3" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="${e1.id}"/>
            <button class="btn btn-primary" type="submit">Выполнить</button>
        </form>
</#list>
</div>
<div>Черновики</div>
<#list evidence2 as e2>
    <div>
        <b>${e2.id}</b>
        <b>${e2.date}</b>
        <span>${e2.total}</span>
        <i>${e2.status}</i>
        <form action="/admin2" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="${e2.id}"/>
            <button class="btn btn-primary" type="submit">Удалить</button>
        </form>
    </#list>
</div>
<div>Архив заказов</div>
<#list evidence3 as e3>
    <div>
        <b>${e3.id}</b>
        <b>${e3.date}</b>
        <span>${e3.total}</span>
        <i>${e3.status}</i>
    </#list>
</div>
</@c.page>
