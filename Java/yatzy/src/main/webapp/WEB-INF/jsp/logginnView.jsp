<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>Yatzy > Pålogging</title>
		<link rel="stylesheet" href="/simple.css">
	</head>
	
	<body>
		<div class="header">
			<h1>
				<a href="/" class="unstyled-link">YATZY<img src="YatzyLogo.png" alt="2 stykk terninger"></a>
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
					<fieldset>
						<div>
						<label for="brukernavn">Brukernavn</label>
							<input
							   type="text" id="brukernavn" name="brukernavn"
							   minlength="3" maxlength="20" pattern="^[a-zA-Z0-9_-]{3,20}$" required
							   oninvalid="this.setCustomValidity('Ikke riktig epost eller passord')"
							   oninput="this.setCustomValidity('')" 
							   >
							</div>
						<div>
							<label for="passord">Passord</label>
							<input
								type="password" id="passord" name="passord"
								minlength="8" maxlength="255" required
								oninvalid="this.setCustomValidity('Ikke riktig epost eller passord')"
								oninput="this.setCustomValidity('')"
								>
						</div>
						<br>
						<input type="submit" value="Logg inn">
					</fieldset>
				</form>
				<p>Ny spiller? <a href="/registrering">Opprett bruker</a></p>
			</div>
		</div>
		
		<footer> 
			<h2>Credits</h2>
			<p><a href="https://www.flaticon.com/free-icons/dice" title="dice icons">Dice icons created by Candy Design - Flaticon</a><p>
		</footer>
	</body>
</html>