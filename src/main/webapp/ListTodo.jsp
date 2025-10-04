<%--
  Created by IntelliJ IDEA.
  User: Joan Reynaud
  Date: 26/02/2025
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.ensicaen.tennis.persistence.TodoEntity" %>
<%@ page import="java.util.List" %>
<jsp:include page="WEB-INF/includes/header.jsp">
	<jsp:param name="credentials" value="false"/>
</jsp:include>

<jsp:useBean id="todoBean" class="fr.ensicaen.tennis.bean.TodoBean" scope="request">
	<%
		final List<TodoEntity> todos = todoBean.getTodoList();
		final TodoEntity new_todo = (TodoEntity)request.getAttribute("new_todo");
	%>

	<div class="container-fluid m-3">
		<div class="row mt-3 border my-title">
			<h1>Liste des t&acirc;ches</h1>
		</div>
		<div class="row mt-3">
			<table class="table">
				<thead><tr>
					<th scope="col">id</th>
					<th scope="col">Description</th>
					<th></th>
				</tr></thead>
<% for(TodoEntity todo : todos) { %>
				<tr>
					<td><%=todo.getIdTodo()%></td>
					<td><%=todo.getDescription()%></td>
					<td><%= new_todo != null && todo.getIdTodo() == new_todo.getIdTodo() ? "c'est la nouvelle t&acirc;che !" : ""%></td>
				</tr>
<% } %>
				<caption>Il y a <%=todos.size()%> t&acirc;ches</caption>
			</table>
		</div>
	</div>
</jsp:useBean>

<jsp:include page="WEB-INF/includes/footer.jsp" />
