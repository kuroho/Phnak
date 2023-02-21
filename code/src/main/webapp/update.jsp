<jsp:include page="header.jsp"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body>
    <main class="form-sign">
        <form method="post" action="UpdateInformationClientServlet">
            <div class="text-center">
                <h1 class="h3 mb-3 fw-normal">Informations client</h1>
                <div class="col align-self-center">
                    <div class="form-floating">
                        <label>Nom</label>
                        <input type="text" name="nomClientUpdate" class="form-control required" value="${client.getNomClient()}" placeholder="name@example.com">
                    </div>
                    <div class="form-floating">
                        <label>Prenom</label>
                        <input type="text" name="prenomClientUpdate" class="form-control required" value="${client.getPrenomClient()}"  placeholder="Prenom">
                    </div>
                    <div class="form-floating mb-5">
                        <label>Telephone</label>
                        <input type="text" name="telephoneClientUpdate" class="form-control telephone required" value="${client.getTelephoneClient()}" placeholder="06xxxxxxxx">
                    </div>
                    <button class="w-100 btn btn-lg btn-primary" type="submit">Modifier les informations</button>
                    <p class="mt-5 mb-3 text-muted">&copy; 2021</p>
                </div>
            </div>
        </form>
    </main>
</body>
</html>
<script src="js/update.js"></script>