<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/header.jsp" %>
<aside class="float-l box-sizing-box clear-b text-align-l width20p mar0-pad0 height-auto bg-black">
    <nav class="height-auto">
        <ul class="mar0-pad0 height-auto left-nav">
            <li class="aside-nav-li mar0-pad0">
                <a class="link width90p margin-0 padding-l-10p float-r font-size-14px text-decor-none color-white bg-black">
                </a>
            </li>
        </ul>
    </nav>
</aside>

<main class="float-r margin-bottom-1p box-sizing-box height-auto bg-black width80p">
    <div class="width100p display-i-b text-align-c">
        <button class="login-btn width50p bg-black">
            <a href="createProductPage" class="width100p display-b">Add Product</a>
        </button>
    </div>
    <div class="width100p display-i-b ">
        <c:if test="${currentProducts != null}">
            <c:forEach var="product" items="${currentProducts}">
                <a href="product-${product.productName}" class="width30p display-b text-decor-none good height-auto float-l">
                    <img src="${product.images[0]}">
                    <p>Name: ${product.productName}</p>
                    <h2>${product.price}</h2>
                </a>
            </c:forEach>
        </c:if>
    </div>

</main>
<script src="/js/functions.js"></script>
<script src="/js/main.js"></script>
<%@include file="templates/footer.jsp" %>