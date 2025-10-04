<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.ensicaen.tennis.bean.Tournoi" %>
<%@ page import="fr.ensicaen.tennis.bean.Inscription" %>
<%@ page import="fr.ensicaen.tennis.bean.Adherent" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    List<Tournoi> competitionsDisponibles = (List<Tournoi>) request.getAttribute("competitionsDisponibles");
    Adherent adherent = (Adherent) session.getAttribute("adherent");

    List<Inscription> inscriptions = adherent != null ? adherent.getInscriptions() : null;

    List<Tournoi> competionsInscrites = new ArrayList<>();
    if (inscriptions != null) {
        for (Inscription inscription : inscriptions) {
            competionsInscrites.add(inscription.getCompetition());
        }
    }
%>

<jsp:include page="/WEB-INF/includes/header.jsp">
    <jsp:param name="credentials" value="true"/>
</jsp:include>

<div class="container-fluid m-3">
    <div class="row border my-title">
        <h1>Inscription aux compétitions</h1>
    </div>

    <div class="row mt-3">
        <h2>Compétitions disponibles</h2>
        <% if (competitionsDisponibles.isEmpty()) { %>
        <p>Aucune compétition disponible pour le moment.</p>
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
            <% for (Tournoi competition : competitionsDisponibles) { %>
            <tr>
                <td><%= competition.getNom() %></td>
                <td><%= competition.getDate() %></td>
                <td><%= competition.getLieu() %></td>
                <td>
                    <form action="inscription" method="post">
                        <input type="hidden" name="competition" value="<%= competition.getCodeCompetition() %>">
                        <button type="submit" class="btn btn-primary">S'inscrire</button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } %>

        <h2>Compétitions auxquelles vous êtes inscrit</h2>
        <% if (competionsInscrites.isEmpty()) { %>
        <p>Vous n'êtes inscrit à aucune compétition.</p>
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
            <% for (Tournoi competition : competionsInscrites) { %>
            <tr>
                <td><%= competition.getNom() %></td>
                <td><%= competition.getDate() %></td>
                <td><%= competition.getLieu() %></td>
                <td>
                    <form action="desinscrire" method="post">
                        <input type="hidden" name="competition" value="<%= competition.getCodeCompetition() %>">
                        <button type="submit" class="btn btn-danger">Se désinscrire</button>
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
