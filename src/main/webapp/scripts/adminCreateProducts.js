$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});
$(document).ready(function () {
    $.ajax({
        url: "/getProductTypes",
        method: "post",
        success: function (types) {
            let $select = $("#productTypeSel");
            $(types).each(function () {
                let $option = $("<option/>");
                $option.attr("value",this);
                $option.text(this);
                $select.append($option);
            });
        }
    });
    $.ajax({
        url: "/show",
        method: "get",
        success: function (categories) {
            let $select = $("#productCategorySel");
            $(categories).each(function () {
                let $option = $("<option/>");
                $option.attr("value",this.categoryName);
                $option.text(this.categoryName);
                $select.append($option);
            });
        }
    });
});
$("#productTypeSel").change(function () {
    let tableName = this.options[this.selectedIndex].getAttribute("value");
    let jsonTableName = JSON.stringify(tableName);
    $.ajax({
        url:"/getIndividualCharact",
        method:"post",
        contentType:"application/json",
        data: jsonTableName,
        success:function (values) {
            let $individCharactContainer = $("#individCharact");
            $individCharactContainer.empty();
            $(values).each(function () {
                let h = $("<h3/>");
                h.text(this);
                h.addClass("headInp");

                $individCharactContainer.append(h);

                let input = $("<input/>");
                input.addClass("width100p");
                input.attr("name","characteristic");
                $individCharactContainer.append(input);
            });
        }
    });
});

$(document).on("change","input[type = file]",function () {
    let $container = $("<div/>");
    let $file = $("<input type='file'/>");
    $file.attr("name","productImages");
    $file.addClass("width98p");
    $container.append($file);
    let $span = $("<span/>");
    $span.addClass("float-r");
    $span.text("x");
    let parent = $(this).parent();
    parent.append($span);
    $($container).insertBefore($("#createProductBtn"));
});
$(document).on("click","span",function () {
    $(this).parent().remove();
});
