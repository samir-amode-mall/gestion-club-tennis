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

@WebServlet("/desinscrire")
public class DesinscriptionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("adherent") == null) {
            response.sendRedirect("Login.html");
            return;
        }

        String param = request.getParameter("competition");
        if (param == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètre 'competition' manquant.");
            return;
        }

        int competitionId;
        try {
            competitionId = Integer.parseInt(param);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de compétition invalide : " + param);
            return;
        }

        Tournoi competition = getCompetitionById(competitionId);
        if (competition == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Compétition non trouvée.");
            return;
        }

        Adherent adherent = (Adherent) session.getAttribute("adherent");
        adherent.retirerCompetition(competition);

        response.sendRedirect("inscription");
    }

    private Tournoi getCompetitionById(int id) {
        return switch (id) {
            case 1 -> new Tournoi(1, "Open Australie", "2025-02-08", "Melbourne");
            case 2 -> new Tournoi(2, "Roland Garros", "2025-05-23", "Paris");
            case 3 -> new Tournoi(3, "Wimbledon", "2025-06-23", "Londres");
            case 4 -> new Tournoi(4, "US Open", "2025-08-30", "New-York");
            default -> null;
        };
    }
}

