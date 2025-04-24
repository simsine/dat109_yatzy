<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>YATZY > Leaderboard</title>
		<link rel="stylesheet" href="<c:url value='/simple.css'/>">
		<title>Yatzy > Leaderboard</title>
	</head>
	
	<body>
		<div class="header">
			<h1>
				<a href="<c:url value='/'/>" class="unstyled-link">YATZY<img src="<c:url value='/YatzyLogo.png'/>" alt="2 stykk røde terninger"></a>
			</h1>
			
			<div class="menubox">
				<p>Hei, ${spiller.brukernavn}</p>
				<c:if test="${isAdmin eq true}">
					<a href="<c:url value='/admin'/>" class="unstyled-link"><p>Adminside→</p></a>
				</c:if>
				<a href="<c:url value='/utlogging'/>"><button>Logg ut</button></a>
				<a href="<c:url value='/spillhistorikk/${spiller.brukernavn}'/>" class="unstyled-link"><p>Mine spill→</p></a>
				<a href="<c:url value='/lobby'/>" class="unstyled-link"><p>Lobby→</p></a>
			</div>
		</div>
		
		<div class="main">
			<h2>Høyeste poengsum</h2>
			<div class="gameTable">
				<table>
					<tr>
						<th>Brukernavn</th>
						<th>Poeng</th>
					</tr>
					<c:forEach var="entry" items="${leaderboard}">
					    <tr>
					        <td style="${spiller.brukernavn eq entry.key ? 'background-color:green' : ''}"><c:out value="${entry.key}"/></td>
					        <td><c:out value="${entry.value}"/></td>
					    </tr>
					</c:forEach>

				</table>
			</div>
		</div>
	</body>
</html>