<jsp:include page="header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
    <div class="container">
        <c:if test="${param.invalidcode==true}">
            <div class="alert alert-danger" role="alert"><c:out value="Le code promo utilisé est invalide."/></div>
        </c:if>
        <div class="card mb-xl-5 mt-xl-5 c">
            <div class="row card-body">
                <div class="col-sm">
                    <c:if test="${pourcentagePromo==null || pourcentagePromo==0 }">
                        <form action="CodePromoPanierServlet" method="POST">
                            <input type="hidden" name="id_produit" value="${pan.idProduit}">
                            <input type="text" name="code_promo" placeholder="SAISIR CODE">
                            <button class="btn btn-success" type="submit" name="promoCode">VALIDER</button>
                        </form>
                    </c:if>

                    <c:if test="${pourcentagePromo!=null}">
                        <span id="Promo" class="bold" > CODE PROMO APPLIQUE : <c:out value="${nomPromo}"/> = <c:out value="${pourcentagePromo}"/>%</span><br>
                        <span id="textepromo" class="bold">AVEC PROMO : </span><br>
                        <span id="ProductPrix2" class="bold"><c:out value="${prixPanierPromo}"/> &euro;</span><br>
                    </c:if>
                </div>
                <div class="col-sm text-center">
                    <c:if test="${Panier.calculerPanier()!=null}">
                        <h4 id="ProductPrix"><c:out value="${Panier.calculerPanier()}"/> &euro;</h4>
                    </c:if>
                </div>

                <div class="col-sm">
                    <a href="ListSimpleProductsServlet"><button class="btn btn-success">CONTINUER LES ACHATS</button></a>

                    <c:if test="${Panier.getPanier() != null}">
                        <a href="PaiementServlet"><button class="btn btn-success">PAIEMENT</button></a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <div class="panier-list" role="listbox">
        <div class="product-row">
            <c:forEach var="pan" items="${Panier.getPanier().keySet()}" varStatus="loop">
            <div class="col-md-3" style="float:left">
                <div class="card mb-2">
                    <div class="card-body">
                        <img alt="image produit" src="images/${pan.idProduit}.jpg" height=200 class="d-flex justify-content-center"><br>
                        <h4 class="card-title"><c:out value="${pan.nomProduit}"/></h4>
                        <span id="ProductMarque2" class="bold" ><c:out value="${pan.nomMarque}"/></span><br>
                        <span id="ProductPrice2" class="bold"><c:out value="${pan.prixProduit}"/>&euro;</span><br>
                        <span id="ProductNb" class="bold"><c:out value="${Panier.getPanier().get(pan)}"/> Produit dans le panier</span><br>
                        <div class="row mt-2">
                            <form action="UpdateProductPanierServlet" class="px-2" method="POST">
                                <input type="hidden" name="id_produit" value="${pan.idProduit}">
                                <select name="quantitemaj">
                                    <option selected>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select>
                                <button class="btn btn-success" type="submit" name="quantitemaj">METTRE A JOUR LE PANIER</button>
                            </form>
                            <form action="RemoveProductPanierServlet" method="POST">
                                <input type="hidden" name="id_produit" value="${pan.idProduit}">
                                <button class="btn btn-success" type="submit" name="supp">SUPPRIMER</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>
