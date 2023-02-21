<jsp:include page="header-sign.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
    <main class="form-sign">
        <form method="post" action="SignUpServlet">
            <div class="text-center">
                <a href="ListSimpleProductsServlet"><img class="mb-4" src="images/logo.png" alt="" width="150" height="150"></a>
            </div>
            <h1 class="h3 mb-3 fw-normal">Rejoindre la Phnak</h1>
            <c:if test="${param.mail_existing==true}">
                <div class="alert alert-danger" role="alert"><c:out value="Cette adresse mail est déjà utilisée."/></div>
            </c:if>
            <div class="form-floating">
                <input type="email" name="email" class="form-control" id="floatingInput" placeholder="name@example.com" required>
                <label for="floatingInput">Adresse mail</label>
            </div>
            <div class="form-floating">
                <input type="password" name="password" class="form-control required" id="floatingPassword" placeholder="Mot de passe" required>
                <label for="floatingInput">Mot de passe</label>
            </div>
            <div class="form-checkbox">
                <label class="radio-inline">
                <input type="checkbox" name="man" id="floatingMonsieur" value="monsieur" checked>Monsieur</label>

                <label class="radio-inline">
                <input type="checkbox" name="woman" id="floatingMadame" value="madame">Madame</label>
            </div>
            <div class="form-floating">
                <input type="text" name="nom" class="form-control required" id="floatingNom" placeholder="name@example.com" required>
                <label for="floatingInput">Nom</label>
            </div>
            <div class="form-floating">
                <input type="text" name="prenom" class="form-control required" id="floatingPrenom" placeholder="Mot de passe" required>
                <label for="floatingPassword">Pr&eacute;nom</label>
            </div>
            <div class="form-floating">
                <input type="date" name="date_naissance" class="form-control required" id="floatingDate" placeholder="name@example.com" required>
                <label for="floatingDate">Adresse mail</label>
            </div>
            <div class="form-floating">
                <input type="text" name="telephone" class="form-control telephone required" id="floatingTelephone" placeholder="06xxxxxxxx" required>
                <label for="floatingInput">Telephone</label>
            </div>
            <button class="w-100 btn btn-lg btn-primary" id="submit" type="submit">S'inscrire</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2021</p>
        </form>
    </main>
</body>
</html>
<script src="js/signup.js"></script>