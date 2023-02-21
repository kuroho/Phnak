<jsp:include page="header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
<div class="product-list" role="listbox">
    <div class="product-row">

        <div class="col-md-3" style="float:left">
            <div class="card mb-2">
                <div class="card-body">
                    <img alt="image produit" src="images/${Desc_Product.idProduit}.jpg" height=200 class="text-center"><br>
                    <h4 class="card-title"><c:out value="${Desc_Product.nomProduit}"/></h4>
                    <span id="ProductMarque" class="bold"><c:out value="${Desc_Product.nomMarque}"/></span><br>
                    <span id="ProductPrice" class="bold"><c:out value="${Desc_Product.prixProduit}"/>&euro;</span><br>
                    <a> DESCRIPTIF : </a><br>
                    <span id="ProductName" class="bold" ><c:out value="${Desc_Product.descriptifProduit}"/></span><br>
                </div>
            </div>
        </div>
    </div>
    <div class="product-row">
    </div>
</div>
</body>
</html>