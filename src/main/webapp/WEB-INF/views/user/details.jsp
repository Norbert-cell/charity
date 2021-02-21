<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href = "<c:url value = "/resources/css/style.css"/>"/>
</head>

<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj ${fullName}
                <ul class="dropdown">
                    <li><a href="/app/user/details">Profil</a></li>
                    <li><a href="#">Moje zbiórki</a></li>
                    <li><a href="/logout">Wyloguj</a></li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="/app" class="btn btn--without-border active">Start</a></li>
            <li><a href="index.html#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="index.html#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="index.html#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="index.html#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>
<section class="stats">
    <h1><em class="align-content-center">Twoje dane</em></h1>
    <div class="container container--85">
        <div class="stats--item">
            <em>Email</em>

            <h3>Imie i nazwisko</h3>
            <a href="/app/user/edit" class="btn">Edytuj</a>
            <a href="/app/user/email" class="btn">Zmien email</a>
            <a href="/app/user/pass" class="btn">Zmien hasło</a>
        </div>

        <div class="stats--item">
            <em>${user.username}</em>
            <h3>${user.fullName}</h3>
        </div>

    </div>
</section>
<%@ include file="../donation/donationFooter.jsp" %>


<script src="../js/app.js"/>
</body>
</html>
