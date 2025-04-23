<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>YATZY > Lobby</title>
		<link rel="stylesheet" href="<c:url value='/simple.css'/>">
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
				<a href="<c:url value='/'/>" class="unstyled-link">YATZY<img src="YatzyLogo.png" alt="2 stykk terninger"></a>
			</h1>
			<div class="menubox">
				<p>Hei, ${spiller.brukernavn}</p>
				<c:if test="${isAdmin eq true}">
					<a href="<c:url value='/admin'/>" class="unstyled-link"><p>Adminside→</p></a>
				</c:if>
				<a href="<c:url value='/utlogging'/>"><button>Logg ut</button></a>
				<a href="<c:url value='/spillhistorikk/${spiller.brukernavn}'/>" class="unstyled-link"><p>Mine spill→</p></a>
			</div>
		</div>
		<div class="main">
			<div class="lesserTitle">
				<h2>Lobby</h2>
				<p><c:out value="${feilmelding}" /></p>
			</div>
			<div class="newGame">
				<form method="post" action="<c:url value='/spill/opprett'/>">
					<div class="item1">
						<input type="submit" value="Opprett nytt spill">
					</div>
				</form>
			</div>
			<c:forEach var="spill" items="${spillListe}">
				<div class="lobbyGames">
					<div class="item1">
					<form action="<c:url value='/spill/${spill.spillnr}/blimed'/>" method="post">
						<input type="submit" value="Bli med i spill ${spill.spillnr}">
					</form>
					</div>
					<div class="item2">
						<p class="visSpillere" data-spillere="${spill.spillereBrukernavn}">${spill.antallSpillere}/6 spillere</p>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<footer> 
			<h2>Credits</h2>
			<p><a href="https://www.flaticon.com/free-icons/dice" title="dice icons">Dice icons created by Candy Design - Flaticon</a><p>
		</footer>
	</body>
</html>