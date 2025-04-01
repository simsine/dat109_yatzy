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
				<a href="/" class="unstyled-link"> YATZY <img src="YatzyLogo.png" alt="2 stykk terninger"></a>
			</h1>
			
			<div class="menubox">
<<<<<<< Updated upstream
				<p>Hei, ${spiller.fornavn}</p>
				<a href="lobby" class="unstyled-link"><p>Lobby→</p></a>
=======
				<a href="lobby" class="unstyled-link"><p> Lobby → </p></a>
>>>>>>> Stashed changes
			</div>
		</div>

		<div class="main">
			<div class="spillhistorikk">
				<h2>Historikk for ${brukernavn} </h2>
			</div>

			<c:if test="${empty tidligereSpill}">
				<p> Ingen tidligere spill</p>
			</c:if>

			<c:if test="${not empty tidligereSpill}">
				<c:forEach var="pt" items="${tidligereSpill}">
					<div class="lobbyGames">
						<div class="item1">
							<a href="${pageContext.request.contextPath}/spill/${pt.poengtabellId.spillnr}">
								<button> Spill ${pt.poengtabellId.spillnr}</button>
							</a>
						</div>
	
						<div class="item2">
							<p>${pt.spill.tidopprettet}</p>
						</div>
				
					</div>
				</c:forEach>
			</c:if>
		</div>
	</body>
</html>