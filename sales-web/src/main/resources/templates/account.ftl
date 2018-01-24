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
    <table class="m-table m-table-row n-table g-b3">
        <colgroup><col class="img"/><col/><col class="time"/><col/><col class="num"/><col/><col class="price"/><col/></colgroup>
        <thead>
        <tr><th>内容图片</th><th>内容名称</th><th>购买时间</th><th>购买数量</th><th>购买价格</th></tr>
        </thead>
        <tbody>
        <tr>
            <td><a href="/show?id=1"><img src="abc" alt=""></a></td>
            <td><h4><a href="/show?id=1">Java编程思想（第4版</a></h4></td>
            <td><span class="v-time">2016-07-07 15:14</span></td>
            <td><span class="v-num">0</span></td>
            <td><span class="v-unit">¥</span><span class="value">1</span></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="4"><div class="total">总计：</div></td>
            <td><span class="v-unit">¥</span><span class="value">146510.69</span></td>
        </tr>
        </tfoot>
    </table>
</div>
<#include "footer.ftl">
</body>
</html>