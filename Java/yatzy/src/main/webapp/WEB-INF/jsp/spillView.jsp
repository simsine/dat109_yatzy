<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
	<head>
		<meta charset="UTF-8">
		<title>Spill</title>
		<link rel="stylesheet" href="simple.css">
		<script src="script.js"></script>
	</head>
	
	<body>
		<div class="header">
			<div>
				<h1>Yatzy</h1>
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
			<h2>Spill $X - $Enere</h2>
			<div class="mainGame">
				<div class="gameTable">
					<p>Insert Spill table</p>
				</div>
				<div class="gameDiceview">
					<h2>Det er $navn sin tur</h2>
					<p> Klikk på terningene du vil beholde</p>
					<img src="terningEn.png" alt="Terning som viser én">
					<img src="terningTo.png" alt="Terning som viser to">
					<img src="terningTre.png" alt="Terning som viser tre">
					<img src="terningFire.png" alt="Terning som viser fire">
					<img src="terningFem.png" alt="Terning som viser fem">
					<img src="terningSeks.png" alt="Terning som viser seks">
					<button>Trill terning</button>
				</div>
			</div>
		</div>
	</body>
</html>