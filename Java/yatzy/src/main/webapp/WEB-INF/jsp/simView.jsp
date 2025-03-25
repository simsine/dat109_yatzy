<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>Yatzy > Simulering</title>
		<link rel="stylesheet" href="simple.css">
		<script src="script.js"></script>
	</head>
	
	<body>
		<div class="header">
			<a href="/"><h1 class="unstyled link">YATZY simulering</h1></a>
			<div class="menubox">
				<p>Hei, $fornavn!</p>
				<a href="/lobby" class="unstyled-link"><p>Lobby→</p></a>			
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