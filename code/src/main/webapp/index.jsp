<jsp:include page="header-sign.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
<main class="form-sign">
    <form method="post" action="ListSimpleProductsServlet">
        <c:if test="${param.command==true}">
            <div class="alert alert-success" role="alert"><c:out value="Votre commande a bien été passée."/></div>
        </c:if>
        <div class="text-center">
            <a href="ListSimpleProductsServlet"><img class="mb-4" src="images/logo.png" alt="" width="150" height="150"></a>
        </div>
        <h1 class="h3 mb-3 fw-normal text-center">BIENVENUE</h1>
        <button class="w-100 btn btn-lg btn-primary mb-1" type="submit">Accéder à la PHNAK</button>
    </form>
</main>
</body>
</html>