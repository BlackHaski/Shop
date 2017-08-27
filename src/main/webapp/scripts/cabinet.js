$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$(document).ready(function () {
    $("#container").load("/cabinet/profile");
});

$("#profile").click(function () {
    $("#container").load("/cabinet/profile");
});

$("#editProfile").click(function () {
    $("#container").load("/cabinet/editProfile");
});
$("#userArchiveProducts").click(function () {
    $.ajax({
        url: "/getUserGoods",
        method: 'post',
        success: function (goods) {
            console.log(goods);
            $("#container").load("/cabinet/purchasedGoods", function () {
                let clearLoad = $("div[name='productInCart']").first().clone(true, true);
                for (let i = 0; i < goods.length; i++) {
                    let obj = goods[i];
                    let load = $(clearLoad).clone(true, true);
                    $(load).children("div").children("img[name='imgPurchasedGood']").attr("src", obj.product.images[0]);
                    $(load).children("div").children("h1").children("a[name = 'productName']").text(obj.product.productName);
                    $(load).children("div").children("h1").children("a[name = 'productName']").attr("href", "product-" + obj.product.productName);
                    $(load).children("div[name = 'count']").text(obj.count);
                    $(load).children("div[name = 'pricePurchasedProduct']").text(obj.count * obj.product.price);
                    $("#container").append(load);
                }
                let child = $("#container").children();
                $(child)[0].remove();
            });
        }
    });
});
