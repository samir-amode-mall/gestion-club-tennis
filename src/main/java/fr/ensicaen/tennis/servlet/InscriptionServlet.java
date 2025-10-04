package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.bean.Adherent;
import fr.ensicaen.tennis.bean.Tournoi;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("adherent") == null) {
            response.sendRedirect("Login.html");
            return;
        }

        List<Tournoi> competitionsDisponibles = new ArrayList<>();
        competitionsDisponibles.add(new Tournoi(1, "Open Australie", "2025-02-08", "Melbourne"));
        competitionsDisponibles.add(new Tournoi(2, "Roland Garros", "2025-05-23", "Paris"));
        competitionsDisponibles.add(new Tournoi(3, "Wimbledon", "2025-06-23", "Londres"));
        competitionsDisponibles.add(new Tournoi(4, "US Open", "2025-08-30", "New-York"));

        request.setAttribute("competitionsDisponibles", competitionsDisponibles);
        request.getRequestDispatcher("InscriptionTournois.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("adherent") == null) {
            response.sendRedirect("Login.html");
            return;
        }

        int competitionId = Integer.parseInt(request.getParameter("competition"));

        Adherent adherent = (Adherent) session.getAttribute("adherent");

        Tournoi competitionSelectionnee = getCompetitionById(competitionId);

        if (competitionSelectionnee != null) {
            adherent.ajouterCompetition(competitionSelectionnee);
        }

        session.setAttribute("adherent", adherent);
        response.sendRedirect("inscription");
    }

    private Tournoi getCompetitionById(int id) {
        if (id == 1) {
            return new Tournoi(1, "Open Australie", "2025-02-08", "Melbourne");
        } else if (id == 2) {
            return new Tournoi(2, "Roland Garros", "2025-05-23", "Paris");
        } else if (id == 3) {
            return new Tournoi(3, "Wimbledon", "2025-06-23", "Londres");
        } else if (id == 4) {
            return new Tournoi(4, "US Open", "2025-08-30", "New-York");
        }
        return null;
    }
}
