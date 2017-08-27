$(document).ready(function () {
        $.ajax({
            url: "/show",
            type: "GET",
            success: function (categories) {
                let result = sort(categories, categories[0]);
                for (let i = 0; i < result.length; i++) {
                    let category = result[i];
                    let clone = $("aside nav ul li:first-child").clone(true, true);
                    clone.children("a").text(category.categoryName);
                    let path = "category-" + category.categoryName;
                    clone.children("a").attr("href", path);
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