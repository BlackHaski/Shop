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

            for (var rating in result.ratings) {
                if (rating.posRating == 0)
                    negRating++;
                else
                    posRating++;
            }

            console.log(result);
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
            delete result.product.count;
            delete result.product.descr;
            delete result.product.images;
            delete result.product.price;
            delete result.product.productId;
            delete result.product.productName;
            delete result.product.productType;
            delete result.product.rebate;
            delete result.product.soldOut;

            console.log(result.product.length)
            for (let key in result.product) {
                let li = $("<li/>");
                li.text(key+":"+result.product[key]);
                $(".characters").append(li);
            }
        }
    });
});