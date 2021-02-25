<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

    <link rel="stylesheet" href = "<c:url value = "/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj ${fullName}
                <ul class="dropdown">
                    <li><a href="/app/user/details">Profil</a></li>
                    <li><a href="/donate/charity">Moje zbiórki</a></li>
                    <li><a href="/logout">Wyloguj</a></li>
                </ul>
            </li>
        </ul>

        <ul>
            <li><a href="/app/" class="btn btn--without-border active">Start</a></li>
            <li><a href="index.html#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="index.html#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="/donate/add-gifts" class="btn btn--without-border">Przekaż dary</a></li>
            <li><a href="index.html#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="help">
    <h2>Przekazane dary</h2>

    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">
        <p>DZIEKUJEMY ZE POMAGASZ RAZEM Z NAMI!</p>

        <ul class="help--slides-items">
            <li>
                <c:forEach items="${institutions}" var="institution" varStatus="status" >
                <c:if test="${status.index % 2 == 0}">
            <li>
                </c:if>
                <div class="col">
                    <div class="title">Nazwa instytucji:${institution.name}</div>
                    <a href="/donate/${institution.id}" class="btn btn--small">Szczegóły</a>
                </div>
                <c:if test="${status.index == 3}">
            </li>
            </c:if>
            </c:forEach>
            </li>
        </ul>
    </div>

</section>
<%@ include file="donationFooter.jsp" %>

<script src="<c:url value="../resources/js/app.js"/>"/>
</body>
</html>
