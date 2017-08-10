$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$(document).ready(function () {
    connect();
    let pathname = window.location.pathname;
    let nameProduct = pathname.substring(pathname.indexOf('-') + 1);
    $.ajax({
        url: "/getCurrentProduct",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(nameProduct),
        success: function (product) {
            $("#productNameH").text(product.productName);
            $("#productPriceH").text(product.price);
            $("#countProductP").text("Count:"+product.count);
            $("#soldOut").text("Sold Out:"+product.soldOut);
            $("#productDescrP").text(product.descr);
            $("#mainProductImg").attr("src", product.images[0]);
            let firstImg = $("#productImagesContainer").children("img").first();
            $("#productImagesContainer").children("img").first().remove();
            for (let i = 0; i < product.images.length; i++) {
                let imgPath = product.images[i];
                let cloneImg = $(firstImg).clone(true, true);
                $(cloneImg).attr("src", imgPath);
                $("#productImagesContainer").append($(cloneImg));
            }
            delete product.count;
            delete product.descr;
            delete product.images;
            delete product.price;
            delete product.productId;
            delete product.productName;
            delete product.productType;
            delete product.rebate;
            delete product.soldOut;

            for (let key in product) {
                let li = $("<li/>");
                li.text(key + ":" + product[key]);
                $(".characters").append(li);
            }
        }
    });
});
$(document).on("click", "#productImagesContainer img", function () {
    $("#mainProductImg").attr("src", this.getAttribute("src"));
});
var typeAnsw = "";
$("img[name = 'posRatingImg'],img[name = 'negRatingImg']").click(function () {
        let val = 0;
        if ($(this).attr("name") == "posRatingImg") {
            changeRating(true);
        } else {
            changeRating(false);
        }
    }
);

function changeRatingOnPage(type) {
    switch (type) {
        case "savetrue": {
            val = Number($("#posRating").text()) + 1;
            $("#posRating").text(val);
            break;
        }
        case "savefalse": {
            val = Number($("#negRating").text()) + 1;
            $("#negRating").text(val);
            break;
        }
        case "deletetrue": {
            val = Number($("#posRating").text()) - 1;
            $("#posRating").text(val);
            break;
        }
        case "deletefalse": {
            val = Number($("#negRating").text()) - 1;
            $("#negRating").text(val);
            break;
        }
        case "anonim": {
            showPopupWindow();
            break;
        }
        default:
            break;
    }
}

function changeRating(type) {
    let pathname = window.location.pathname;
    let nameProduct = pathname.substring(pathname.indexOf('-') + 1);
    let params = {
        nameP: nameProduct,
        rating: type
    };
    $.ajax({
        url: "/setProductRating",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(params),
        success: function (success) {
            typeAnsw = success;
            changeRatingOnPage(typeAnsw);
        }
    });
}

$("#addToCart").click(function () {
    let pathname = window.location.pathname;
    let nameProduct = pathname.substring(pathname.indexOf('-') + 1);
    let count = $("#countProduct").val();
    let params = {
        nameProduct: nameProduct,
        countProduct: count
    };
    $.ajax({
        url: "/addToCartProduct",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(params),
        success: function (response) {
            if (response == "anonim") {
                showPopupWindow();
            } else if (response == "add" || response == "changeCount") {
                let interval = setInterval(function () {
                    $("#shopCartPage").addClass("color-green");
                    setTimeout(function () {
                        $("#shopCartPage").removeClass("color-green");
                    }, 300);
                }, 600);
                setTimeout(function () {
                    clearInterval(interval);
                },3000)
            }
        }
    });
});
$(window).bind('beforeunload', function(){
    return disconnect();
});