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
            <div class="card-header"><h3 class="text-center font-weight-light my-4">Edycja kategorii</h3></div>
            <div class="card-body">
                <form:form method="post" action="/admin/category/edit" modelAttribute="category">
                    <form:hidden path="id"/>
                    <div class="form-row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="small mb-1" for="inputName">Nazwa kategorii</label>
                                <form:input path="name" class="form-control py-4" id="inputName" placeholder="Wpisz nazwe kategorii" />
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="small mb-1" for="visible">Zmień widoczność</label>
                                <form:checkbox path="visible" class="form-control py-4" id="visible" />
                            </div>
                        </div>
                    </div>
                    <div class="form-group mt-4 mb-0"><button type="submit" class="btn btn-primary btn-block">Edytuj</button></div>
                </form:form>
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
