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
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <link rel="stylesheet" type="text/css" href="/css/about.css">
    <link rel="stylesheet" type="text/css" href="/css/contacts.css">
    <link rel="stylesheet" type="text/css" href="/css/registration.css">
    <link rel="stylesheet" type="text/css" href="/css/admin.css">
    <title>Main</title>
</head>
<body class="mar0-pad0 width100p height-auto">

<header class="logo width94p bg-black">
    <div class="width30p float-l">
        <a href="/">
            <img src="../../images/logo.png" height="55px" width="55px" class="display-i-b float-l"/>
        </a>
        <h1 class="mar0-pad0"><a href="/" class="color-white text-decor-none">Internet-Shop</a></h1>
    </div>
    <div class="float-r width70p">
        <form class=" mar0-pad0 margin-r-2p float-r">
            <input type="text" class="log-in-inp" placeholder="Login">
            <input type="password" class="log-in-inp" placeholder="Password">
            <input type="submit" class="login-btn" value="Ввійти">
        </form>
    </div>
</header>
<div class="content width90p height-auto margin-auto ">
    <nav class="border border-radius bg-white margin-bottom-1p">
        <ul class="float-l mar0-pad0 width55p" style="margin-left: 6px">
            <li class="head-nav-li border-r width20p">
                <a href="/" class="link text-decor-none font-size-18px">
                    Catalog
                </a>
            </li>
            <li class="head-nav-li border-r width20p">
                <a href="/about" class="link text-decor-none font-size-18px">
                    About shop
                </a>
            </li>
            <li class="head-nav-li border-r width20p">
                <a href="/contacts" class="link text-decor-none font-size-18px">
                    Contacts
                </a>
            </li>
            <li class="head-nav-li bg-red border-r width20p">
                <a href="/registration" class="link text-decor-none color-white font-size-18px">
                    Registration
                </a>
            </li>
        </ul>
        <form class="float-r width30p text-align-r padding-r-5p">
            <input type="search" class="log-in-inp" placeholder="Search">
            <a href="#"><img src="../images/search.png" height="17px" width="17px"></a>
        </form>
    </nav>
</html>