<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
    <#if userModel??>
        <div class="user">
            <#switch userModel.type>
                <#case 0>
                    卖家你好，
                    <#break>
                <#case 1>
                    买家你好，
                    <#break>
            </#switch>
            <span>${userModel.account}!</span>
            <a href="javascript:quitLogin()">[退出]</a>
        </div>
        <ul class="nav">
            <li><a href="/sales">首页</a></li>
            <#if userModel.type=0>
                <li><a href="/sales/publish">发布</a></li>
            <#else>
                <li><a href="/sales/account">账务</a></li>
                <li><a href="/sales/settleAccount">购物车</a></li>
            </#if>
        </ul>
    <#else>
        <div class="user">
            请<a href="/sales/login">[登录]</a>
        </div>
        <ul class="nav">
            <li><a href="/sales">首页</a></li>
        </ul>
    </#if>
    </div>
</div>