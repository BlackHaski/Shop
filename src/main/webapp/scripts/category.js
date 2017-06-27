"use strict";

$(document).ready(function () {
        $.ajax({
            url: "/show",
            type: "GET",
            success: function (categories) {
                for (var i = 0; i < categories.length; i++) {
                    var category = categories[i];
                    let $cloneLi = $("aside nav li:first").clone(true,true);
                    $cloneLi.text(category[1]);
                    $("aside nav ul").append($cloneLi);

                }
            },
            error: function () {
                console.log("error");
            }
        })
    }
);