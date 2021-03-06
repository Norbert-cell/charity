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

<%@ include file="../fragments/appHeader.jsp" %>

<section class="stats">
    <h1><em class="align-content-center">Twoje dane</em></h1>
    <div class="container container--85">
        <div class="stats--item">

            <em><h3>UDALO CI SIE ZMIENIC HASLO!</h3></em>
            <a href="/app/user/details" class="btn">Wróć</a>
        </div>


    </div>
</section>
<%@ include file="../fragments/appFooter.jsp" %>


<script src="../js/app.js"/>
</body>
</html>
