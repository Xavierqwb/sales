<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>首页</title>
    <meta charset="UTF-8"/>
    <meta http-equiv="Content-Type" content="text/html"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" type="text/css" href="/css/style.css" charset="UTF-8"/>

</head>
<body>
<#include "menu.ftl">
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li class="z-sel"><a href="/">所有内容</a></li>

            </ul>
        </div>
    </div>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
        <#list products>
            <#items as product>
                <li id="p-${product_index+1}">
                    <a href="/show?id=#{product.id}" class="link">
                        <div class="img"><img src="${product.image}" alt="${product.title}"></div>
                        <h3>Java编程思想（第4版</h3>
                        <div class="price"><span class="v-unit">¥</span>
                            <span class="v-value">${product.price}</span>
                        </div>
                    </a>

                </li>
            </#items>
        </#list>
        </ul>
    </div>
</div>
<#include "footer.ftl">
</body>
<script type="text/javascript" src="/js/functions.js"></script>
</html>