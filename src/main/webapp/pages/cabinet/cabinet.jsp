<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../templates/header.jsp" %>
<c:if test="${canEdit == true}">
    <aside class="float-l box-sizing-box clear-b text-align-l width20p mar0-pad0 height-auto">
        <nav class="height-auto">
            <ul class="mar0-pad0 height-auto left-nav">
                <li class="aside-nav-li mar0-pad0">
                    <a id="profile"
                       class="link width90p margin-0 padding-l-10p float-r font-size-14px text-decor-none color-white bg-black">
                        Profile
                    </a>
                </li>
                <li class="aside-nav-li mar0-pad0">
                    <a id="editProfile"
                       class="link width90p margin-0 padding-l-10p float-r font-size-14px text-decor-none color-white bg-black">
                        Edit Profile
                    </a>
                </li>
            </ul>
        </nav>
    </aside>
</c:if>
<div id="container" class="float-r margin-bottom-1p box-sizing-box height-auto bg-black width80p">
</div>
<script src="/js/cabinet.js"></script>
<%@include file="../templates/footer.jsp" %>
