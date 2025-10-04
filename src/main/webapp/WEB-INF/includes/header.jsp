<%--
  Created by IntelliJ IDEA.
  User: Joan Reynaud
  Date: 26/02/2025
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
	final String path = request.getContextPath();
	boolean isAuthenticated = session.getAttribute("adherent") != null;
	long sessionStartTime = (long) session.getAttribute("sessionStartTime");
	long currentTime = System.currentTimeMillis();
	long timeLeft = (60*5) - ((currentTime - sessionStartTime) / 1000);
%>
<!DOCTYPE html>
<html lang="fr" data-bs-theme="auto">
<head>
	<title>Tennis</title>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="<%=path%>/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<link href="<%=path%>/css/myapp.css" rel="stylesheet" integrity="sha384-Adh6wTShfmVzb1AcFrKZRZdZ9yVkHRNVsV1U4foJS6anepv0eDDjTAioxyVli/GW" crossorigin="use-credentials">
	<script type="module" src="<%=path%>/js/color-modes.js" integrity="sha384-4VBPEsXn8OFy1QjsLh45jTyxGGLeBsKwRpHPloZEV+t6lgoIGs41R+vHsLwHRtxk" crossorigin="use-credentials"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
	<div class="container-fluid">
		<a class="navbar-brand" href="<%=path%>/">
			<img src="<%=path%>/media/6672399.webp" alt="Tennis Ball" width="36" height="36">
			<small class="text-opacity-25">Tennis Club</small>
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a href="<%=path%>/index.jsp" class="nav-link">🏠 Accueil</a>
				</li>
				<li class="nav-item">
					<form action="<%=path%>/AddTodo.jsp" method="post">
						<input type="hidden" name="parameter" value="value" />
						<button type="submit" class="nav-link">Ajout Todo</button>
					</form>
				</li>
				<li class="nav-item">
					<a href="<%=path%>/ListTodo.jsp" class="nav-link">List Todo</a>
				</li>
			</ul>
			<% if (isAuthenticated) { %>
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<span class="nav-link">Temps restant : <span id="countdown"><%= timeLeft %></span> secondes</span>
				</li>
				<li class="nav-item">
					<a href="<%=path%>/logout" class="nav-link">Déconnexion</a>
				</li>
			</ul>
			<% } else { %>
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a href="<%=path%>/Login.html" class="nav-link">Connexion</a>
				</li>
			</ul>
			<% } %>
		</div>
	</div>
</nav>

<script>
	let timeLeft = <%= timeLeft %>;

	function updateCountdown() {
		const countdownElement = document.getElementById("countdown");
		if (countdownElement) {
			countdownElement.textContent = timeLeft;
		}

		if (timeLeft <= 0) {
			window.location.href = "<%=path%>/Login.html";
		} else {
			timeLeft--;
			setTimeout(updateCountdown, 1000);
		}
	}

	updateCountdown();
</script>
</body>
</html>