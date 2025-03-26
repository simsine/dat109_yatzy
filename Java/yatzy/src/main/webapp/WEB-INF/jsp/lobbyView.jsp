<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>YATZY > Lobby</title>
		<link rel="stylesheet" href="simple.css">
		<script src="script.js"></script>
	</head>
	
	<body>
		<div class="header">
			<h1>
				<a href="/" class="unstyled-link">YATZY<img src="YatzyLogo.png"></a>
			</h1>
			<div class="menubox">
				<p>Hei, $fornavn!</p>
				<a href="/spillhistorikk" class="unstyled-link"><p>Spillhistorikk→</p></a>
			</div>
		</div>
		<div class="main">
			<div class="lobby">
				<h2>Lobby<h2>
			</div>
			<div class="newGame">
				<div class="item1"><a href="/spill"><button>Opprett nytt spill</button></a></div>
			</div>
			<div class="lobbyGames">
				<div class="item1"><a href="/spill/1"><button>Spill $1</button></a></div>
				<div class="item2"><p>Spillere: $Y/6</p></div>
			</div>
			<div class="lobbyGames">
				<div class="item1"><a href="/spill/2"><button>Spill $2</button></a></div>
				<div class="item2"><p>Spillere: $Y/6</p></div>
			</div>
		</div>
	</body>
</html>