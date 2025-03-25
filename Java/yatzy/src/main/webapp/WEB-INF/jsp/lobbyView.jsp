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
			<h1>
				<a href="/" class="unstyled-link">YATZY</a>
			</h1>
			<div class="menubox">
				<p>Hei, $Navn!</p>
				<a href="/spillhistorikk" class="unstyled-link"><p>Spillhistorikk→</p></a>
			</div>
		</div>
		<div class="main">
			<div class="lobby">
				<h2>Lobby<h2>
			</div>
			<div class="lobbyGames">
				<div class="item1"><a href="/spill"><button>Spill $X</button></a></div>
				<div class="item2"><p>Spillere: $Y/6</p></div>
			</div>
		</div>
	</body>
</html>