$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

$("[name = 'deleteProductFromCart']").click(function () {
    $.ajax({
        url:"/deleteProductFromCart",
        method:"post",
        contentType:"text/plain",
        data:$(this).attr("data-delProdname"),
        success:function (response) {
            if (response == true) {
                window.location.reload();
            }
        }
    });
});
