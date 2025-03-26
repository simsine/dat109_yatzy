<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
<head>
    <meta charset="UTF-8">
    <title>YATZY > Spill</title>
    <link rel="stylesheet" href="simple.css">
    <script src="script.js"></script>
</head>

<body>
    <div class="header">
        <h3 class="white-text">
            <a href="/" class="unstyled-link">YATZY<img src="YatzyLogo2.png"></a>
        </h3>
        <div class="menubox">
            <p class="white-text">Hei, ${fornavn}!</p>
            <a href="/lobby" class="unstyled-link"><p>Lobby→</p></a>
        </div>
    </div>

    <div class="main">
        <div class="gameView">
            <div class="item1">
                <h6 class="white-text">Spill $Nummer - $Enere</h6>
            </div>
            <div class="item2"></div>

            <div class="gameBrettConatiner">
                <div class="gameTable">
                    <table>
                        <thead>
                            <tr>
                                <th>Kategori</th>
                                <c:forEach var="player" items="${poengTabell}">
                                    <th>${player.brukernavn}</th>
                                </c:forEach>
                                <th>test 1</th>
                                <th>test 2</th>
                                <th>test 3</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="categories" value="${[
                                'Enere', 'Toere', 'Treere', 'Firere', 'Femmere', 'Seksere', 
                                'Bonus', 'Ett par', 'To par', 'Tre like', 'Fire like', 
                                'Liten straight', 'Stor straight', 'Full house', 
                                'Sjanse', 'Yatzy', 'Total score'
                            ]}" />

                            <c:forEach var="kategori" items="${categories}">
                                <tr>
                                    <td>${kategori}</td>
                                    <td>-</td>
                                    <td>-</td>
                                    <td>-</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <p>${GameTable}</p>
                </div>
            </div>

            <div class="gameDiceContainer">
                <div class="gameDiceview">
                    <div class="gameDiceHeader">
                        <h4>Det er $navn sin tur</h4>
                        <p><img src="click.png" alt="Musepil som klikker" width="14px" color="#f1bb14">  Klikk på terningene du vil beholde</p>
                    </div>

                    <div class="terningContainer">
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
                    </div>

	                <div class="trillButtonContainer">
	                    <p>Du har ${X} kast igjen</p>
	                    <button class="trillButton">Trill</button>
	                </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
