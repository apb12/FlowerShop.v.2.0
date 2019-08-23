<#import "parts/common.ftl" as c>
<#import "parts/log.ftl" as l>

<@c.page>
<div align="center"><b>Распечатка заказа</b></div>
<#list show as shows>
<div align="center">
        <b>${shows.flower.name}</b>
        <b>${shows.amount}</b>
        <span>${shows.sum}</span>

</#list>
</div>
</@c.page>
