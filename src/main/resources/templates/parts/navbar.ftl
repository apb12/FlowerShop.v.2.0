<#include "security.ftl">
<#import "log.ftl" as l>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">Flowershop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/login">Login</a>
            </li>
            <#if isActivated>
            <li class="nav-item">
                <a class="nav-link" href="/main">Main</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/room">Your Account</a>
            </li>
        </#if>
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/admin">Admin</a>
            </li>
        </#if>
        </ul>
        <div class="navbar-text mr-3" >${name}</div>
        <@l.logout />
    </div>
</nav>