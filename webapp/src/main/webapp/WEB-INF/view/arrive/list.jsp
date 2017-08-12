<%@ page import="java.util.Date" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html lang="en">
<c:set var="nowDate" value="<%=System.currentTimeMillis()%>"></c:set>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>明仁信息管理系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/assets/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/assets/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet"
          type="text/css">

    <!-- DataTables CSS -->
    <link href="${pageContext.request.contextPath}/assets/vendor/datatables-plugins/dataTables.bootstrap.css"
          rel="stylesheet">

    <!-- DataTables Responsive CSS -->
    <link href="${pageContext.request.contextPath}/assets/vendor/datatables-responsive/dataTables.responsive.css"
          rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <%@ include file="../include/header.jsp" %>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">到货清单</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <%--<div class="panel panel-default">--%>
                    <%--<div class="panel-heading">--%>
                    <%--DataTables Advanced Tables--%>
                    <%--</div>--%>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <form id="form1" name="form1">
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables">
                                <thead>
                                <tr>
                                    <th style="text-align: center">序号</th>
                                    <th style="text-align: center">到货清单</th>
                                    <th style="text-align: center">状态</th>
                                    <th style="text-align: center">完成时间</th>
                                    <th class="visible-md"
                                        style="text-align: center">创建人</th>
                                    <th class="visible-md" style="text-align: center">创建时间</th>
                                    <th style="text-align: center">修改人</th>
                                    <th style="text-align: center">修改时间</th>
                                    <th style="text-align: center">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pageData}" var="item" varStatus="status">
                                    <c:choose>
                                        <c:when test="${item.status == 1}">
                                            <tr class="success">
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                        </c:otherwise>
                                    </c:choose>
                                    <td style="text-align: right;vertical-align: middle">${status.index+1}</td>
                                    <td style="text-align: left;vertical-align: middle">${item.excelName}</td>
                                    <td style="text-align: left;vertical-align: middle">
                                        <c:if test="${item.status != 0}">已完成</c:if>
                                        <c:if test="${item.status == 0}">进行中</c:if>
                                    </td>
                                    <td style="text-align: left;vertical-align: middle">
                                        <c:if test="${item.status != 0}">
                                            <fmt:formatDate
                                                    value="${item.updateTime}" type="date" pattern="yyyy-MM-dd"/>
                                        </c:if>
                                    </td>
                                    <td class="visible-md" style="text-align: left;vertical-align: middle">${item.createUserName}</td>
                                    <td class="visible-md" style="text-align: left;vertical-align: middle"><fmt:formatDate
                                            value="${item.createTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td style="text-align: left;vertical-align: middle">${item.updateUserName}</td>
                                    <td style="text-align: left;vertical-align: middle"><fmt:formatDate
                                            value="${item.updateTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
                                    <td style="text-align: center;vertical-align: middle">
                                        <button type="button" class="btn btn-primary"
                                                onclick="return editProgress(${item.id})">编辑
                                        </button>
                                        <button type="button" class="btn btn-info" id="view"
                                                onclick="return showProgress(${item.id})">查看
                                        </button>
                                        <button type="button" class="btn btn-danger"
                                                onclick="return deleteProgress(${item.id})">删除
                                        </button>
                                    </td>

                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                    </div>
                    </form>
                    <!-- /.panel-body -->
                    <%--</div>--%>
                    <!-- /.panel -->
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 id="title" class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div id="content" class="modal-body">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/assets/vendor/metisMenu/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath}/assets/dist/js/sb-admin-2.js"></script>

<!-- DataTables JavaScript -->
<script src="${pageContext.request.contextPath}/assets/vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/vendor/datatables-responsive/dataTables.responsive.js"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
    var table
    $(document).ready(function () {
        table = $('#dataTables').DataTable({
            responsive: true,
            "bStateSave": true,
            "stateDuration": -1,
        });

        var pageNo = "${pageNo}"
        if (pageNo == 0) {
            table.page(0).draw(false);
            table.search("").draw();
        }
    });

    function showProgress(progressId) {
        document.form1.action = "${ctx}/arrive/toView/" + progressId;
        document.form1.submit();
        return true;
    }

    function editProgress(progressId) {
        document.form1.action = "${ctx}/arrive/toEdit/" + progressId;
        document.form1.submit();
        return true;
    }

    function deleteProgress(progressId) {
        if (confirm('确定要删除吗?')) {
            document.form1.action = "${ctx}/arrive/delete/" + progressId;
            document.form1.submit();
            return true;
        }
        return false;
    }
</script>
</body>

</html>
