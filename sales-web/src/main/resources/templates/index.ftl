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
            <#--如果买家登录，显示所有内容、未购买的内容-->
            <#if userModel?? && userModel.type=1>
            <#--未购买的内容标签-->
                <#if notBuyFlag=1>
                    <li><a href="/sales">所有内容</a></li>
                    <li class="z-sel"><a href="/sales?type=1">未购买的内容</a></li>
                <#else>
                <#--所有内容标签-->
                    <li class="z-sel"><a href="/sales">所有内容</a></li>
                    <li><a href="/sales?type=1">未购买的内容</a></li>
                </#if>
            <#else>
            <#--如果卖家、路人，只显示所有内容标签-->
                <li class="z-sel"><a href="/sales">所有内容</a></li>
            </#if>
            </ul>
        </div>
    </div>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
        <#if notBuyFlag=0>
            <#list boughtProducts>
                <#items as product>
                    <li id="p-${product_index+1}">
                        <a href="/sales/show?id=#{product.id}" class="link">
                            <div class="img"><img src="${product.image}"
                                                  alt="${product.title}">
                            </div>
                            <h3>${product.title}</h3>
                            <div class="price"><span class="v-unit">¥</span>
                                <span class="v-value">${product.price/100}</span>
                            </div>
                            <#if userModel??>
                                <#if userModel.type=1>
                                    <span class="had"><b>已购买</b></span>
                                <#else>
                                    <span class="had"><b>已售出</b></span>
                                </#if>
                            </#if>
                        </a>
                    </li>
                </#items>
            </#list>
        </#if>
        <#list notBoughtProducts>
            <#items as product>
                <li id="p-${product_index+1}">
                    <a href="/sales/show?id=#{product.id}" class="link">
                        <div class="img"><img src="${product.image}"
                                              alt="${product.title}">
                        </div>
                        <h3>${product.title}</h3>
                        <div class="price"><span class="v-unit">¥</span>
                            <span class="v-value">${product.price/100}</span>
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