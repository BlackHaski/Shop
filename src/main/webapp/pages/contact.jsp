<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/header.jsp" %>
<div class="height-auto width100p margin-l--2p mar0-pad0">
    <div class="headContact width50p margin-auto text-align-c">
        <h1 class="color-white">Contact Us</h1>
        <p>
            We enjoy suggestions, questions, feedback, work inquiries, and even a simple hello!
            We will do our best to respond as soon as possible.
        </p>
    </div>
    <form action="/sendMail" method="post" class="contactForm margin-auto width79p">
        <h1 class="color-white">Send email</h1>

        <h2 class="headInp">Name</h2>
        <input name="username" type="text" class="float-l width100p">

        <h2 class="headInp clear-b">Email Address</h2>
        <input name="email" type="email" class="width100p">

        <h2 class="headInp clear-b">Password</h2>
        <input name="password" type="password" class="width100p">

        <h2 class="headInp clear-b">Message</h2>
        <textarea name="message" cols="129" rows="8"></textarea>

        <div class="text-align-c">
            <input type="submit" value="SEND EMAIL">
        </div>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}">
    </form>
</div>
<%@include file="templates/footer.jsp" %>