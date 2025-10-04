package fr.ensicaen.tennis.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "todo", schema = "PUBLIC", catalog = "tennis")
public class TodoEntity {
	private long idTodo;
	private String description;

	@Id
	@Column(name = "id_todo")
	// AUTO si Hibernate génère l'id, IDENTITY si c'est la BDD qui prend en charge la génération
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getIdTodo() {
		return idTodo;
	}
	public void setIdTodo(long id) {
		this.idTodo = id;
	}

	@Basic
	@Column(name = "description", nullable = false, length = 200)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
