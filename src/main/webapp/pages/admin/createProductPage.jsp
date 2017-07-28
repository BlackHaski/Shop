<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../templates/header.jsp" %>
<div class="margin-l-2p">
    <div class="width100p prodDesc float-l">
        <form action="/createProduct" method="post" class="width48p margin-auto" enctype="multipart/form-data">

            <h3 class="headInp">Name</h3>
            <input name="prodNameInp" type="text" class="width100p">

            <select name="productCategory" id="productCategorySel" class="width100p">
                <option value="default">Select Category</option>
            </select>

            <h3 class="headInp">Count</h3>
            <input name="prodCountInp" type="text" class="width100p">

            <h3 class="headInp">Price</h3>
            <input name="prodPriceInp" type="text" class="width100p">

            <h3 class="headInp">Rebate</h3>
            <input name="rebateInp" type="text" class="width100p">

            <select name="productType" id="productTypeSel" class="width100p">
                <option value="default">Select Product Type</option>
            </select>
            <div id="individCharact" class="characters">
            </div>

            <h3 class="headInp">Description</h3>
            <textarea cols="75" rows="8" name="description"></textarea>

            <h3 class="headInp">Images</h3>
            <div>
            <input type="file" name="productImages" class="width100p">
            </div>
            <input id="createProductBtn" class="addBtn" type="submit" value="CREATE">

            <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}">
        </form>
    </div>
</div>
<script src="/js/adminProducts.js"></script>
<%@include file="../templates/footer.jsp" %>