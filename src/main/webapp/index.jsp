<%--
  Created by IntelliJ IDEA.
  User: Joan Reynaud
  Date: 26/02/2025
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.ensicaen.tennis.bean.Adherent" %>
<%
	Adherent adherent = (Adherent) session.getAttribute("adherent");
	boolean isAuthenticated = adherent != null;
%>
<jsp:include page="/WEB-INF/includes/header.jsp">
	<jsp:param name="credentials" value="<%= isAuthenticated %>"/>
</jsp:include>

<div class="container-fluid m-3">
	<div class="row border my-title">
		<h1>Gestion du club de tennis de l'Ensicaen</h1>
	</div>
	<div class="row mt-3">
		<a href="console/">Console H2 database</a>
	</div>

	<% if (isAuthenticated) { %>
	<div class="row mt-3">
		<h2>Bienvenue, <%= adherent.getPrenom() %> !</h2>
		<p>Vous êtes connecté avec l'email : <%= adherent.getEmail() %></p>
		<ul>
			<li><a href="adherent">Consulter mon dossier</a></li>
			<li><a href="inscription">S'inscrire à une compétition</a></li>
		</ul>
	</div>
	<% } else { %>
	<div class="row mt-3">
		<p>Veuillez vous <a href="Login.html">connecter</a> pour accéder aux services.</p>
	</div>
	<% } %>
</div>

<jsp:include page="/WEB-INF/includes/footer.jsp" />
