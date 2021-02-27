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
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />

</head>

<%@ include file="../fragments/appHeader.jsp" %>

<div class="container bootdey flex-grow-1 container-p-y">

    <div class="media align-items-center py-3 mb-3">
        <div class="media-body ml-4">
            <h2 class="font-weight-bold mb-0">${fullName} <span class="text-muted font-weight-normal"></span></h2>
            <div class="text-muted mb-2">ID: ${user.id}</div>
            <a href="/app/user/edit" class="btn btn-primary btn-sm">Edytuj</a>&nbsp;
            <a href="/app/user/pass" class="btn btn-default btn-sm">Zmien haslo</a>&nbsp;
        </div>
    </div>

    <div class="table-bordered">
        <hr class="border-light m-0 table-bordered">
        <div class="card-body">

            <table class="table user-view-table m-0">
                <tbody>
                <tr>
                    <td>Email:</td>
                    <td>${user.username}</td>
                </tr>
                <tr>
                    <td>Imie:</td>
                    <td>${user.firstName}</td>
                </tr>
                <tr>
                    <td>Nazwisko:</td>
                    <td>${user.lastName}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

</div>
<%@ include file="../fragments/appFooter.jsp" %>


<script src="../js/app.js"/>
</body>
</html>
