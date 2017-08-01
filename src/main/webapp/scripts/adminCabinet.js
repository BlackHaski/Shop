$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

function Category(id,categoryName,parentId) {
    this.categoryId = id;
    this.categoryName = categoryName;
    this.parentId = parentId;
}
function showCategories() {
    $("#adminContent").empty();
    $("#categories").empty();

    $("#adminContent").load("/editCategory",function () {
        $.ajax({
            url: "/show",
            method: "get",
            success: function (categories) {
                $("main").removeClass("hide");
                let result = sort(categories,categories[0]);
                $(result).each(function () {
                    $("#categories").append('<ul class="categoryList">'+
                        '<li class="categoryId">'+
                        '<p>'+ `${this.categoryId}` +'</p>'
                        + '</li>'+

                        '<li class="categoryName">'+
                        '<p>'+ `${this.categoryName}` +'</p>'
                        + '</li>'+
                        '<li class="parentId">'+
                        '<p>'+ `${this.parentId}`+
                        '<span id="'+ `${this.categoryId}` + '"class="bg-black delete hide">×</span>'+
                        '</p>'
                        + '</li>'+
                        '</ul>'
                    );
                });
            }
        });
    });
}
$(document).on("click","#addCategory",
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
$(document).on("click",".cancel",function () {
    $.ajax({
        success : function () {
            clearAll();
        }
    });
});
$(document).on("click","#save",function () {
    let name = $("#categoryName").val();
    let pId = $("#parentId").val();
    let obj = new Category(0,name,pId);
    let jsonObj = JSON.stringify(obj);
    $.ajax({
        url: "/saveCategory",
        method: "post",
        contentType: "application/json",
        data : jsonObj,
        success : function () {
            showCategories();
            console.log(jsonObj);
            clearAll();
        }
    });
});
$(document).on("click","#deleteCategory",function () {
    clearAll();
    $(".delete").removeClass("hide");
    $("#deletebtn").removeClass("hide");
});
var counter = 0;
$(document).on('click','.delete',function () {
    if (counter == 0) {
        $(this).parent().parent().parent().addClass("bg-red");
        $(this).attr("delete", "true");
        counter++;
    }else {
        $(this).parent().parent().parent().removeClass("bg-red");
        $(this).attr("delete", "false");
        counter = 0;
    }
});
$(document).on("click","#save2",function () {
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
            success : function () {
                clearAll();
                showCategories();
            }
        });}
});

var $copyCategLi;
$(document).on('dblclick',".categoryName,.parentId",function () {
    clearAll()
    $("#changename").removeClass("hide");
    $copyCategLi = $(this).clone(true, true);
    let myPlaceholder;
    if ($(this).hasClass("categoryName")){
        myPlaceholder = "Enter name";
    }else if ($(this).hasClass("parentId")){
        myPlaceholder = "Enter parent id";
    }
    $(this).children().replaceWith($('<input/>', {type: 'text', placeholder: myPlaceholder}));
});
$(document).on('keypress',"ul li input",function (event) {
   if (event.keyCode == 13){
       if($copyCategLi.hasClass("categoryName")) {
           let $name = $(this).val();
           let $pId = $(this).parent().next().text();
           let $id = $(this).parent().prev().text();
           clearAll();
           let category = new Category($id, $name, $pId[0]);
           let jsonObj = JSON.stringify(category);
           if ($name != "") {
               console.log(jsonObj);
               $copyCategLi.children("p").text($name);
               $(this).parent().replaceWith($($copyCategLi).clone(true, true));
               $.ajax({
                   url: "/changeName",
                   method: "post",
                   contentType: "application/json",
                   data: jsonObj
               });
               clearAll();
           }
       }else if ($copyCategLi.hasClass("parentId")){
           let $pId = $(this).val();
           let $name = $(this).parent().prev().text();
           let $id = $(this).parent().prev().prev().text();
           clearAll();
           if ($pId != "") {
               let category = new Category($id, $name, $pId[0]);
               let jsonObj = JSON.stringify(category);
               console.log(jsonObj);
               $copyCategLi.children("p").text($pId);
               $(this).parent().replaceWith($($copyCategLi).clone(true, true));
               $.ajax({
                   url: "/changeParentId",
                   method: "post",
                   contentType: "application/json",
                   data: jsonObj
               });
               clearAll();
           }
       }
   }
});

function clearAll() {
    let $buttons = $("#savebtn");
    $buttons.addClass("hide");
    $buttons.prev("#inputsCreate").empty();
    $(".delete").addClass("hide");
    $("#deletebtn").addClass("hide");
    $("main ul").removeClass("bg-red");
    $(".delete").attr("delete","false");
    $(".categoryList li:nth-child(2)").removeClass("bg-green");
    $("#changename").addClass("hide");
    $("ul li input").parent().replaceWith($($copyCategLi));
}
