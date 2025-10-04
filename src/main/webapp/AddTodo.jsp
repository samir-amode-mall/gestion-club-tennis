<%--
  Created by IntelliJ IDEA.
  User: Joan Reynaud
  Date: 26/02/2025
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="WEB-INF/includes/header.jsp">
	<jsp:param name="credentials" value="true"/>
</jsp:include>
<%
	final String _path = request.getContextPath();
%>

<div class="container-fluid m-3">
	<div class="row border my-title">
		<h1>Ajout d'une tâche à la Todolist</h1>
	</div>
	<div class="row mt-3">
		<form action="<%=_path%>/api/todo" method="post">
			<label for="description" class="form-label text-dark">Description : </label>
			<input id="description" name="description" type="text" maxlength="200" class="form-control" placeholder="Saisir ici une description">
			<button type="submit" class="btn btn-primary mt-2">Ajouter cette tâche</button>
		</form>
	</div>
</div>

<jsp:include page="WEB-INF/includes/footer.jsp" />
