"use strict";

$(document).ready(function () {
        $.ajax({
            url: "/show",
            type: "GET",
            success: function (categories) {
                console.log(categories);
            },
            error: function () {
                console.log("error");
            }
        })
    }
);