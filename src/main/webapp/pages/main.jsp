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
        <sec:authorize access="hasRole('ADMIN')">
            <button class="login-btn width50p bg-black">
                <a href="createProductPage" class="width100p display-b">Add Product</a>
            </button>
        </sec:authorize>
        <form action="searchProducts" method="post" class="float-r padding-r-5p">
            <input type="search" name="key" required class="log-in-inp" placeholder="Enter product name">
            <input type="submit" value="Search" class="login-btn">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}">
        </form>
    </div>
    <div id="currentProductList" class="width100p display-i-b">
        <c:if test="${not empty currentProducts}">
            <div class="width100p">
                <form action="/sortProducts" method="post">
                    <p class="float-l mar0-pad0 color-white margin-r-2p">Sort by:</p>
                    <select name="column" class="float-l margin-r-2p" >
                        <option value="date">Date</option>
                        <option value="productName">Name</option>
                        <option value="price">Price</option>
                        <option value="rebate">Rebate</option>
                    </select>
                    <select name="direction" class="float-l margin-r-2p">
                        <option value="DESC">DESC</option>
                        <option value="ASC">ASC</option>
                    </select>
                    <input class="margin-r-2p color-green" type="submit" value="Sort">
                    <input type="hidden"
                                       name="${_csrf.parameterName}"
                                       value="${_csrf.token}">
                </form>
            </div>
            <c:forEach var="product" items="${currentProducts}">
                <div class="width30p float-l margin-l-2p">
                    <a href="product-${product.productName}"
                       class="width100p display-b text-decor-none good height-auto">
                        <img src="${product.images[0]}">
                        <p>Name: ${product.productName}</p>
                        <h2>${product.price}</h2>
                        <c:if test="${product.rebate gt 0}">
                            <h2 class="color-green">REBATE:${product.rebate*100}%</h2>
                        </c:if>
                    </a>
                    <sec:authorize access="hasRole('ADMIN')">
                        <div class="clear-b text-align-c">
                            <span name="deleteProduct" data-productName="${product.productName}" class="color-green">Delete</span>
                        </div>
                    </sec:authorize>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty currentProducts}">
            <div class="width100p height-auto text-align-c margin-top-30px">
                <img src="/images/empty.png" width="80%" height="450px">
            </div>
        </c:if>
    </div>
    <div>
        <c:forEach var="page" step="${1}" begin="${1}" end="${pages}">
            <a href="/page?number=${page}" onclick="beforeUnloadFalse()"
               class="float-l margin-l-2p text-decor-none color-white">${page}</a>
        </c:forEach>
    </div>

</main>
<script src="/js/functions.js"></script>
<script src="/js/main.js"></script>
<script src="/js/product.js"></script>
<%@include file="templates/footer.jsp" %>