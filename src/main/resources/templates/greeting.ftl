<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<#if isActivated>
<div align="center"><b>Приветствуем вас в нашем цветочном магазине</b></div>
<#else>
<#if user??>
<div align="center"><b>Ваш аккаунт не активирован</b></div>
</#if>
</#if>
</@c.page>
