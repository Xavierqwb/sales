<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
    <#if isLogin??>
        <div class="user">
            <#switch name>
                <#case 'seller'>
                    卖家你好，
                    <#break>
                <#case 'buyer'>
                    买家你好，
                    <#break>
            </#switch>
            <span>${name}!</span>
            <a href="javascript:quitLogin()">[退出]</a>
        </div>
    <#else>
        <div class="user">
            请<a href="/sales/login">[登录]</a>
        </div>
    </#if>
        <ul class="nav">
            <li><a href="/sales">首页</a></li>
        </ul>
    </div>
</div>