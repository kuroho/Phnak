<jsp:include page="header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
<div class="product-list" role="listbox">
    <div class="container">
        <div id="carouselContent" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner" role="listbox">
                <div class="carousel-item active text-center p-4">
                    <h1>Bienvenue à la Phnak</h1>
                </div>
                <div class="carousel-item text-center p-4">
                    <h1>Une liste d'articles infinie!</h1>
                </div>
                <div class="carousel-item text-center p-4">
                    <h1>Des cadeaux pour vous et vos amis!</h1>
                </div>
                <div class="carousel-item text-center p-4">
                    <h4>Code VIP20 pour obtenir une réduction de 20% sur votre prochaine commande!</h4>
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselContent" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselContent" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <div class="row px-5 mt-1">
        <c:forEach var="p" items="${SimpleProduct}" varStatus="loop">
        <div class="col-md-3">
            <div class="card card-body mb-2">
                <form action="DescriptifProductServlet" method="POST">
                    <input type="hidden" name="id_produit" value="${p.idProduit}">
                    <input type="image" class="align-self-center" alt="image produit" src="images/${p.idProduit}.jpg" height=200>
                </form>

                <a href="DescriptifProductServlet?id_produit=${p.idProduit}"><h4 class="card-title"><c:out value="${p.nomProduit}"/></h4></a>
                <span id="ProductMarque" class="bold"><c:out value="${p.nomMarque}"/></span><br>
                <span id="ProductPrice" class="bold"><c:out value="${p.prixProduit}"/>&euro;</span><br>

                <form action="AddProductPanierServlet" method="POST">
                    <input type="hidden" name="id_produit" value="${p.idProduit}">
                    <button class="btn btn-success mt-2 align-self-end" type="submit"><span class="fas fa-cart-plus"></span> AJOUTER AU PANIER</button>
                </form>
            </div>
        </div>
        </c:forEach>
    </div>
</div>
</body>
</html>