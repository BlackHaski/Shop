<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/header.jsp" %>

<div class="margin-l-2p">
    <div class="width100p prodDesc float-l">
        <div class="float-l width62p"/>
            <p class="prodImg">
                <img src="/images/empty2.png" width="826px" height="550px">
                <img src="/images/goodRating.png" class="goodRatingImg" width="30px" height="30px">
                <img src="/images/badRating.png" class="badRatingImg" width="30px" height="30px">
            </p>
            <div class="width100p margin-top-580px text-align-c">
                <img src="/images/empty2.png" width="12%" height="67px">
                <img src="/images/empty2.png" width="12%" height="67px">
                <img src="/images/empty2.png" width="12%" height="67px">
                <img src="/images/empty2.png" width="12%" height="67px">
            </div>
        </div>
        <div class="margin-l-2p float-l width34p">
            <div class="float-l margin-l-2p">
                <h1 class="color-white">Name product</h1>
                <h2 class="color-green">Price</h2>
                <ul class="characters">
                    <li>Lorem: Ipsum</li>
                    <li>Lorem: Ipsum</li>
                    <li>Lorem: Ipsum</li>
                    <li>Lorem: Ipsum</li>
                </ul>
                <p>
                    Give yourself an edge over the competition with this custom line-based icon set that is essential
                    for every iOS app designer & developer. The icons follow the standard guidelines of Apple and work
                    perfectly for iOS apps. This set also includes a glyph version of the icons, perfect for selected
                    states within the iOS tab bar.
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

<%@include file="templates/footer.jsp" %>