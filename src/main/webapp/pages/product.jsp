<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/header.jsp" %>
<div class="margin-l-2p">
    <div class="width100p prodDesc float-l">
        <div class="float-l width62p">
            <p class="prodImg">
                <img id="mainProductImg" width="826px" height="550px">
                <div class="ratingImg text-align-c display-i-b">
                    <img name="posRatingImg" src="/images/goodRating.png" width="30px" height="30px">
                    <p id="posRating" class="mar0-pad0">${posRating}</p>
                </div>
                <div class="ratingImg text-align-c display-i-b">
                    <img name="negRatingImg" src="/images/badRating.png" width="30px" height="30px">
                    <p id="negRating" class="mar0-pad0">${negRating}</p>
                </div>
            </p>
            <div id="productImagesContainer" class="width100p margin-top-510px text-align-c">
                    <img width="12%" class="margin-l-2p margin-r-2p" height="67px">
            </div>
        </div>
        <div class="margin-l-2p float-l width34p">
            <div id="prodInfo" class="float-l margin-l-2p">
                <h1 id="productNameH" class="color-white"></h1>
                <h2 id="productPriceH" class="color-green"></h2>
                <sec:authorize access="hasRole('ADMIN')">
                    <select id="productCategory">
                        <option value="default">Select category</option>
                    </select>
                </sec:authorize>
                <p id="countProductP"></p>
                <p id="soldOut"></p>
                <ul class="characters">
                </ul>
                <p id="productDescrP">
                </p>
                <div class="height-22px">
                    <span class="color-gray height-auto float-l font-size-14px mar0-pad0 margin-r-2p">COUNT</span>
                    <input id="countProduct" type="number" class="float-l width20p height-auto margin-l-2p font-size-14px" value="1" min="1" max="50">
                </div>
                <button id="addToCart" class="addBtn clear-b">ADD TO CART</button>
            </div>
        </div>
    </div>
</div>
<div id="commentsBlock" class="width100p clear-b height-auto">
    <div name="comment" class="margin-auto clear-b height-auto width80p">
        <h2 name="usernameCommentator" data-idcomment="${comm.commentId}" class="float-l width20p color-green">
            ${comm.user.username}
        </h2>
        <p name="commentMessage" class="float-l width60p font-size-18px color-gray">
            ${comm.text}
        </p>
        <p name="timeComment" class="float-r text-align-r width10p color-white">
            ${comm.date}
        </p>
    </div>
    <c:forEach var="comm" items="${comments}">
        <div name="comment" class="margin-auto clear-b height-auto width80p">
            <h2 name="usernameCommentator" data-idcomment="${comm.commentId}" class="float-l width20p color-green">
                    ${comm.user.username}
            </h2>
            <p name="commentMessage" class="float-l width60p font-size-18px color-gray">
                    ${comm.text}
            </p>
            <p name="timeComment" class="float-r text-align-r width10p color-white">
                    ${comm.date}
            </p>
        </div>
    </c:forEach>
</div>
<div class="margin-l--2p clear-b">
    <div class="width100p prodDesc float-l">
        <form class="contactForm margin-auto width79p">
            <h2 class="headInp clear-b">Comment</h2>
            <textarea cols="132" rows="6" id="commentText"></textarea>
            <div class="text-align-c">
                <input id="sendComment" type="button" value="COMMENT">
            </div>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}">
        </form>
    </div>
</div>
<script src="/resources/sockjs-client-0.3.4.js"></script>
<script src="/resources/stomp-js.js"></script>
<script src="/js/comment.js"></script>
<script src="/js/functions.js"></script>
<script src="/js/product.js"></script>
<%@include file="templates/footer.jsp" %>