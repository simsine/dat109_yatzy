<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>YATZY > Historikk</title>
		<link rel="stylesheet" href="simple.css">
		<script src="script.js"></script>
	</head>
	
	<body>
		<div class="header">
			<h1>
				<a href="/" class="unstyled-link">YATZY<img src="YatzyLogo2.png"></a>
			</h1>
			<div class="menubox">
				<p>Hei, $fornavn!</p>
				<a href="lobby" class="unstyled-link"><p>Lobby→</p></a>
			</div>
		</div>
		<div class="main">
			<div class="spillhistorikk">
				<h2>Historikk<h2>
			</div>
			<div class="lobbyGames">
				<div class="item1"><a href="/gameID"><button>Spill $X</button></a></div>
				<div class="item2"><p>$25/03/25</p></div>
			</div>
		</div>
	</body>
</html>