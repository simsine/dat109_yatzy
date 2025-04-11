<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>YATZY > Simulering</title>
		<link rel="stylesheet" href="<c:url value='/simple.css'/>">
		<title>Yatzy > Simulering</title>
	</head>
	
	<body>
		<div class="header">
			<a href="<c:url value='/'/>" class="unstyled-link">YATZY sim<img src="<c:url value='/YatzyLogo.png'/>" alt="2 stykk terninger"></a>
			<div class="menubox">
				<p>Hei, ${fornavn}</p>
				<c:if test="${isAdmin eq true}">
					<a href="<c:url value='/admin'/>" class="unstyled-link"><p>Adminside→</p></a>
				</c:if>
				<a href="<c:url value='/utlogging'/>"><button>Logg ut</button></a>
				<a href="<c:url value='/lobby'/>" class="unstyled-link"><p>Lobby→</p></a>			
			</div>
		</div>
		<div class="main">
			<p><c:out value="${fikkYatzy}"/></p>
			<table>
				<tr>
					<th>Type</th>
					<th>Poeng</th>
				</tr>
				<c:forEach var="entry" items="${poengMap}">
					<tr>
						<td><c:out value="${entry.key}"/></td>
	  					<td><c:out value="${entry.value}"/></td>
					</tr>
				</c:forEach>
				<tr>
					<td>Sum</td>
					<td><c:out value="${poengSum}"></c:out></td>
				</tr>
			</table>
		</div>
	</body>
</html>