package fr.ensicaen.tennis.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="action", urlPatterns = "/api/action")
public class ActionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		PrintWriter w = resp.getWriter();
		w.println("<HTML><BODY>");
		w.println("<br/>Le code est : "+code);
		w.println("</BODY></HTML>");
	}
}
