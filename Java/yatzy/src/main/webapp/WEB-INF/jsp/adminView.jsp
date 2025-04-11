<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
<head>
<meta charset="UTF-8">
<title>YATZY > Admin</title>
<link rel="stylesheet" href="/simple.css">
		<script>
		    document.addEventListener("DOMContentLoaded", () => {
		        const tooltip = document.createElement("div");
		        tooltip.style.position = "absolute";
		        document.body.appendChild(tooltip);
		
		        document.querySelectorAll(".visSpillere").forEach(button => {
		            const spillere = button.getAttribute("data-spillere");
		
		            
		            button.addEventListener("mouseover", (e) => {
		                tooltip.innerHTML = spillere;
		                tooltip.style.display = "block";
		                
		                // Bruk offsetTop og offsetLeft for å få posisjon i forhold til hele dokumentet
		                const rect = button.getBoundingClientRect();
		                const buttonTop = rect.top + window.pageYOffset;
		                const buttonLeft = rect.left + window.pageXOffset;

		                tooltip.style.top = (buttonTop) + "px"; // 5px under knappen
		                tooltip.style.left = (buttonLeft + 100) + "px"; // Juster til venstre for knappen 
		            });
		
		            button.addEventListener("mouseleave", () => {
		                tooltip.style.display = "none";
		            });
		        });
		    });
		</script>
</head>

<body>
	<div class="header">
		<h1>
			<a href="/yatzy-1.0/" class="unstyled-link">YATZY<img src="YatzyLogo.png"
				alt="2 stykk terninger"></a>
		</h1>
		<div class="menubox">
			<p>Hei, ${spiller.brukernavn}</p>
			<a href=/yatzy-1.0/utlogging><button>Logg ut</button></a> <a href="/yatzy-1.0/lobby"
				class="unstyled-link"><p>Lobby →</p></a> <a
				href="/yatzy-1.0/spillhistorikk/${spiller.brukernavn}" class="unstyled-link"><p>Spillhistorikk→</p></a>
		</div>
	</div>
	<div class="main">
		<div class="adminTitle">
			<h2>Admin - Slett spill</h2>
		</div>
		<c:forEach var="spill" items="${spillListe}">
			<div class="deleteGames">
				<div class="item1">
					<form method="post" action="/yatzy-1.0/admin/slett/${spill.spillnr}">
						<input type="submit" value="Slett spill ${spill.spillnr}">
					</form>
				</div>
				<div class="item2">
					<p class="visSpillere" data-spillere="${spill.spillereBrukernavn}">${spill.antallSpillere}/6 spillere</p>
				</div>
			</div>
		</c:forEach>
		<div class="adminTitle">
			<h2>Admin - Deaktiver spiller</h2>
		</div>
		<c:forEach var="spiller" items="${spillerListe}">
			<div class="deletePlayers">
				<div class="item1">
					<c:choose>
					  <c:when test = "${spiller.aktiv eq false}">
						<form method="post" action="/yatzy-1.0/admin/aktiver/${spiller.brukernavn}">
							<input type="submit" value="Aktiver">
						</form>			           
			         </c:when>
			         <c:otherwise>
						<form method="post" action="/yatzy-1.0/admin/deaktiver/${spiller.brukernavn}">
							<input type="submit" value="Deaktiver">
						</form>
			         </c:otherwise>
			      </c:choose>
				</div>
				<div class="item2">
					<p style="color:${spiller.aktiv eq false ? 'red' : ''}" >${spiller.brukernavn}</p>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>