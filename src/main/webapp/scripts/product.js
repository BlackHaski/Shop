$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$(document).ready(function () {
    let pathname = window.location.pathname;
    let nameProduct = pathname.substring(pathname.indexOf('-') + 1);
    $.ajax({
        url: "/getCurrentProduct",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(nameProduct),
        success: function (result) {
            let posRating = 0;
            let negRating = 0;

            for (let key in result.ratings) {
                if (result.ratings[key].posRating == false)
                    negRating++;
                else
                    posRating++;
            }
            $("#productNameH").text(result.product.productName);
            $("#productPriceH").text(result.product.price);
            $("#productDescrP").text(result.product.descr);
            $("#posRating").text(posRating);
            $("#negRating").text(negRating);
            $("#mainProductImg").attr("src", result.product.images[0]);
            let firstImg = $("#productImagesContainer").children("img").first();
            $("#productImagesContainer").children("img").first().remove();
            for (let i = 0; i < result.product.images.length; i++) {
                let imgPath = result.product.images[i];
                let cloneImg = $(firstImg).clone(true, true);
                $(cloneImg).attr("src", imgPath);
                $("#productImagesContainer").append($(cloneImg));
            }
            console.log(result);
            delete result.product.count;
            delete result.product.descr;
            delete result.product.images;
            delete result.product.price;
            delete result.product.productId;
            delete result.product.productName;
            delete result.product.productType;
            delete result.product.rebate;
            delete result.product.soldOut;

            for (let key in result.product) {
                let li = $("<li/>");
                li.text(key + ":" + result.product[key]);
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
    switch (type){
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
        default:break;
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
        nameProduct : nameProduct,
        countProduct : count
    };
    $.ajax({
        url:"/addToCartProduct",
        method:"post",
        contentType:"application/json",
        data:JSON.stringify(params),
        success:function (response) {
            if (response == false){
                showPopupWindow();
            }
        }
    });
});