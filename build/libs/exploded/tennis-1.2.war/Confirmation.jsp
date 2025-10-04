
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/includes/header.jsp">
  <jsp:param name="credentials" value="true"/>
</jsp:include>

<div class="container-fluid m-3">
  <div class="row border my-title">
    <h1>Confirmation d'inscription</h1>
  </div>

  <div class="row mt-3">
    <p>Votre inscription a bien été enregistrée.</p>
    <a href="index.jsp" class="btn btn-primary">Retour au menu</a>
  </div>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
