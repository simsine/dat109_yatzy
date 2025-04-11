<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
<head>
<meta charset="UTF-8">
<title>YATZY > Spill</title>
<link rel="stylesheet" href="/simple.css">
</head>

	<body>
		<div class="header">
			<h1>
				<a href="/yatzy-1.0/" class="unstyled-link">YATZY<img src="/YatzyLogo.png" alt="2 stykk terninger"></a>
			</h1>
			<div class="menubox">
				<p>Hei, ${spiller.brukernavn}</p>
				<c:if test="${isAdmin eq true}">
					<a href="/yatzy-1.0/admin" class="unstyled-link"><p>Adminside→</p></a>
				</c:if>
				<a href=/yatzy-1.0/utlogging><button>Logg ut</button></a>		
				<a href="/yatzy-1.0/lobby" class="unstyled-link"><p>Lobby→</p></a>
			</div>
		</div>
	
		<div class="main">
			<div class="gameView">
				<div class="item1">
					<h2>Spill ${spillnr}</h2>
					<p><c:out value="${feilmelding}" /></p>
				</div>
				<div class="item2">
					<h2>Runde: ${typenaa}</h2>
				</div>
				<div class="gameBrettContainer">
					<div class="gameTable">
						<table>
							<thead>
								<tr>
									<th>Kategori</th>
									<c:forEach var="poengtabell" items="${poengtabeller}">
										<th style="background-color:${poengtabell.poengtabellId.brukernavn eq aktivspiller ? 'green' : 'grey'}">${poengtabell.poengtabellId.brukernavn}</th>
									</c:forEach>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="poengtype" items="${poengtyper}">
									<tr>
										<td style="background-color:${typenaa eq poengtype ? 'grey' : 'white'}">${poengtype}</td>
										<c:forEach var="poengtabell" items="${poengtabeller}">
											<td>${poengtabell.poeng[poengtype] == -1 ? '-' : poengtabell.poeng[poengtype]}</td>
										</c:forEach>
									</tr>
									
									<c:if test="${poengtype.toString() eq 'SEKSERE'}">
										<tr>
										
											<td><b>Sum</b></td>
											<c:forEach var="poengtabell" items="${poengtabeller}">
												<td>${poengtabell.sumOppTilOgMedSeksere}</td>
											</c:forEach>

										</tr>
										<tr>
											<td><b>Bonus</b></td>
											<c:forEach var="poengtabell" items="${poengtabeller}">
												<td>${poengtabell.bonus}</td>
											</c:forEach>
										</tr>
									</c:if>
								</c:forEach>
								<tr>
									<td>Sum</td>
									<c:forEach var="poengtabell" items="${poengtabeller}">
										<td>${poengtabell.sum}</td>
									</c:forEach>
								</tr>
							</tbody>
						</table>
						<p>${GameTable}</p>
					</div>
				</div>
	
				<div class="gameDiceContainer">
					<div class="gameDiceview">
						<div class="gameDiceHeader">
							<h3>Det er ${aktivspiller} sin tur</h3>
							<p>
								<img src="/click.png" alt="Musepil som klikker" width="20px"> Klikk på terningene du vil beholde
							</p>
						</div>
						
						<form method="post">
							<div class="terningContainer">
								<c:forEach items="${terninger}" var="terning">
									<label class="image-checkbox"> 
										<input type="checkbox" name="valgteterninger" value="${terning}"> 
										<img src="/terning${terning}.png">
									</label>
								</c:forEach>
								<input type="hidden" name="alleterninger" value="${terninger}" />
							</div>
							
							<div class="trillButtonContainer">
								<p>Du har <c:out value="${antallkastigjen}" /> kast igjen</p>
								<c:if test="${typenaa != null}">
									<c:if test="${antallkastigjen > 0}">
										<input type="submit" formaction="/yatzy-1.0/spill/${spillnr}/trill"
											   value="Trill" class="trillButton" />
									</c:if>
									<c:if test="${(antallkastigjen < 1) or (alleterninger[0] == alleterninger[1] 
								        and alleterninger[1] == alleterninger[2] 
								        and alleterninger[2] == alleterninger[3] 
								        and alleterninger[3] == alleterninger[4])}">
								    	<input type="submit" formaction="/yatzy-1.0/spill/${spillnr}/registrer" value="Avslutt runde" class="registrerButton" />
									</c:if>
								</c:if>
							</div>	
						</form>
					</div>
				</div>
					<p>Lurer du på reglene? Besøk Wikipedias Yatzy-side <a href="https://no.wikipedia.org/wiki/Yatzy" target="_blank">her</a>.<p>
			</div>
		</div>
		<footer> 
			<h2>Credits</h2>
			<p><a href="https://www.flaticon.com/free-icons/dice" title="dice icons">Dice icons created by Candy Design - Flaticon</a><p>
		</footer>
		
	</body>
</html>
