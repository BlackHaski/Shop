$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

var realCode;
var usrnm;
$("#sendRecover").click(function () {
    let username = $("#usernameRec").val();
    usrnm = username;
    let email = $("#emailRec").val();
    let arr = new Array();
    arr.push(username);
    arr.push(email);
    let jsonArr = JSON.stringify(arr);
    $.ajax({
        url: "/checkUser",
        method: "post",
        contentType: "application/json",
        data: jsonArr,
        success: function (code) {
            if (code == 0)
                alert("User is not Exist!");
            else {
                realCode = code;
                $(".headContact h1").text("Enter Code!");
                $("#recoverPass").empty();
                $("#recoverPass").html("<h2 class=\"headInp clear-b\">Code</h2>\n" +
                    "        <input id=\"code\" class=\"float-l width100p\" type=\"email\">\n" +
                    "        <div class=\"text-align-c\">\n" +
                    "            <input id=\"sendCode\" type=\"button\" value=\"SEND\">\n" +
                    "        </div>");
            }
        }
    });
});

$(document).on("click", "#sendCode", function () {
    let inpCode = $("#code").val();
    if (inpCode == realCode) {
        $(".headContact h1").text("Enter new Password!");
        $("#recoverPass").empty();
        $("#recoverPass").html("<h2 class=\"headInp clear-b\">New Password</h2>\n" +
            "<input id=\"newPass\" type=\"password\" class=\"width100p\">\n" +
            "<h2 class=\"headInp clear-b\">Check Password</h2>\n" +
            "<input id=\"checkNewPassword\" type=\"password\" class=\"width100p\">\n" +
            "<div class=\"text-align-c\">\n" +
            "<input id='changePass' type=\"button\" value=\"CHANGE\">\n" +
            "</div>");
    }
    else alert("bad");
    console.log(realCode, " ", inpCode)
});

$(document).on("click", "#changePass", function () {
    let pass = $("#newPass").val();
    let checkPass = $("#checkNewPassword").val();
    if (pass == checkPass) {
        let params = new Array();
        params.push(pass);
        params.push(usrnm);
        let jsonParams = JSON.stringify(params);
        $.ajax({
            url: "/changePassword",
            method: "post",
            contentType: "application/json",
            data: jsonParams,
            success: function () {
                $("#recoverPass").empty();
                $("#recoverPass").html("<p>"+"Done"+"</p>");
            }
        });
    } else {
        alert("Password != checkPassword");
    }
});