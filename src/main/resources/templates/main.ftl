<#import "parts/common.ftl" as c>
<#import "parts/log.ftl" as l>
<@c.page>
    <script src="http://code.jquery.com/jquery-2.2.4.js"
            type="text/javascript"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<#if flowers??>
<div align="center">Список товаров</div>
<#list flowers as flower>
    <div align="center">
        <b>${flower.name}</b>
        <span>${flower.price}</span>
        <i>${flower.amount}</i>
        <form action="/ss" method="post">
            <input type="number" step=1 min=1 pattern=[0-9]{3} name="amount" id="amount" required/>
            <input type="hidden" name="flowername" id="flowername" value="${flower.name}"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="положить в корзину"/>
        </form>
    </div>
</#list>
</#if>

    <div align="center">
        <#if message??><b>${message}</b></#if>
</div>
<script>
    $(document).ready(function() {
                                         $('#amount').blur(function() {
                                         console.log('сработка события onchange');
                                         $.ajax({
                                             url: '/validate',
                                             data : {
                                        amount : $('#amount').val(),
                                        flowername : $('#flowername').val()
                                    },
                                             success: function(data) {
                                                 if (data == true){
                                                 alert('У вас недостаточно средств/на складе не хватает цветов')
                                                 }
                                               }
                                            });
                                        });
                                     });
</script>
<div align="center">
    <form action="/userroom" method="post">
        <input type="submit" value="Корзина"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
    <@l.logout />
</div>
</@c.page>

