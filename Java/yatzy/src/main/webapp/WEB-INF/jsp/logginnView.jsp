<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>Yatzy > Pålogging</title>
		<link rel="stylesheet" href="simple.css">
		<script src="script.js"></script>
	</head>
	
	<body>
		<div class="header">
			<h1>
				<a href="/" class="unstyled-link">YATZY</a>
			</h1>
		</div>
		<div class="main">
			<div class="schema">
				<h2>Logg inn</h2>
				<form method="POST" action="/innlogging">
					<c:if test="${errors.size() > 0}">
						<p style="color:red;">Feil:</p>
						<ul style="color:red;">
							<c:forEach var="e" items="${errors}">
								<li>${e}</li>
							</c:forEach>			
						</ul>			
					</c:if>
					<!-- Må fikses -->
					<fieldset>
						<label for="epost">Epost</label>
						<input
						   type="text" id="epost" name="epost"
						   minlength="2" maxlength="20" pattern="^[A-ZÆØÅ][a-zæøåA-ZÆØÅ \-]{1,19}$" required
						   oninvalid="this.setCustomValidity('Ikke riktig epost eller passord')"
						   oninput="this.setCustomValidity('')" 
						   >
						<label for="passord">Passord</label>
						<input
							type="text" id="passord" name="passord"
							minlength="2" maxlength="20" pattern="^[a-zæøåA-ZÆØÅ \-]{2,20}$" required
							oninvalid="this.setCustomValidity('Ikke riktig epost eller passord')"
							oninput="this.setCustomValidity('')"
							>
						<br>
						<input type="submit" value="Logg inn">
					</fieldset>
				</form>
				<p>Ikke registrert? Registrer deg <a href="/registrering">her</a>.</p>
			</div>
		</div>
	</body>
</html>