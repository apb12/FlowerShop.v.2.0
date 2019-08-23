<#import "parts/common.ftl" as c>
<#import "parts/log.ftl" as l>
<@c.page>
<div>Добавдение цветов на склад
    <form method="post" action="admin">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="text" name="name" placeholder="название цветка"/>
        <input type="integer" name="price" placeholder="Цена"/>
        <input type="integer" name="amount" placeholder="Количество"/>
        <button class="btn btn-primary" type="submit">Добавить на склал</button>
    </form>
</div>

<div>Удаление цветка со склада
    <form method="post" action="delete">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="number" step=1 min=1 pattern=[0-9]{3} name="id" placeholder="Id"/>
        <button class="btn btn-primary" type="submit">Удалить цветок</button>
    </form>
</div>

<div>Добавить количество цветов на склад
    <form method="post" action="addamount">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="number" step=1 min=1 pattern=[0-9]{3} name="id" placeholder="Id"/>
        <input type="number" step=1 min=1 pattern=[0-9]{3} name="amount" placeholder="количество"/>
        <button class="btn btn-primary" type="submit">Добавить количество</button>
    </form>
</div>
<div>Изменение цены
    <form method="post" action="newprice">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="number" step=1 min=1 pattern=[0-9]{3} name="id" placeholder="Id"/>
        <input type="number" step=1 min=1 pattern=[0-9]{3} name="price" placeholder="цена"/>
        <button class="btn btn-primary" type="submit">Изменить цену</button>
    </form>
</div>

<div>Склад цветов</div>
<#list flowers as flower>
    <div>
        <b>${flower.id}</b>
        <b>${flower.name}</b>
        <span>${flower.price}</span>
        <i>${flower.amount}</i>
</#list>
</div>

<div align="center">
    <a href="/admin2">Обработка заказов</a>
</div>
<div align="center">
    <a href="/user">Пользователи</a>
</div>
</@c.page>