<#import "parts/common.ftl" as c>

<@c.page>
<div align="center">Приветсвуем вас в нашем цветочном магазине</div>
<div align="center">
    <div align="center">
        <form action="/main" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Главная страница">

        </form>
    </div>
</div>
</@c.page>
