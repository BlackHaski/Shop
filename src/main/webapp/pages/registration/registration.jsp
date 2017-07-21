<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../templates/header.jsp" %>

<div id="regCont" class="width100p">
    <c:if test="${!isLogin}">
        <div class="height-auto width100p margin-l--2p mar0-pad0">
            <div class="headContact width50p margin-auto text-align-c">
                <h1 class="color-white">Sign up</h1>
                <p>
                    Sign up and get more opportunities!
                </p>
            </div>
            <form action="/regUser" method="post" class="contactForm margin-auto width79p">
                <h2 class="headInp">Name</h2>
                <input name="username" type="text" class="float-l width100p">
                <h2 class="headInp clear-b">Email Address</h2>
                <input name="email" type="text" class="width100p">
                <h2 class="headInp clear-b">Birthday</h2>
                <input name="birthday" type="date" class="width100p">
                <h2 class="headInp clear-b">Password</h2>
                <input name="password" type="password" class="width100p">
                <h2 class="headInp clear-b">Check Password</h2>
                <input name="checkPassword" type="password" class="width100p">
                <div class="text-align-c">
                    <input type="submit" value="SIGN UP">
                </div>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}">
            </form>
        </div>
    </c:if>

    <c:if test="${isLogin}">
        <div class="headContact width50p margin-auto text-align-c">
            <h1 class="color-white">Sign in</h1>
            <p>
                Sign in and get more opportunities!
            </p>
        </div>
        <form method="post" class="contactForm margin-auto width79p">
            <h2 class="headInp clear-b">Username</h2>
            <input name="username" class="float-l width100p" type="text">
            <h2 class="headInp clear-b">Password</h2>
            <input name="password" class="float-l width100p" type="password">
            <div class="text-align-c">
                <input type="submit" value="SIGN IN">
            </div>
            <div class="text-align-c">
                <a id="forgot" href="/recoverPass" class="color-white">Forgot Password?</a>
            </div>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}">
        </form>
    </c:if>
</div>
<%@include file="../templates/footer.jsp" %>