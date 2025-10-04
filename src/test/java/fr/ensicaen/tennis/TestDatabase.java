package fr.ensicaen.tennis;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestDatabase {

	private static EntityManager entityManager;

	@BeforeAll
	public static void before() {
		final String db_unit_name = ApplicationProperties.get("fr/ensicaen/tennis");
		entityManager = Persistence.createEntityManagerFactory(db_unit_name).createEntityManager() ;
		TodoEntity todo = new TodoEntity();
		todo.setDescription("-- todo --");
		entityManager.getTransaction().begin();
		entityManager.persist(todo);
		entityManager.getTransaction().commit();
	}

	@Test
	public void database_engine_ok() {
		assert entityManager != null;
	}

	@Test
	public void insert_todo() {
		TodoEntity todo = new TodoEntity();
		todo.setDescription("This is Todo !!!");
		entityManager.getTransaction().begin();
		entityManager.persist(todo);
		entityManager.getTransaction().commit();
		assert todo.getIdTodo() != 0;
	}

	@Test
	public void get_todo() {
		Query query = entityManager.createQuery("FROM TodoEntity t WHERE t.description LIKE :desc");
		query.setParameter("desc", "%todo%");
		assert !query.getResultList().isEmpty();
	}

	@AfterAll
	public static void after() {
		entityManager.close();
	}
}
