<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Dashboard - SB Admin</title>
    <link href = "<c:url value = "/resources/css/styles.css"/>" rel="stylesheet" />
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<%@ include file="adminHeader.jsp" %>

<div id="layoutSidenav_content">
    <main>
        <div class="card shadow-lg border-0 rounded-lg mt-5">
            <div class="card-header"><h3 class="text-center font-weight-light my-4">Edycja</h3></div>
            <div class="card-body">
                <form:form method="post" action="/admin/edit" modelAttribute="user">
                    <form:hidden path="id"/>
                    <form:hidden path="username"/>
                    <form:hidden path="password"/>
                    <form:hidden path="role"/>
                    <div class="form-row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="small mb-1" for="inputFirstName">Imie</label>
                                <form:input path="firstName" class="form-control py-4" id="inputFirstName" type="text" placeholder="Imie" />
                                <form:errors path="firstName"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="small mb-1" for="inputLastName">Nazwisko</label>
                                <form:input path="lastName" class="form-control py-4" id="inputLastName" type="text" placeholder="Nazwisko" />
                                <form:errors path="lastName"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group mt-4 mb-0"><button type="submit" class="btn btn-primary btn-block">Zmień</button></div>
                </form:form>
            </div>
            <div class="card-footer text-center">
                <div class="small"><a href="login.html">Have an account? Go to login</a></div>
            </div>
        </div>
    </main>
    <%@ include file="adminFooter.jsp" %>
</div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="assets/demo/chart-area-demo.js"></script>
<script src="assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="assets/demo/datatables-demo.js"></script>
</body>
</html>
