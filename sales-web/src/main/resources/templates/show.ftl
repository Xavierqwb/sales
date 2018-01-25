<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>商品展示</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head><body>
<#include "menu.ftl">
<div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="${product.image}" alt="" ></div>
        <div class="cnt">
            <h2>${product.title}</h2>
            <p class="summary">${product.summary}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${product.originPrice/100}</span>
            </div>
            <div class="num">购买数量：<span id="plusNum" class="lessNum"><a>-</a></span><span class="totalNum" id="allNum">1</span><span id="addNum" class="moreNum"><a>+</a></span></div>
            <div class="oprt f-cb">
                <#if product.bought>
                    <span class="u-btn u-btn-primary z-dis">已购买</span>
                    <span class="buyprice">当时购买价格：¥${product.price/100}</span>
                <#else>
                    <button class="u-btn u-btn-primary" id="add" data-id="${product.id}" data-title="${product.title}" data-price="${product.originPrice?c}">
                        加入购物车</button>
                </#if>
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${product.detail}
    </div>
</div>
<#include "footer.ftl">
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/pageShow.js"></script>
</body>
</html>