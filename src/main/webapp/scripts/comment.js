var stopmClient = null;
var username = null;

function connect() {
    var socket = new SockJS("/comment");
    stopmClient = Stomp.over(socket);
    stopmClient.connect({}, function (frame) {
        console.log(frame);
        username = frame.headers['user-name'];
        stopmClient.subscribe("/topic/product", function (res) {
            showComment(JSON.parse(res.body));
        })
    });
}

function disconnect() {
    stopmClient.disconnect();
}


$("#sendComment").click(function () {
    let params = {
        message: $("#commentText").val(),
        product: getProductName()
    };
    if (username == "" || username == null)
        showPopupWindow();
    else if ($("#commentText").val().length>0 && $("#commentText").val().split(' ').join("").length>0) {
        $("#commentText").val("");
        stopmClient.send("/app/comment", {}, JSON.stringify(params));
    }
});

function showComment(res) {
    // let commentBlock = $("div[name = 'comment']").first().clone(true, true);
    // $(commentBlock).children("h2").text(res.username);
    // $(commentBlock).children("h2").attr("data-idcomment",res.commentId);
    // let children = $(commentBlock).children("p");
    // $(children[0]).text(res.comment);
    // $(children[1]).text(res.date);
    // $(children[1]).append();
    // $("#commentsBlock").append(commentBlock);
    window.location.reload(true);
}
$(document).on("dblclick","div [name = 'comment'] p[name='commentMessage']",function () {
    $(this).parent().remove();
    $.ajax({
        url: "/deleteComment",
        method: "post",
        contentType: "text/plain",
        data: $(this).attr("data-idcomment"),
    });
});
