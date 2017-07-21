<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<%@include file="../templates/header.jsp" %>

<aside class="float-l box-sizing-box clear-b text-align-l width20p mar0-pad0 height-auto bg-white">
    <nav class="height-auto">
        <ul class="mar0-pad0 height-auto left-nav">
            <li class="aside-nav-li mar0-pad0">
                <a id="aCategory" onclick="showCategories()" class="link width90p margin-0 padding-l-10p float-r font-size-14px text-decor-none color-white bg-black">
                    Category
                </a>
            </li>
        </ul>
    </nav>
</aside>

<main id="adminContent" class="float-r margin-bottom-1p box-sizing-box height-auto width79p hide"
</main>

<script src="/js/functions.js"></script>
<script src="/js/admin.js"></script>
</body>
</html>