/**
 * Created by xavier on 2018/1/25.
 */

var $ = function (id) {
    return document.getElementById(id);
}

$('plusNum').onclick = function (e) {
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = $('allNum').textContent;
    if (num > 0) {
        num--;
        $('allNum').innerHTML = num;
    } else {
        alert("您没有购买任何商品");
    }
};

$('addNum').onclick = function (e) {
    e = window.event || e;
    o = e.srcElement || e.target;
    var num = $('allNum').textContent;
    num++;
    $('allNum').innerHTML = num;
};

var loading = new Loading();
var layer = new Layer();

$('add').onclick = function (e) {
    var ele = e.target;
    var id = ele && ele.dataset.id;
    var title = ele && ele.dataset.title;
    var price = ele && ele.dataset.price;
    var num = $('allNum').innerHTML;
    var productDetail = {'id': id, 'price': price, 'title': title, 'num': num};
    // var name = 'productsAddToCart';
//     var productList = [];
//     productList.push(productDetail);
//     util.setCookie(name, encodeURIComponent(JSON.stringify(productList)));
//     console.log(document.cookie);
// //		util.deleteCookie(name);
//     e == window.event || e;
    layer.reset({
                    content: '确认加入购物车吗？',
                    onconfirm: function () {
                        layer.hide();
                        loading.show();
                        var xhr = new XMLHttpRequest();
                        var data = JSON.stringify(productDetail);
                        xhr.onreadystatechange = function(){
                            if(xhr.readyState === 4){
                                var status = xhr.status;
                                if(status >= 200 && status < 300 || status === 304){
                                    var json = JSON.parse(xhr.responseText);
                                    if(json && json.code === 200){
                                        loading.result('添加到购物车成功',function(){location.href = './settleAccount';});
                                        util.deleteCookie(name);
                                    }else{
                                        alert(json.message);
                                    }
                                }else{
                                    loading.result(message||'添加到购物车失败');
                                }
                            }
                        };
                        xhr.open('post','/sales/api/addToCart');
                        xhr.setRequestHeader('Content-Type','application/json');
                        xhr.send(data);
                        // loading.result('添加购物车成功');
                        // location.reload(true);
                    }.bind(this)
                }).show();
    return;
};




