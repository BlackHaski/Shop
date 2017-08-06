<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/header.jsp" %>
<div class="width92p margin-l-2p margin-top-30px">
    <h1 class="color-white font-size-20px">Shopping Cart</h1>
    <div class="headCart">
        <div class="float-l width70p">
            <p>ITEM</p>
        </div>
        <div class="float-l width20p">
            <p>COUNT</p>
        </div>
        <div class="float-l width10p">
            <p>PRICE</p>
        </div>
    </div>
    <c:forEach var="product" items="${cartProducts}">
        <div class="clear-b border-t productCart border-b">
            <div class="height-auto width5p float-l">
                <p id="deleteProductFromCart" class="float-l mar0-pad0">×</p>
            </div>
            <div class="height-auto width65p float-l">
                <img src="${product.key.images.get(0)}" class="float-l" height="70px" width="100px">
                <h1 class="mar0-pad0">${product.key.productName}</h1>
            </div>
            <div class="float-l width20p height-auto">${product.value}</div>
            <div class="float-l width10p height-auto">${product.key.price}</div>
        </div>
    </c:forEach>
    <div class="clear-b float-r subtotal">
        <div>
            <p class="float-l">Subtotal </p>
            <p class="float-r">7000$</p>
        </div>
        <button class="clear-b">CHECKOUT</button>
    </div>
</div>
<%@include file="templates/footer.jsp" %>