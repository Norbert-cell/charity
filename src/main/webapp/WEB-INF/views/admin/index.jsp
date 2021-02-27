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
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<%@ include file="adminHeader.jsp" %>

    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid">
                <h1 class="mt-4">Strona głowna</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item active">Dashboard</li>
                </ol>
                <div class="row">
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-primary text-white mb-4">
                            <div class="card-body">Liczba uzytkownikow</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <div>${sumUser}</div>
                                <div class="small text-white"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-warning text-white mb-4">
                            <div class="card-body">Liczba adminów</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <div>${sumAdmin}</div>
                                <div class="small text-white"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-success text-white mb-4">
                            <div class="card-body">Liczba fundacji</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <div>${sumFoundation}</div>
                                <div class="small text-white"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-md-6">
                        <div class="card bg-danger text-white mb-4">
                            <div class="card-body">Przekazane dary</div>
                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <div>${sumAllGifts}</div>
                                <div class="small text-white"></div>
                            </div>
                        </div>
                    </div>
                </div>
<%--                <div class="row">--%>
<%--                    <div class="col-xl-6">--%>
<%--                        <div class="card mb-4">--%>
<%--                            <div class="card-header">--%>
<%--                                <i class="fas fa-chart-area mr-1"></i>--%>
<%--                                Area Chart Example--%>
<%--                            </div>--%>
<%--                            <div class="card-body"><canvas id="myAreaChart" width="100%" height="40"></canvas></div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="col-xl-6">--%>
<%--                        <div class="card mb-4">--%>
<%--                            <div class="card-header">--%>
<%--                                <i class="fas fa-chart-bar mr-1"></i>--%>
<%--                                Bar Chart Example--%>
<%--                            </div>--%>
<%--                            <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="card mb-4">--%>
<%--                    <div class="card-header">--%>
<%--                        <i class="fas fa-table mr-1"></i>--%>
<%--                        DataTable Example--%>
<%--                    </div>--%>
<%--                    <div class="card-body">--%>
<%--                        <div class="table-responsive">--%>
<%--                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">--%>
<%--                                <thead>--%>
<%--                                <tr>--%>
<%--                                    <th>Name</th>--%>
<%--                                    <th>Position</th>--%>
<%--                                    <th>Office</th>--%>
<%--                                    <th>Age</th>--%>
<%--                                    <th>Start date</th>--%>
<%--                                    <th>Salary</th>--%>
<%--                                </tr>--%>
<%--                                </thead>--%>
<%--                                <tfoot>--%>
<%--                                <tr>--%>
<%--                                    <th>Name</th>--%>
<%--                                    <th>Position</th>--%>
<%--                                    <th>Office</th>--%>
<%--                                    <th>Age</th>--%>
<%--                                    <th>Start date</th>--%>
<%--                                    <th>Salary</th>--%>
<%--                                </tr>--%>
<%--                                </tfoot>--%>
<%--                                <tbody>--%>
<%--                                <tr>--%>
<%--                                    <td>Tiger Nixon</td>--%>
<%--                                    <td>System Architect</td>--%>
<%--                                    <td>Edinburgh</td>--%>
<%--                                    <td>61</td>--%>
<%--                                    <td>2011/04/25</td>--%>
<%--                                    <td>$320,800</td>--%>
<%--                                </tr>--%>

<%--                                </tbody>--%>
<%--                            </table>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
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
