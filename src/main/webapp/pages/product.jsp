<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/header.jsp" %>

<div class="margin-l-2p">
    <div class="width100p prodDesc float-l">
        <div class="float-l width62p">
            <p class="prodImg">
                <img id="mainProductImg" width="826px" height="550px">
                <div class="ratingImg text-align-c display-i-b">
                    <img src="/images/goodRating.png" width="30px" height="30px">
                    <p id="posRating" class="mar0-pad0"></p>
                </div>
                <div class="ratingImg text-align-c display-i-b">
                    <img src="/images/badRating.png" width="30px" height="30px">
                    <p id="negRating" class="mar0-pad0"></p>
                </div>
            </p>
            <div id="productImagesContainer" class="width100p margin-top-530px text-align-c">
                    <img width="12%" class="margin-l-2p margin-r-2p" height="67px">
            </div>
        </div>
        <div class="margin-l-2p float-l width34p">
            <div class="float-l margin-l-2p">
                <h1 id="productNameH" class="color-white"></h1>
                <h2 id="productPriceH" class="color-green"></h2>
                <ul class="characters">
                </ul>
                <p id="productDescrP">
                </p>
                <button class="addBtn">ADD TO CART</button>
            </div>
        </div>
    </div>
</div>

<div class="margin-l--2p clear-b">
    <div class="width100p prodDesc float-l">
        <form class="contactForm margin-auto width79p">
            <h2 class="headInp clear-b">Comment</h2>
            <textarea cols="132" rows="6"></textarea>
            <div class="text-align-c">
                <input type="submit" value="COMMENT">
            </div>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}">
        </form>
    </div>
</div>
<script src="/js/product.js"></script>
<%@include file="templates/footer.jsp" %>