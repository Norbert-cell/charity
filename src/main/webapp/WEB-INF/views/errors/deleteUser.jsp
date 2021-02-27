<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Strona główna - SB Admin</title>
    <link href = "<c:url value = "/resources/css/styles.css"/>" rel="stylesheet" />
</head>
<body class="sb-nav-fixed">
<%@ include file="../admin/adminHeader.jsp" %>

<div id="layoutSidenav_content">
    <main>
        <div class="container-fluid">
            <h1 class="mt-4">${error}</h1>
        </div>
    </main>
    <%@ include file="../admin/adminFooter.jsp" %>
</div>
</div>
<script src="js/scripts.js"></script>
</body>
</html>

