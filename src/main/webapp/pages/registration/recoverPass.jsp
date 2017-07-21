<%--
  Created by IntelliJ IDEA.
  User: blackhaski
  Date: 21.07.17
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../templates/header.jsp" %>
<div id="recoverCont">
    <div class="headContact width50p margin-auto text-align-c">
        <h1 class="color-white">Recover password!</h1>
    </div>
    <div id="recoverPass" class="contactForm margin-auto width79p">
        <h2 class="headInp clear-b">Username</h2>
        <input id="usernameRec" class="float-l width100p" type="text">
        <h2 class="headInp clear-b">Email</h2>
        <input id="emailRec" class="float-l width100p" type="email">
        <div class="text-align-c">
            <input id="sendRecover" type="button" value="SEND">
        </div>
    </div>
</div>
<script src="/js/registration.js"></script>
<%@include file="../templates/footer.jsp" %>
