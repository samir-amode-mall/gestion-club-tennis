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
import java.util.Arrays;

@WebServlet("/adherent")
public class AdherentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("adherent") == null) {
            response.sendRedirect("Login.html");
            return;
        }

        Adherent adherent = (Adherent) session.getAttribute("adherent");

        request.setAttribute("adherent", adherent);
        request.getRequestDispatcher("Adherent.jsp").forward(request, response);
    }
}
