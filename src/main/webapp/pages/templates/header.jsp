<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="eng">
<head>
    <meta charset="UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/about.css">
    <link rel="stylesheet" type="text/css" href="/css/contacts.css">
    <link rel="stylesheet" type="text/css" href="/css/admin.css">
    <link rel="stylesheet" type="text/css" href="/css/product.css">
    <link rel="stylesheet" type="text/css" href="/css/shopcart.css">
    <title>Main</title>
</head>
<body class="mar0-pad0 width100p height-auto">

<header class="logo width94p bg-black">
    <div class="float-l width40p">
        <div class="text-align-c display-i-b">
            <a id="catalogPage" href="/" class="link text-decor-none font-size-14px float-l">
                Catalog
            </a>
            <a id="aboutPage" href="/about" class="link text-decor-none font-size-14px float-l">
                About shop
            </a>
            <a id="contactPage" href="/contact" class="link text-decor-none font-size-14px float-l">
                Contact
            </a>
        </div>
    </div>
    <div class="width20p float-l text-align-c">
        <a href="/" class="display-i-b">
            <img src="http://static1.squarespace.com/static/589ba404414fb513e70016e4/t/58abb1261b631ba9d7adaa4f/1499818589218/%3Fformat=1500w" height="40px" width="40px" class="display-i-b float-l"/>
        </a>
    </div>
    <div class="float-r width40p">
        <sec:authorize access="isAnonymous()">
            <div class="mar0-pad0 margin-r-2p float-r">
                <button id="toLoginPage" type="submit" class="login-btn bg-black">
                    <a  href="<c:url value="/login"/>">Ввійти</a>
                </button>
                <button id="toRegPage" type="submit" class="login-btn bg-black">
                    <a href="<c:url value="/registration"/>">Зареєструватися</a>
                </button>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </div>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a id="shopCartPage" href="/shopcart" class="link text-decor-none font-size-14px float-r">
                CART
            </a>
            <form action="/logout" method="post" class="mar0-pad0 margin-r-2p float-r">
                <button type="submit" class="login-btn bg-black">
                    <a>EXIT</a>
                </button>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
            </form>
            <sec:authentication var="user" property="principal"></sec:authentication>
            <form action="/cabinet-${user.username}" method="get" class="mar0-pad0 margin-r-2p float-r">
                <button type="submit" class="login-btn bg-black">
                    <a>CABINET</a>
                </button>
            </form>
        </sec:authorize>
    </div>
</header>
<div class="content width98p margin-l-2p height-auto">
</html>