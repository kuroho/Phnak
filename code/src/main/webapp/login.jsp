<jsp:include page="header-sign.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
<main class="form-sign">
    <form method="post" action="SignInServlet">
        <div class="text-center">
            <a href="ListSimpleProductsServlet"><img class="mb-4" src="images/logo.png" alt="" width="150" height="150"></a>
        </div>
        <h1 class="h3 mb-3 fw-normal">Connectez-vous</h1>
        <c:if test="${param.login_invalid==true}">
            <div class="alert alert-danger" role="alert"><c:out value="Cet utilisateur n'existe pas."/></div>
        </c:if>
        <c:if test="${param.password_invalid==true}">
            <div class="alert alert-danger" role="alert"><c:out value="Mot de passe incorrect."/></div>
        </c:if>
        <div class="form-floating">
            <input type="email" name="login" class="form-control" id="floatingInput" placeholder="nom@exemple.fr">
            <label for="floatingInput">Adresse mail</label>
        </div>
        <div class="form-floating">
            <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Mot de passe">
            <label for="floatingPassword">Mot de passe</label>
        </div>

        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> Se souvenir de moi
            </label>
        </div>
        <button class="w-100 btn btn-lg btn-primary mb-1" type="submit">Se connecter</button>
        <a href="SignUpServlet"><button class="w-100 btn btn-lg btn-primary">S'inscrire</button></a>
        <p class="mt-5 mb-3 text-muted">&copy; 2021</p>
    </form>
</main>
</body>
</html>