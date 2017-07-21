$(document).ready(function () {
        $.ajax({
            url: "/show",
            type: "GET",
            success: function (categories) {
                let result = sort(categories, categories[0]);
                for (let i = 0; i < result.length; i++) {
                    let category = result[i];
                    let clone = $("aside nav ul li:first-child").clone(true,true);
                    if (category.parentId == 0) clone.children().addClass("bg-red");
                    clone.children("a").text(category.categoryName);
                    $("aside nav ul").append(clone);
                }
                $("aside nav ul li:first-child").remove();
            },
            error: function () {
                console.log("error");
            }
        });
    }
);