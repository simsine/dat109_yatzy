<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>Lobby</title>
		<link rel="stylesheet" href="simple.css">
		<script src="script.js"></script>
	</head>
	
	<body>
		<div class="header">
			<div>
				<h1>YATZY simulasjon</h1>
			</div>
			<div class="logginnbox">
				<div>
					<p>Du er innlogget som $Navn</p>
				</div>
				<div>
					<p>Mine spill →</p>
				</div>
			</div>
		</div>
		<div class="main">
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