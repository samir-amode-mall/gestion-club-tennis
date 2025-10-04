package fr.ensicaen.tennis.servlet;

import fr.ensicaen.tennis.persistence.Database;
import fr.ensicaen.tennis.persistence.TodoEntity;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="Todo", urlPatterns = "/api/todo")
public class TodoServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String description = req.getParameter("description");
		RequestDispatcher requestDispatcher = null;
		if (description == null || description.isEmpty()) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			TodoEntity todo = Database.getInstance().addTodoByDescription(description);
			requestDispatcher = req.getRequestDispatcher("/ListTodo.jsp");
			req.setAttribute("new_todo", todo);
			requestDispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/ListTodo.jsp");
		requestDispatcher.forward(req, resp);
	}
}
