<#macro login path>
<div>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Логин :</label>
            <div class="col-sm-6">
            <input type="text" id="username" name="username" value="<#if user??>${user.username}</#if>" class="form-control
             ${(usernameError??)?string('is-invalid','')}"
            placeholder="User name"/>
                <#if usernameError??>
                <div class="invalid-feedback">
                    ${usernameError}
                </div>
            </#if>
            </div>
        </div>
        <div class="form-group row">
            <label  class="col-sm-2 col-form-label"> Пароль : </label>
            <div class="col-sm-6">
            <input type="password" name="password" class="form-control
             ${(passwordError??)?string('is-invalid','')}"
             placeholder="Password"/>
                <#if passwordError??>
                <div class="invalid-feedback">
                    ${passwordError}
                </div>
            </#if>
            </div>
        </div>
        <#if path=="/registration">
        <div class="form-group row">
    <label  class="col-sm-2 col-form-label"> Повторите пароль : </label>
    <div class="col-sm-6">
        <input type="password" name="password2" class="form-control
             ${(password2Error??)?string('is-invalid','')}"
               placeholder="Confirm password"/>
        <#if password2Error??>
        <div class="invalid-feedback">
            ${password2Error}
        </div>
    </#if>
</div>
</div>
        <div class="form-group row">
        <label class="col-sm-2 col-form-label" > Email :</label>
        <div class="col-sm-6">
        <input type="text" name="email" value="<#if user??>${user.email}</#if>" class="form-control ${(emailError??)?string('is-invalid','')}"
               placeholder="Email@email.com"/>
            <#if emailError??>
            <div class="invalid-feedback">
                ${emailError}
            </div>
        </#if>
        </div>
    </div>
    </#if>
<#if path=="/registration">
<div>
<div class="g-recaptcha" data-sitekey="6LcCLLUUAAAAABZ_XtkobXRzUto-gcsj1W5dQd-E"></div>
</div>
<#if captchaError??>
<div class="alert alert-danger" role="alert">
    ${captchaError}
</div>
</#if>
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