<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>Yatzy > Registrering</title>
		<link rel="stylesheet" href="/simple.css">
	</head>
	
	<body>
		<div class="header">
			<h1>
				<a href="/yatzy-1.0/" class="unstyled-link">YATZY<img src="YatzyLogo.png" alt="2 stykk terninger"></a>
			</h1>
		</div>
		<div class="main">
			<div class="schema">
				<h2>Opprett bruker</h2>
				<form method="POST" action="/yatzy-1.0/registrering">
					<c:if test="${errors.size() > 0}">
						<p style="color:red;">Feil:</p>
						<ul style="color:red;">
							<c:forEach var="e" items="${errors}">
								<li>${e}</li>
							</c:forEach>			
						</ul>			
					</c:if>
					<!-- Regex må fikses -->
					<fieldset>
						<div>
							<label for="epost">Epost</label>
								<input
								   type="email" id="epost" name="email" value="${registreringForm.email}"
								   minlength="2" maxlength="100" pattern="^[\w\-\.]+@([\w-]+\.)+[\w-]{2,}$" required
								   oninvalid="this.setCustomValidity('Epost må være gyldig')"
								   oninput="this.setCustomValidity('')" 
								   >
					   </div>
					   <div>
	   						<label for="brukernavn">Brukernavn</label>
	   							<input
	   							   type="text" id="brukernavn" name="brukernavn" value="${registreringForm.brukernavn}"
	   							   minlength="2" maxlength="20" pattern="^[a-zA-Z0-9_-]{3,20}$" required
	   							   oninvalid="this.setCustomValidity('Brukernavn må være engelske bokstaver, tall og understrek(_), og unikt')"
	   							   oninput="this.setCustomValidity('')" 
	   							   >
	   				   </div>
					   <div>
						   <label for="fornavn">Fornavn</label>
								<input
								   type="text" id="fornavn" name="fornavn" value="${registreringForm.fornavn}"
								   minlength="2" maxlength="30" pattern="^[A-ZÆØÅ][a-zæøåA-ZÆØÅ \-]{1,19}$" required
								   oninvalid="this.setCustomValidity('Fornavn må være 2-20 bokstaver, ha stor forbokstav, og kan inneholde mellomrom og bindestrek')"
								   oninput="this.setCustomValidity('')" 
								   >
					    </div>
						<div>
							<label for="etternavn">Etternavn</label>
								<input
								   type="text" id="etternavn" name="etternavn" value="${registreringForm.etternavn}"
								   minlength="2" maxlength="20" pattern="^[A-ZÆØÅ][a-zæøåA-ZÆØÅ \-]{1,19}$" required
								   oninvalid="this.setCustomValidity('Etternavn må være 2-20 bokstaver, ha stor forbokstav, og kan inneholde mellomrom og bindestrek')"
								   oninput="this.setCustomValidity('')" 
								   >
						</div>
						<div>
							<label for="passord">Passord</label>
								<input
									type="password" id="passord" name="passord"
									minlength="8" maxlength="20" required
									oninvalid="this.setCustomValidity('')"
									oninput="this.setCustomValidity('')"
									>
						</div>
						<div>
							<label for="passord_re">Repeter passord</label>
								<input
								   type="password" id="passord_re" name="passord_re"
								   minlength="8" maxlength="20" required
								   oninvalid="this.setCustomValidity('')"
								   oninput="this.setCustomValidity('')" 
								   >
						</div>
						<br>
						<input type="submit" value="Registrer">
					</fieldset>
				</form>
				<p>Har du allerede bruker? <a href="/yatzy-1.0/innlogging">Logg inn</a></p>
			</div>
		</div>
		
		<footer> 
			<h2>Credits</h2>
			<p><a href="https://www.flaticon.com/free-icons/dice" title="dice icons">Dice icons created by Candy Design - Flaticon</a><p>
		</footer>
	</body>
</html>