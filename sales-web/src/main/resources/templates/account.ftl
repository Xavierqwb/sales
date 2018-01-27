<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>java</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<#include "menu.ftl">
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已购买的内容</h2>
    </div>
<#if userModel??>
    <table class="m-table m-table-row n-table g-b3">
        <colgroup>
            <col class="img"/>
            <col/>
            <col class="time"/>
            <col/>
            <col class="num"/>
            <col/>
            <col class="price"/>
            <col/>
        </colgroup>
        <thead>
        <tr>
            <th>内容图片</th>
            <th>内容名称</th>
            <th>购买时间</th>
            <th>购买数量</th>
            <th>购买价格</th>
        </tr>
        </thead>

        <#if finances??>
            <tbody>
                <#list finances>
                    <#items as finance>
                    <tr>
                        <td><a href="/show?id=${finance.productId}"><img src="${finance.image}" alt=""></a></td>
                        <td><h4><a href="/show?id=${finance.productId}">${finance.title}</a></h4>
                        </td>
                        <td><span class="v-time">${finance.purchaseTime}</span></td>
                        <td><span class="v-num">${finance.num}</span></td>
                        <td><span class="v-unit">¥</span><span class="value">${(finance.price/100)?c}</span></td>
                    </tr>
                    </#items>
                </#list>

            </tbody>
            <tfoot>
            <tr>
                <td colspan="4">
                    <div class="total">总计：</div>
                </td>
                <td><span class="v-unit">¥</span><span class="value">${(totalPrice/100)?c}</span></td>
            </tr>
            </tfoot>
        </#if>
    </table>
<#else>
    <div class="n-result">
        <h3>暂无内容！</h3>
    </div>
</#if>

</div>
<#include "footer.ftl">
</body>
</html>