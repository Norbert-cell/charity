<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href = "<c:url value = "/resources/css/style.css"/>" />
</head>
<body>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/registry" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Reset hasła</h2>
            <form method='post' action="/reset">
                ${notMatchPassword}
                    ${errorToken}
                    <input type="text" name="token" value="${resetToken}" hidden>
                <div class="form-group">
                    <input type="password" name="password" placeholder="Hasło" min="3" max="24" />
                </div>
                <div class="form-group">
                    <input type="password" name="password2" placeholder="Powtórz hasło" min="3" max="24" />
                </div>
                <button class="btn" type="submit">Zatwierdz</button>
                </div>
            </form>
</section>

<%@ include file="../fragments/indexFooter.jsp" %>

</body>
</html>
