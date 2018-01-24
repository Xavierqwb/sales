<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>java</title>
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body>
<#include "menu.ftl">
<div class="g-doc" id="settleAccount">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已添加到购物车的内容</h2>
    </div>
    <table id="newTable" class="m-table m-table-row n-table g-b3">
        <tbody>
        <tr>
            <th>内容名称</th>
            <th>数量</th>
            <th>价格</th>
        </tr>
        <#list cartRecords>
            <#items as cartRecord>
            <tr>
                <td>${cartRecord.name}</td>
                <td>
                    <span class="lessNum">-</span>
                    <span class="totalNum" id="allNum">${cartRecord.number}</span>
                    <span id="thisId">${cartRecord.id}</span>
                    <span class="moreNum">+</span>
                </td>
                <td>${cartRecord.price}</td>
            </tr>
            </#items>
        </#list>
        </tbody>
    </table>
    <div id="act-btn">
        <button class="u-btn u-btn-primary" id="back">退出</button>
        <button class="u-btn u-btn-primary" id="Account">购买</button>
    </div>
</div>
<#include "footer.ftl">
<script type="text/javascript" src="../js/global.js"></script>
<script type="text/javascript" src="../js/settleAccount.js"></script>
</body>
</html>