<#import "parts/common.ftl" as c>
<#import "parts/log.ftl" as l>
<@c.page>
    <script src="http://code.jquery.com/jquery-2.2.4.js"
            type="text/javascript"></script>


<div align="center">

    <#if message??> <b>${message}</b></#if>

</div>
<@l.login "/registration" />
<@l.logout />
<script>
    $(document).ready(function() {
                                         $('#username').blur(function() {
                                         console.log('сработка события onchange');
                                         $.ajax({
                                             url: '/check',
                                             data : {
                                        username : $('#username').val()
                                    },
                                             success: function(data) {
                                                 if (data == true){
                                                 alert('Такой логин существует,придумайте новый')
                                                 }
                                               }
                                            });
                                        });
                                     });
</script>
</@c.page>




