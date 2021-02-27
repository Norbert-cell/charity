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
    <h2>Podaj meila</h2>
    <form method='post' action="/forgot">
        ${errorMessage}
        ${successMessage}
        <div class="form-group">
            <input type="text" name="username" placeholder="Email" />
        </div>
            <button class="btn" type="submit">Wyslij na meila</button>
        </div>
    </form>
</section>

<%@ include file="../fragments/indexFooter.jsp" %>

</body>
</html>
