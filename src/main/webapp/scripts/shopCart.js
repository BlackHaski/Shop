$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$("[name = 'deleteProductFromCart']").click(function () {
    $.ajax({
        url: "/deleteProductFromCart",
        method: "post",
        contentType: "text/plain",
        data: $(this).attr("data-delProdname"),
        success: function (response) {
            if (response == true) {
                window.location.reload();
            }
        }
    });
});

$("#checkout").click(function () {
    let prod = {};
    let cart = [];
    let counter = 0;
    $("div[name = 'productInCart']").each(function () {
        let div = $(this).children()[1];
        let h = $(div).children("h1");
        let name = $(h).children();
        let count = $(this).children("[name='count']");

        prod.name = $(name).attr("data-productName");
        prod.count = $(count).attr("data-count");
        console.log(prod);
        cart[counter] = prod;
        prod={};
        counter++;
    });
    $.ajax({
        url:"/buyProduct",
        method:"post",
        contentType:"application/json",
        data:JSON.stringify(cart),
        success:function () {
            window.location.reload(true);
        }
    });
});
