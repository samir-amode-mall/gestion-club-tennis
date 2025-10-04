package fr.ensicaen.tennis.bean;

import fr.ensicaen.tennis.persistence.Database;
import fr.ensicaen.tennis.persistence.TodoEntity;
import fr.ensicaen.tennis.util.Logger;

import java.util.List;

public class TodoBean {
	private final Database database;
	private final static Logger logger = new Logger(TodoBean.class.getName());
	public TodoBean() {
		database = Database.getInstance();
		logger.debug("Loading bean.");
	}

	public List<TodoEntity> getTodoList() {
		return database.listTodo();
	}
}
