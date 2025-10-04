<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.ensicaen.tennis.bean.Adherent" %>
<%@ page import="fr.ensicaen.tennis.bean.Inscription" %>
<%
  Adherent adherent = (Adherent) request.getAttribute("adherent");
  if (adherent == null) {
    response.sendRedirect("Login.html");
    return;
  }
%>
<jsp:include page="/WEB-INF/includes/header.jsp">
  <jsp:param name="credentials" value="true"/>
</jsp:include>

<div class="container-fluid m-3">
  <div class="row border my-title">
    <h1>Dossier Adhérent</h1>
  </div>

  <div class="row mt-3">
    <h2>Informations personnelles</h2>
    <p><strong>Nom :</strong> <%= adherent.getNomComplet() %></p>
    <p><strong>Prénom :</strong> <%= adherent.getPrenom() %></p>
    <p><strong>Email :</strong> <%= adherent.getEmail() %></p>
    <p><strong>Numéro adhérent :</strong> <%= adherent.getIdentifiant() %></p>
  </div>

  <div class="row mt-3">
    <h2>Compétitions inscrites</h2>
    <% if (adherent.getInscriptions().isEmpty()) { %>
    <p>Aucune compétition inscrite pour le moment.</p>
    <% } else { %>
    <table class="table">
      <thead>
      <tr>
        <th>Nom</th>
        <th>Date</th>
        <th>Lieu</th>
        <th>Action</th>
      </tr>
      </thead>
      <tbody>
      <% for (Inscription inscription : adherent.getInscriptions()) {
        String codeCompetition = inscription.getCompetition().getCodeCompetition();
      %>
      <tr>
        <td><%= inscription.getCompetition().getNom() %></td>
        <td><%= inscription.getCompetition().getDate() %></td>
        <td><%= inscription.getCompetition().getLieu() %></td>
        <td>
          <form action="desinscrire" method="post">
            <input type="hidden" name="competition" value="<%= codeCompetition %>" />
            <button type="submit" class="btn btn-danger btn-sm">Se désinscrire</button>
          </form>
        </td>
      </tr>
      <% } %>
      </tbody>
    </table>

    <% } %>
  </div>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
