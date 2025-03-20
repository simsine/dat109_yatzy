<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>Yatzy > Spill</title>
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
				<a href="/lobby" class="unstyled-link"><p>Lobby→</p></a>
			</div>
		</div>
		<div class="main">
			<div class="gameView">
				<div class="item1"></div>
				<div class="item2"><h2>Spill $X - $Enere</h2></div>
				<div class="item3">
					<div class="gameTable">
						<p>$GameTable</p>
					</div>
				</div>
				<div class="item4">
					<div class="gameDiceview">
							<h2>Det er $navn sin tur</h2>
							<p><img src="cursor.png" alt="Musepil som klikker" scale="1">Klikk på terningene du vil beholde</p>
						<div>
							<img src="terningEn.png" alt="Terning som viser én">
							<img src="terningTo.png" alt="Terning som viser to">
							<img src="terningTre.png" alt="Terning som viser tre">
						</div>
						<div><hr></div>
						<div>
							<img src="terningFire.png" alt="Terning som viser fire">
							<img src="terningFem.png" alt="Terning som viser fem">
						</div>
						<p>Beholdt</p>
						<p>Du har $X kast igjen</p>
						<button>Trill terninger</button>
					</div>
				</div>	
			</div>
		</div>
	</body>
</html>