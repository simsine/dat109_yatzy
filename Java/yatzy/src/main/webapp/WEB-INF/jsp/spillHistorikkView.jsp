<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>YATZY > Historikk</title>
		<link rel="stylesheet" href="/simple.css">
	</head>
	
	<body>
		<div class="header">
			<h1>
				<a href="/" class="unstyled-link">YATZY<img src="/YatzyLogo.png" alt="2 stykk røde terninger"></a>
			</h1>
			
			<div class="menubox">
				<p>Hei, ${spiller.brukernavn}</p>
				
				<a href="/admin" class="unstyled-link"><p>Adminside→</p></a>
							
				<a href=/utlogging><p>Logg ut<p></a>
				
				<a href="/lobby" class="unstyled-link"><p> Lobby → </p></a>
			</div>
		</div>

		<div class="main">
			<div class="spillhistorikk">
				<h2>Historikk for ${brukernavn}</h2>
			</div>

			<div class="historyGames">
					
				<c:if test="${empty tidligereSpill}">
					<p> Ingen tidligere spill</p>
				</c:if>
	
				<c:if test="${not empty tidligereSpill}">
					<c:forEach var="pt" items="${tidligereSpill}">
						
							<div class="item1">
								<a href="/spill/${pt.poengtabellId.spillnr}">
									<button> Spill ${pt.poengtabellId.spillnr}</button>
								</a>
							</div>
		
							<div class="item2">
								<p>${pt.spill.tidopprettet}</p>
							</div>

					</c:forEach>
				</c:if>
				
			</div>
		</div>
		
		<footer> 
			<h2>Credits</h2>
			<p><a href="https://www.flaticon.com/free-icons/dice" title="dice icons">Dice icons created by Candy Design - Flaticon</a><p>
		</footer>
	</body>
</html>