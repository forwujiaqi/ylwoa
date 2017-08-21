<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html lang="en">

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

    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/ueditor/ueditor.parse.min.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/ueditor/lang/zh-cn/zh-cn.js"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <% String permit = ""; %>
</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <%@ include file="../include/header.jsp" %>

    <!-- Page Content -->
    <div id="page-wrapper" style="margin-left: 200px">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">日志列表</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <c:if test="${success == false}">
                    <div class="alert alert-danger" role="alert">${message}</div>
                </c:if>
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
                                    <th style="text-align: center">日报</th>
                                    <th style="text-align: center">填写人</th>
                                    <th style="text-align: center">填写时间</th>
                                    <th style="text-align: center">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pageData}" var="item" varStatus="status">
                                    <tr class="odd gradeX">
                                        <td style="text-align: right;vertical-align: middle">${status.index+1}</td>
                                        <td style="text-align: left;vertical-align: middle">${item.recordName}</td>
                                        <td style="text-align: left;vertical-align: middle">${item.createUserName}</td>
                                        <td style="text-align: left;vertical-align: middle"><fmt:formatDate
                                                value="${item.createTime}" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td style="text-align: center;vertical-align: middle">
                                            <c:if test="${sessionScope.USER_SESSION_MARK.id == item.createUserId or
                                             (permitNo == 1 or permitNo == 3)}">
                                                <button type="button" class="btn btn-primary"
                                                        onclick="return editRecord(${item.id})">编辑
                                                </button>
                                            </c:if>
                                            <button type="button" class="btn btn-info" id="view"
                                                    onclick="return showRecord(${item.id})">查看
                                            </button>
                                            <c:if test="${sessionScope.USER_SESSION_MARK.id == item.createUserId or
                                             (permitNo == 1 or permitNo == 3)}">
                                                <button type="button" class="btn btn-danger"
                                                        onclick="return deleteRecord(${item.id})">删除
                                                </button>
                                            </c:if>
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
        if(pageNo == 0) {
            table.page(0).draw(false);
            table.search("").draw();
        }
    });

    function showRecord(recordId) {
        document.form1.action = "${ctx}/jobRecord/toView/" + recordId;
        document.form1.submit();
        return true;
    }

    function editRecord(recordId) {
        $(this).button('loading')
        document.form1.action = "${ctx}/jobRecord/toEdit/" + recordId;
        document.form1.submit();
        return true;
    }

    function deleteRecord(recordId) {
        if (confirm('确定要删除吗?')) {
            $(this).button('loading')
            document.form1.action = "${ctx}/jobRecord/delete/" + recordId;
            document.form1.submit();
            return true;
        }
        return false;
    }
</script>
</body>

</html>
