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
<%@ include file="../fragments/indexHeader.jsp" %>


<section class="login-page">
    <h2>Reset hasła</h2>
    <div class="stats--item">
        <em>${notMatchPassword}</em>
        <em>${errorToken}</em>
    </div>
            <form:form modelAttribute="user" method='post' action="/reset">
                <form:hidden path="id"/>
                <form:hidden path="username"/>
                <form:hidden path="role"/>
                <form:hidden path="firstName"/>
                <form:hidden path="lastName"/>
                <form:hidden path="accountNonLocked"/>
                <form:hidden path="enabled"/>
                    <input type="text" name="resetToken" value="${resetToken}" hidden>
                <div class="form-group">
                    <form:password path="password" placeholder="Hasło"  />
                    <form:errors path="password"/>
                </div>
                <div class="form-group">
                    <input type="password" name="password2" placeholder="Powtórz hasło" required />
                </div>
                <button class="btn" type="submit">Zatwierdz</button>
                </div>
            </form:form>
</section>

<%@ include file="../fragments/indexFooter.jsp" %>

</body>
</html>
