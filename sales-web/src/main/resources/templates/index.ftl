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
        <#list allProducts>
            <#items as product>
                <#if userModel??>
                    <#if userModel.type=0>
                        <li id="p-${product.id}">
                            <a href="/sales/show?id=#{product.id}" class="link">
                                <div class="img"><img src="${product.image}"
                                                      alt="${product.title}">
                                </div>
                                <h3>${product.title}</h3>
                                <div class="price"><span class="v-unit">¥</span>
                                    <span class="v-value">${product.price/100}</span>
                                </div>
                            </a>
                            <#if product.soldNum=0>
                                <span class="u-btn u-btn-normal u-btn-xs del"
                                      data-del="${product.id}">删除</span>
                            <#else>
                                <span class="had"><b>已售出${product.soldNum}件</b></span>
                            </#if>
                        </li>
                    <#elseif notBuyFlag=0>
                        <li id="p-${product.id}">
                            <a href="/sales/show?id=#{product.id}" class="link">
                                <div class="img"><img src="${product.image}"
                                                      alt="${product.title}">
                                </div>
                                <h3>${product.title}</h3>
                                <div class="price"><span class="v-unit">¥</span>
                                    <span class="v-value">${product.price/100}</span>
                                </div>
                            </a>
                            <#if (product.soldNum > 0)>
                                <span class="had"><b>已购买</b></span>
                            </#if>
                        </li>
                    <#elseif product.soldNum=0>
                        <li id="p-${product.id}">
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
                    </#if>
                <#else>
                    <li id="p-${product.id}">
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
                </#if>
            </#items>
        </#list>
        </ul>
    </div>
</div>
<#include "footer.ftl">
</body>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageIndex.js"></script>
</html>