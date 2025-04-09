<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
				<c:if test="${isAdmin eq true}">
					<a href="/admin" class="unstyled-link"><p>Adminside→</p></a>
				</c:if>
				<a href=/utlogging><button>Logg ut</button></a>
				<a href="/lobby" class="unstyled-link"><p> Lobby→</p></a>
			</div>
		</div>

		<div class="main">
			<div class="spillhistorikk">
				<h2>Din spillhistorikk</h2>
				<h3>Aktive spill:</h3>
				<c:if test="${empty aktivespill}">
					<p> Ingen aktive spill</p>
				</c:if>
			</div>
			
			<div class="activeGames">

				<c:if test="${not empty aktivespill}">
					<c:forEach var="pt" items="${aktivespill}">

							<div class="item1">
								<a href="/spill/${pt.spillnr}">
									<button> Spill ${pt.spillnr}</button>
								</a>
							</div>

							<div class="item2">
								<c:set var="timeStart" value="${pt.tidopprettet}"></c:set>
								<c:set var="modifiedT" value="${fn:replace(timeStart, 'T', ' ')}"/>
								<c:set var="modifiedStartTime" value="${fn:replace(modifiedT, '-', '.')}"/>
								<p>Opprettet: ${modifiedStartTime}</p>
							</div>

					</c:forEach>
				</c:if>
			</div>
			
			<div class="spillhistorikk">
				<h3>Avsluttede spill:</h3>
				<c:if test="${empty avsluttedespill}">
					<p>Ingen avsluttede spill</p>
				</c:if>
			</div>
			
			<div class="endedGames">

				<c:if test="${not empty avsluttedespill}">
					<c:forEach var="pt" items="${avsluttedespill}">

							<div class="item1">
								<a href="/spill/${pt.spillnr}">
									<button> Spill ${pt.spillnr}</button>
								</a>
							</div>

							<div class="item2">
								<c:set var="timeStart" value="${pt.tidopprettet}"></c:set>
								<c:set var="modifiedS" value="${fn:replace(timeStart, 'T', ' ')}"/>
								<c:set var="modifiedStartTime" value="${fn:replace(modifiedS, '-', '.')}"/>
								<p>Opprettet: ${modifiedStartTime}</p>
								<c:set var="timeEnd" value="${pt.tidavsluttet}"></c:set>
								<c:set var="modifiedE" value="${fn:replace(timeEnd, 'T', ' ')}"/>
								<c:set var="modifiedEndTime" value="${fn:replace(modifiedE, '-', '.')}"/>
								<p>Avsluttet:  ${modifiedEndTime}</p>
							</div>

					</c:forEach>
				</c:if>
			</div>
			<div class="spillhistorikk">
				<p>*Dato er oppgitt på formatet YYYY.MM.DD</p>
			</div>
		</div>
		<footer> 
			<h2>Credits</h2>
			<p><a href="https://www.flaticon.com/free-icons/dice" title="dice icons">Dice icons created by Candy Design - Flaticon</a><p>
		</footer>
	</body>
</html>