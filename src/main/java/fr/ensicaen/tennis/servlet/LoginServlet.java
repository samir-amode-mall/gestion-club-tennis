package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.bean.Adherent;
import fr.ensicaen.tennis.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Adherent adherent = getAdherentFromDatabase(email);

        if (adherent != null && PasswordUtil.checkPassword(password, adherent.getMotDePasse())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("adherent", adherent);

            session.setMaxInactiveInterval(5*60);

            session.setAttribute("sessionStartTime", System.currentTimeMillis());

            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("Login.html");
        }
    }

    private Adherent getAdherentFromDatabase(String email) {
        if ("samir@gmail.com".equals(email)) {
            String motDePasse = PasswordUtil.hashPassword("onadorelejava");
            return new Adherent(email,"AMODE MALL","Samir", "987654", motDePasse);
        }
        return null;
    }
}
