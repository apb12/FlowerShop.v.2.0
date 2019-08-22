<#macro login path>
<div align="center">
    <form action="${path}" method="post">
        <div><label> Логин : <input type="text" id="username" name="username"/> </label></div>
        <div><label> Пароль : <input type="password" name="password"/> </label></div>
        <#if path=="/registration"> <div><label> Email : <input type="email" name="email" placeholder="sss@SSS.com"/> </label></div></#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <#if path=="/registration"><div><input type="submit" value="Регистрация"/></div><#else><div><input type="submit" value="Вход"/></div></#if>
    </form>
</div>
</#macro>
<#macro logout>
<div align="center">
    <form action="/logout" method="post">
        <input type="submit" value="Выход"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</div>
</#macro>