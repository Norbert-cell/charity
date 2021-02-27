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
    <link rel="stylesheet" type="text/css" href = "https://cdn.datatables.net/1.10.23/css/dataTables.bootstrap4.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/dt-1.10.23/r-2.2.7/datatables.min.css"/>

    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.16/css/dataTables.bootstrap.min.css">

    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function() {
            $('#example').DataTable();
        } );

    </script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../fragments/appHeader.jsp" %>

<div class="help">
    <div class="row card-body table-responsive">
        <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>Data utworzenia</th>
                <th>Instytucja</th>
                <th>Odebrano</th>
                <th>Dostarczono</th>
                <th>Przedmioty</th>
                <th>Worki</th>
                <th>Zmiana statusu</th>
            </tr>
            </thead>
            <tfoot>
            <tr>
                <th>Data utworzenia</th>
                <th>Instytucja</th>
                <th>Odebrano</th>
                <th>Dostarczono</th>
                <th>Przedmioty</th>
                <th>Worki</th>
                <th>Zmiana statusu</th>
            </tr>
            </tfoot>
            <tbody>
            <c:forEach items="${donations}" var="donation" >
                <tr>
                    <td>${donation.createdDateTime}</td>
                    <td>${donation.institution.name}</td>
                    <td><c:forEach items="${donation.archives}" var="archive">
                        <c:if test="${archive.status == 'RECEIVED'}">
                            ${archive.localDate} o godzinie ${archive.localTime}
                        </c:if>
                    </c:forEach></td>
                    <td>
                        <c:forEach items="${donation.archives}" var="archive">
                            <c:if test="${archive.status == 'DELIVERED'}">
                                ${archive.localDate} o godzinie ${archive.localTime}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td><c:forEach items="${donation.categories}"
                                   var="category">${category.name},</c:forEach>
                    </td>
                    <td>
                            ${donation.quantity}
                    </td>
                    <c:choose>
                        <c:when test="${donation.archives.size()>1}">
                            <td></td>
                        </c:when>
                        <c:otherwise>
                            <td>  <a href="/donate/set-status/${donation.id}" class="btn btn--small">Zmień na odebrane</a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <a id="back-to-top" href="#" class="btn btn-primary btn-lg back-to-top" role="button" title="Click to return on the top page" data-toggle="tooltip" data-placement="left"><span class="glyphicon glyphicon-chevron-up"></span></a>

    </div>
</div>

<%--<section class="help">--%>
<%--    <h2>Przekazane dary</h2>--%>
<%--    <div class="card-body">--%>
<%--        <div class="table-responsives">--%>
<%--            <table data-order='[[ 1, "asc" ]]' data-page-length='2' class="table table-striped table-bordered" id="example" style="width: 100%" cellspacing="0">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>Data utworzenia</th>--%>
<%--                    <th>Instytucja</th>--%>
<%--                    <th>Odebrano</th>--%>
<%--                    <th>Dostarczono</th>--%>
<%--                    <th>Zarchiwizowano</th>--%>
<%--                    <th>Przedmioty</th>--%>
<%--                    <th>Worki</th>--%>
<%--                    <th>Zmiana statusu</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tfoot>--%>
<%--                <tr>--%>
<%--                    <th>Data utworzenia</th>--%>
<%--                    <th>Instytucja</th>--%>
<%--                    <th>Odebrano</th>--%>
<%--                    <th>Dostarczono</th>--%>
<%--                    <th>Zarchiwizowano</th>--%>
<%--                    <th>Przedmioty</th>--%>
<%--                    <th>Worki</th>--%>
<%--                    <th>Zmiana statusu</th>--%>
<%--                </tr>--%>
<%--                </tfoot>--%>
<%--                <tbody>--%>
<%--                <c:forEach items="${donations}" var="donation" >--%>
<%--                    <tr>--%>
<%--                        <td>--%>
<%--                            ${donation.createdDateTime}--%>
<%--                        </td>--%>
<%--                        <td>${donation.institution.name}</td>--%>
<%--                        <td><c:forEach items="${donation.archives}" var="archive">--%>
<%--                            <c:if test="${archive.status == 'RECEIVED'}">--%>
<%--                                ${archive.localDate} o godzinie ${archive.localTime}--%>
<%--                            </c:if>--%>
<%--                        </c:forEach></td>--%>
<%--                        <td>--%>
<%--                            <c:forEach items="${donation.archives}" var="archive">--%>
<%--                                <c:if test="${archive.status == 'DELIVERED'}">--%>
<%--                                    ${archive.localDate} o godzinie ${archive.localTime}--%>
<%--                                </c:if>--%>
<%--                            </c:forEach>--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                            <c:forEach items="${donation.archives}" var="archive">--%>
<%--                                <c:if test="${archive.status == 'ARCHIVED'}">--%>
<%--                                    ${archive.localDate} o godzinie ${archive.localTime}--%>
<%--                                </c:if>--%>
<%--                            </c:forEach>--%>
<%--                        </td>--%>
<%--                        <td><c:forEach items="${donation.categories}"--%>
<%--                                                                                                   var="category">${category.name},</c:forEach>--%>
<%--                        </td>--%>
<%--                        <td>--%>
<%--                                ${donation.quantity}--%>
<%--                        </td>--%>
<%--                         <c:choose>--%>
<%--                            <c:when test="${donation.archives.size()>1}">--%>
<%--                        <td style="background-color: red"></td>--%>
<%--                            </c:when>--%>
<%--                            <c:otherwise>--%>
<%--                              <td style="background-color: #1e7e34">  <a href="/donate/set-status/${donation.id}" class="btn btn--small">Zmień</a></td>--%>
<%--                            </c:otherwise>--%>
<%--                        </c:choose>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</section>--%>
<%@ include file="../fragments/appFooter.jsp" %>

<script src="<c:url value="../resources/js/app.js"/>"></script>
<script type="text/javascript" src="https://cdn.datatables.net/v/dt/dt-1.10.23/r-2.2.7/datatables.min.js"></script>
</body>
</html>
