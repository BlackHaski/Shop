<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<body>
<%@include file="templates/header.jsp" %>
<aside class="float-l border box-sizing-box clear-b text-align-l width20p mar0-pad0 height-auto bg-white">
    <nav class="height-auto">
        <ul class="mar0-pad0 height-auto left-nav">
            <li class="aside-nav-li mar0-pad0 border-b">
                <div class="section display-i-b width100p">
                    <a id="aCategory"
                       class="link width90p padding-l-10p float-r font-size-14px text-decor-none color-white bg-black">
                        Categories
                    </a>
                </div>
            </li>
        </ul>
    </nav>
</aside>
<main class="float-r margin-bottom-1p border border-radius box-sizing-box height-auto bg-white width79p hide">
    <div id="buttons" class="width100p">
        <button id="addCategory" class="float-l bg-green button font-size-18px">Add category</button>
        <button id="deleteCategory" class="float-l bg-red button font-size-18px">Delete category</button>
        <button id="changeCategory" class="float-l bg-yellow button font-size-18px">Change name category</button>
        <button id="moveCategory" class="float-l bg-blue button font-size-18px">Move category</button>
    </div>
    <ul class="bg-black">
        <li>
            <p>Category id</p>
        </li>
        <li>
            <p>Category name</p>
        </li>
        <li>
            <p>Parent Id</p>
        </li>
    </ul>
    <div id="categories" class="width100p clear-b text-align-c margin-auto">
    </div>
    <div id="savebtn" class="clear-b margin-auto text-align-c hide">
        <button id="save" class="button bg-green height-22px border-r">Save</button>
        <button class="cancel button bg-red height-22px border-r">Cancel</button>
    </div>
    <div id="deletebtn" class="clear-b margin-auto text-align-c hide">
        <button id="save2" class="button bg-green height-22px border-r">Ok</button>
        <button class="cancel button bg-red height-22px border-r">Cancel</button>
    </div>
    <div id="changename" class="clear-b margin-auto text-align-c hide">
        <button id="save3" class="button bg-green height-22px border-r">Ok</button>
        <button class="cancel button bg-red height-22px border-r">Cancel</button>
    </div>
</main>
<script src="/js/admin.js"></script>
</body>
</html>