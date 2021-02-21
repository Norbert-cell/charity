<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href = "<c:url value = "/resources/css/style.css"/>"/>
</head>
<body>
<%@ include file="../fragments/indexHeader.jsp" %>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user" action="/registry" onsubmit="checkPassword()">
        <div class="form-group">
            <form:input path="firstName" placeholder="Imie" />
            <form:errors path="firstName"/>
        </div>
        <div class="form-group">
            <form:input path="lastName" placeholder="Nazwisko" />
            <form:errors path="lastName"/>
        </div>
        <div class="form-group">
            <form:input path="username" placeholder="Email" />
            <form:errors path="username" cssStyle="color: red"/>
        </div>
        <div class="form-group">
            <form:password path="password" id="pass" placeholder="Hasło" />
            <h3><form:errors path="password"/></h3>
        </div>
        <div class="form-group">
            <input id="pass2" type="password" name="password2" required placeholder="Powtórz hasło" />
            <div style="color: red"><h3>${invalidPassword}</h3></div>
        </div>

        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>

<%@ include file="../fragments/indexFooter.jsp" %>
<script>function checkPassword() {
    const pass1 = document.getElementById("#pass").value;
    const pass2 = document.getElementById("#pass2").value;
    if (pass1 !== pass2) {
        alert("Passwords Do not match");
    }
}</script>
</body>
</html>
