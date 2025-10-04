package fr.ensicaen.tennis.persistence;

import fr.ensicaen.tennis.security.XSSRequestWrapper;
import fr.ensicaen.tennis.ApplicationProperties;
import fr.ensicaen.tennis.util.Logger;
import jakarta.persistence.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class Database {
	private static Database instance;
	private EntityManagerFactory emf;
	private EntityManager entityManager;
	private static final Logger logger = new Logger(Database.class.getName());

	private static final String[] DISPLAY_PROPERTIES = {
			"user.dir", "jakarta.persistence.jdbc.url", "hibernate.persistenceUnitName", "hibernate.connection.driver_class", "hibernate.dialect"};

	public static Database getInstance(String realPath) {
		if (instance == null) instance = new Database(realPath);
		return instance;
	}

	public static Database getInstance() {
		return getInstance(null);
	}

	protected Database() {
		createAndOpenDB(null);
	}

	protected Database(String dbURL) {
		createAndOpenDB(dbURL);
	}

	private void createAndOpenDB(String realPath) {
		final String db_unit_name = ApplicationProperties.get("db_unit_name");
		// Override url property
		Properties props = new Properties();
		props.setProperty("jakarta.persistence.jdbc.url", "jdbc:h2:file:" + realPath + "/WEB-INF/db/tennis;LOCK_MODE=0;DB_CLOSE_DELAY=0;");
		// Create persistence unit
		emf = Persistence.createEntityManagerFactory(db_unit_name, props);
		logger.info("Open persistence unit : " + db_unit_name);
		logger.config("------ ENTITY MANAGER PROPERTIES");
		Map<String, Object> properties = emf.getProperties();
		Arrays.stream(DISPLAY_PROPERTIES).forEach(key -> logger.config(key + "=" + properties.get(key)));
		logger.config("----------------------------------");
		// Create entity manager (query database from this)
		entityManager = emf.createEntityManager();
	}

	public void close() {
		logger.info("Close persistence ...");
		if (entityManager.isOpen()) entityManager.close();
		if (emf.isOpen()) emf.close();
	}

	/**
	 * Anti-injection method.
	 */
	public static String XSSReplacer(String s) {
		for (Pattern scriptPattern : XSSRequestWrapper.patterns) {
			s = scriptPattern.matcher(s).replaceAll("");
		}
		return s;
	}

	// ********** TODOLIST **********

	public List<TodoEntity> listTodo() {
		final TypedQuery<TodoEntity> query = entityManager.createQuery("from TodoEntity t", TodoEntity.class);
		return query.getResultList();
	}

	public TodoEntity getTodoById(int id) {
		final Query query = entityManager.createQuery("from TodoEntity t where t.idTodo = :id");
		query.setParameter("id", id);
		return (TodoEntity)query.getSingleResult();
	}

	public TodoEntity addTodoByDescription(String description) {
		if (description == null || description.isEmpty()) return null;
		TodoEntity todo = new TodoEntity();
		todo.setDescription(XSSReplacer(description));
		entityManager.getTransaction().begin();
		entityManager.persist(todo);
		entityManager.getTransaction().commit();
		return todo;
	}
}
