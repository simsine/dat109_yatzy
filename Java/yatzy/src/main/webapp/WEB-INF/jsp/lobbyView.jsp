<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>Yatzy > Lobby</title>
		<link rel="stylesheet" href="simple.css">
		<script src="script.js"></script>
	</head>
	
	<body>
		<div class="header">
			<h1>YATZY</h1>
			<div class="logginnbox">
				<p>Du er innlogget som $Navn</p>
				<p>Spillhistorikk→</p>
			</div>
		</div>
		<div class="main">
			<div class="lobby">
				<h2>Lobby<h2>
			</div>
			<div class="lobbyGames">
				<div class="item1"><p>Velg spill</p></div>
				<div class="item2"><button>Spill $X</button></div>
				<div class="item3"><p>Spillere: $Y/6</p></div>
			</div>
		</div>
	</body>
</html>