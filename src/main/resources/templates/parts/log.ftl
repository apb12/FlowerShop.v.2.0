<#macro login path>
<div>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Логин :</label>
            <div class="col-sm-6">
            <input type="text" id="username" name="username" class="form-control"/>
            </div>
        </div>
        <div class="form-group row">
            <label  class="col-sm-2 col-form-label"> Пароль : </label>
            <div class="col-sm-6">
            <input type="password" name="password" class="form-control"/>
            </div>
        </div>
        <#if path=="/registration"> <div class="form-group row">
        <label class="col-sm-2 col-form-label" > Email :</label>
        <div class="col-sm-6">
        <input type="email" name="email" placeholder="sss@SSS.com" class="form-control"/>
        </div>
    </div>
    </#if>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <#if path=="/registration"><div>
    <button class="btn btn-primary" type="submit">Регистрация</button></div><#else><div><button class="btn btn-primary" type="submit">Вход</button></div></#if>
    </form>
</div>
</#macro>
<#macro logout>
<div>
    <form action="/logout" method="post">
        <button class="btn btn-primary" type="submit">Выход</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</div>
</#macro>