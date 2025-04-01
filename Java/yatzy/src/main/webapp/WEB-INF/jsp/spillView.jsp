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
			<a href="/" class="unstyled-link">YATZY<img src="/YatzyLogo.png" alt="2 stykk terninger"></a>
		</h1>
		<div class="menubox">
			<p>Hei, ${fornavn}</p>
			<a href="/lobby" class="unstyled-link"><p>Lobby→</p></a>
		</div>
	</div>

	<div class="main">
		<div class="gameView">
			<div class="item1">
				<h2>Spill ${spillnr}</h2>
			</div>
			<div class="item2">
				<h2>Runde: $Enere</h2>
			</div>
			<div class="gameBrettContainer">
				<div class="gameTable">
					<table>
						<thead>
							<tr>
								<th>Kategori</th>
								<c:forEach var="poengtabell" items="${poengtabeller}">
									<th>${poengtabell.poengtabellId.brukernavn}</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<!-- 
                            <c:set var="categories" value="${[
                                'Enere', 'Toere', 'Treere', 'Firere', 'Femmere', 'Seksere', 
                                'Bonus', 'Ett par', 'To par', 'Tre like', 'Fire like', 
                                'Liten straight', 'Stor straight', 'Full house', 
                                'Sjanse', 'Yatzy', 'Total score'
                            ]}" />
							 -->
							<c:forEach var="poengtype" items="${poengtyper}">
								<tr>
									<td>${poengtype}</td>
									<c:forEach var="poengtabell" items="${poengtabeller}">
										<td>${poengtabell.poeng[poengtype] == -1 ? '-' : poengtabell.poeng[poengtype]}</td>
									</c:forEach>
								</tr>
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
						<h3>Det er ${fornavn} sin tur</h3>
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
							<c:if test="${antallkastigjen > 0}">
								<input type="submit" formaction="/spill/${spillnr}/trill"
										value="Trill" class="trillButton" />
							</c:if>
							<c:if test="${antallkastigjen < 1}">
								<input type="submit" formaction="/spill/${spillnr}/registrer" 
										value="Avslutt runde" class="registrerButton" />
							</c:if>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>