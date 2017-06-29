function Category(categoryName,parentId) {
    this.categoryId = 0;
    this.categoryName = categoryName;
    this.parentId = parentId;
}
$("#aCategory").click(
    function () {
        $.ajax({
            url: "/show",
            method: "get",
            success: function (categories) {
                $("main").removeClass("hide");
                let $categories = $("#categories");
                $categories.empty();
                console.log(categories);
                $(categories).each(function () {
                    $categories.append('<ul>'+
                                        '<li class="categoryId">'+
                                            '<p>'+ `${this.categoryId}` +'</p>'
                                        + '</li>'+

                                        '<li class="categoryName">'+
                                            '<p>'+ `${this.categoryName}` +'</p>'
                                        + '</li>'+
                                        '<li class="parentId">'+
                                            '<p>'+ `${this.parentId}`+
                                                '<span id="'+ `${this.categoryId}` + '"class="bg-red delete hide">×</span>'+
                                            '</p>'
                                        + '</li>'+
                                    '</ul>');
                })
            }
        });
    }
);
$("#addCategory").click(
    function () {
        $.ajax({
            success : function () {
                let $buttons = $("#savebtn");
                clearAll();
                $buttons.removeClass("hide");
                $buttons.before('<ul id="inputsCreate">'+
                                      '<li class="categoryName width50p">'+
                                            '<input id="categoryName" type="text" placeholder="Category name">'+'</input>'
                                    + '</li>'+
                                    '<li class="parentId width50p">'+
                                            '<input id="parentId" type="text" placeholder="Parent id">' +'</input>'
                                    + '</li>'+
                                '</ul>');
            }

        });
    }
);
$(".cancel").click(function () {
    $.ajax({
        success : function () {
            clearAll();
        }
    });
});
$("#save").click(function () {
    let name = $("#categoryName").val();
    let pId = $("#parentId").val();
    let obj = new Category(name,pId);
    let jsonObj = JSON.stringify(obj);
    $.ajax({
        url: "/saveCategory",
        method: "post",
        contentType: "application/json",
        data : jsonObj,
        success : function () {
            console.log(jsonObj);
            clearAll();
        }
    });
});
$("#deleteCategory").click(function () {
    clearAll();
    $(".delete").removeClass("hide");
    $("#deletebtn").removeClass("hide");
});
$(document).on('click','.delete',function () {
    $(this).parent().parent().parent().addClass("bg-blue");
    $(this).attr("delete","true");
});
$(document).on('dblclick','.delete',function () {
    $(this).parent().parent().parent().removeClass("bg-blue");
    $(this).attr("delete","false");
});
$("#save2").click(function () {
    let indexes=[];
    $(".delete").each(function () {
        if ($(this).attr("delete")== "true"){
            indexes.push($(this).attr("id"))
        }
    });
    if (indexes.length <= 0) return; else {
        let jsonIndexes = JSON.stringify(indexes);
        $.ajax({
            url : "/deleteCategory",
            method : "post",
            contentType: "application/json",
            data : jsonIndexes,
        });
        clearAll();
    }
});
function clearAll() {
    let $buttons = $("#savebtn");
    $buttons.addClass("hide");
    $buttons.prev("#inputsCreate").empty();
    $(".delete").addClass("hide");
    $("#deletebtn").addClass("hide");
    $("main ul").removeClass("bg-blue");
    $(".delete").attr("delete","false");
}
