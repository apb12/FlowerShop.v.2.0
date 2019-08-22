<#import "parts/common.ftl" as c>
<#import "parts/log.ftl" as l>
<@c.page>
    <div align="center">
        <b>Привет ,${user.username}</b>
        <b>Tвой баланc равен : ${user.cash}</b>
        <b>Tвоя скидка равна : ${user.discount}</b>

</div>

</div>
<div>корзина</div>
<#list evidence as evidences>
    <div>
        <b>${evidences.id}</b>
        <b>${evidences.total}</b>
        <span>${evidences.status}</span>
        <form action="/pay" method="post">
            <input type="hidden" name="id" value="${evidences.id}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Оплатить заказ"/>
        </form>
</#list>
</div>
<div>архив заказов</div>
<#list evidence1 as evidences1>
    <div>
        <b>${evidences1.id}</b>
        <b>${evidences1.date}</b>
        <span>${evidences1.status}</span>

</#list>
</div>
<div align="center>">
    <form action="/us" method="post">
        <input type="number" name="id" placeholder="id"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="расшифрока заказа"/>
    </form>
</div>
<div align="center">
    <form action="/main" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="На главную"/>
    </form>

</div>
<@l.logout />
</@c.page>