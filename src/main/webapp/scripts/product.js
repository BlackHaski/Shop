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
        success: function (response) {
            console.log(response);
            $("#productNameH").text(response.product.productName);
            $("#productNameH").attr("data-key", "productName");

            $("#productPriceH").text(response.product.price);
            $("#productPriceH").attr("data-key", "price");

            $("#countProductP").text("Count:" + response.product.count);
            $("#countProductP").attr("data-val", response.product.count);
            $("#countProductP").attr("data-key", "count");

            $("#soldOut").text("Sold Out:" + response.product.soldOut);
            $("#soldOut").attr("data-key", "soldOut");

            $("#productDescrP").text(response.product.descr);
            $("#productDescrP").attr("data-key", "descr");

            $("#mainProductImg").attr("src", response.product.images[0]);
            let firstImg = $("#productImagesContainer").children("img").first();
            $("#productImagesContainer").children("img").first().remove();
            for (let i = 0; i < response.product.images.length; i++) {
                let imgPath = response.product.images[i];
                let cloneImg = $(firstImg).clone(true, true);
                $(cloneImg).attr("src", imgPath);
                $("#productImagesContainer").append($(cloneImg));
            }
            if (Number.parseInt(response.product.count) <= 0){
                document.getElementById("addToCart").disabled = true;
            }
            delete response.product.count;
            delete response.product.descr;
            delete response.product.images;
            delete response.product.price;
            delete response.product.productId;
            delete response.product.productName;
            delete response.product.productType;
            delete response.product.rebate;
            delete response.product.soldOut;

            for (let key in response.product) {
                let li = $("<li/>");
                li.text(key + ":" + response.product[key]);
                li.attr("data-key", key);
                $(".characters").append(li);
            }
            for (let key in response.categories) {
                let $option = $("<option/>");
                $option.attr("value",response.categories[key].categoryName);
                $option.text(response.categories[key].categoryName);
                $("#productCategory").append($option);
            }
        }
    });
});

$("#productCategory").change(function () {
    let categoryName = this.options[this.selectedIndex].getAttribute("value");
    let productName = getProductName();
    let params = {
        productName:productName,
        categoryName: categoryName
    };
    if (categoryName != "default"){
        $.ajax({
            url:"/changeProductCategory",
            method:"post",
            contentType:"application/json",
            data:JSON.stringify(params),
            success:function () {
                console.log("here");
            }
        });
    }
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
                }, 3000)
            }
        }
    });
});
$(window).bind('beforeunload', function () {
    return disconnect();
});

$("span[name = 'deleteProduct']").click(function () {
    let name = $(this).attr("data-productName");
    $.ajax({
        url: "/deleteProduct",
        method: "post",
        contentType: "text/plain",
        data: name
    });
    $(this).parent().parent().remove();
});

$(document).on("dblclick", "#prodInfo h1, #prodInfo h2, #prodInfo p, #prodInfo li", function () {
    let inputWindow = document.createElement("div");
    let header = document.createElement("h1");
    let input = document.createElement("input");
    let button = document.createElement("button");
    $(button).text("CHANGE");

    $(header).html("Please enter a new property ("
        + "<span data-key=" + $(this).attr('data-key') + ">" + $(this).attr("data-key") + "</span>" + "):");
    $(header).attr("class", "headerInputWindow");

    $(inputWindow).attr("class", "inputWindow");

    $(input).attr("class", "inputInInputWindow");

    $(button).attr("class", "buttonInInputWindow");

    $(inputWindow).append("<span class='closeInputWindow'>X</span>")
    $(inputWindow).append(header);
    $(inputWindow).append(input);
    $(inputWindow).append(button);
    document.body.appendChild(inputWindow);
});
$(document).on("click", ".buttonInInputWindow", function () {
    let val = $(".inputWindow input").val();
    let key = $(".headerInputWindow span").attr("data-key");
    let params = {
        column: key,
        productName: getProductName(),
        value: val
    };
    $.ajax({
        url: "/updateProduct",
        method: "post",
        contentType: "application/json",
        data: JSON.stringify(params),
        success: function (response) {
            $(".inputWindow").remove();
            if (key == "productName"){
                var url = "localhost8080:/product-".concat(val);
                window.location.href = url;
            }else {
                window.location.reload();
            }
        }
    });
});
$(document).on("click", ".closeInputWindow", function () {
    $(".inputWindow").remove();
});
