<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="no">
    <head>
        <meta charset="UTF-8">
        <title>YATZY > Admin</title>
        <link rel="stylesheet" href="/simple.css">
    </head>
    
    <body>
        <div class="header">
            <h1>
                <a href="/" class="unstyled-link">YATZY<img src="YatzyLogo.png" alt="2 stykk terninger"></a>
            </h1>
            <div class="menubox">
                <p>Hei, ${spiller.brukernavn}</p>
                
                <form method="post" action="/innlogging">
                    <div class="item1">
                        <input type="hidden" value="loggut" name="brukernavn">
                        <input type="submit" value="Logg ut">
                    </div>
                </form>
                
                <a href="/spillhistorikk/${spiller.brukernavn}" class="unstyled-link"><p>Spillhistorikk→</p></a>
            </div>
        </div>
        <div class="main">
            <div class="lobby">
                <h2>Admin - Slett spill<h2>
            </div>
            <c:forEach var="spill" items="${spillListe}">
                <div class="lobbyGames">
                    <div class="item1">
                        <form method="post" action="/admin/slett/${spill.spillnr}">
                            <input type="submit" value="Slett spill ${spill.spillnr}">
                        </form>                            
                    </div>
                    <div class="item2"><p>${spill.antallSpillere}/6 spillere</p></div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>