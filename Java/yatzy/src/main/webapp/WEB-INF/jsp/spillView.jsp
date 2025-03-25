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
			<h6>
				<a href="/" class="unstyled-link">YATZY</a>
			</h6>
			<div class="menubox">
				<p>Hei, $fornavn!</p>
				<a href="/lobby" class="unstyled-link"><p>Lobby→</p></a>
			</div>
		</div>
		<div class="main">
			<div class="gameView">
				<div class="item1"></div>
				<div class="item2"><h6>Spill $X - $Enere</h6></div>
				<div class="item3">
					<div class="gameTable">
						<table>
						    <thead>
						        <tr>
						            <th>Kategori</th>
						            <c:forEach var="player" items="${players}">
						                <th>${player.playerName}</th>
						            </c:forEach>
						            <th>test 1</th>
						            <th>test 2</th>
						            <th>test 3</th>
						        </tr>
						    </thead>
						    <tbody>
						        <!-- Øvre seksjon -->
						        <tr>
						            <td>Enere</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.ones}">
						                            ${player.ones}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Toere</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.toere}">
						                            ${player.toere}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Treere</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.treere}">
						                            ${player.treere}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Firere</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.firere}">
						                            ${player.firere}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Femmere</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.femere}">
						                            ${player.femere}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Seksere</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.seksere}">
						                            ${player.seksere}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Bonus</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.bonus}">
						                            ${player.bonus}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>

						        <!-- Nedre seksjon -->
						        <tr>
						            <td>Ett par</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.etPar}">
						                            ${player.etPar}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>To par</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.twoPar}">
						                            ${player.twoPar}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Tre like</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.treLike}">
						                            ${player.treLike}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Fire like</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.fireLike}">
						                            ${player.fireLike}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Liten straight</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.litenStraight}">
						                            ${player.litenStraight}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Stor straight</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.storStraight}">
						                            ${player.storStraight}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Full house</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.hus}">
						                            ${player.hus}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Sjanse</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.sjansje}">
						                            ${player.sjansje}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Yatzy</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.yatzy}">
						                            ${player.yatzy}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						        </tr>
						        <tr>
						            <td>Total score</td>
						            <c:forEach var="player" items="${players}">
						                <td>
						                    <c:choose>
						                        <c:when test="${not empty player.totalScore}">
						                            ${player.totalScore}
						                        </c:when>
						                        <c:otherwise>-</c:otherwise>
						                    </c:choose>
						                </td>
						            </c:forEach>
						            <td>1</td>
						            <td>1</td>
						        </tr>
						    </tbody>
						</table>
						
						
						
						<p>$GameTable</p>
					</div>
				</div>
				<div class="item4">
					<div class="gameDiceview">

							<h3>Det er $navn sin tur</h3>
							<p><img src="click.png" alt="Musepil som klikker" scale="1">Klikk på terningene du vil beholde</p>
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
						<p><b>Beholdt</b></p>
						<p>Du har $X kast igjen</p>
						<button>Trill</button>
					</div>
				</div>	
			</div>
		</div>
	</body>
</html>