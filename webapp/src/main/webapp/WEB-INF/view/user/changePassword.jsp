<%@ page language="java" pageEncoding="utf-8"%>
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
    <link href="${pageContext.request.contextPath}/assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

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
        <%@ include file="../include/header.jsp"%>

        <!-- Page Content -->
        <div id="page-wrapper" style="margin-left: 200px">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">修改密码</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <form role="form" id="form" method="post" action="${pageContext.request.contextPath}/user/changePassword">
                    <c:if test="${success == false}">
                        <div class="alert alert-danger" role="alert">${message}</div>
                    </c:if>
                    <div class="row">
                        <div class="col-lg-12" >
                            <input id="password1"
                                   name="password1" class="form-control" type="password" placeholder="原密码"
                                   style="width: 500px">
                            <br>
                        </div>
                        <div class="col-lg-12" >
                            <input id="password2" type="password"  placeholder="新密码"
                                   name="password2" class="form-control"
                                   onfocus="$('#password2').popover('show');" data-placement="right" data-toggle="popover" data-content="密码由8~12位的半角英数字组成"
                                   style="width: 500px">
                            <br>
                        </div>
                        <div class="col-lg-12" >
                            <input id="password3" type="password"  placeholder="再次输入新密码"
                                   name="password3" class="form-control"
                                   onfocus="$('#password3').popover('show');" data-placement="right" data-toggle="popover" data-content="密码由8~12位的半角英数字组成"
                                   style="width: 500px">
                            <br>
                        </div>
                    <!-- /.col-lg-12 -->
                    </div>
                    <div class="row">
                        <div class="col-lg-12" style="text-align:center;width: 500px">
                            <br>
                            <button type="button" id="save" class="btn btn-success">提交</button>
                            <a href="/jobRecord/list/0" class="btn btn-warning" role="button">退出</a>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                </form>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/dist/js/sb-admin-2.js"></script>

    <script type="text/javascript">

    </script>
    <script type="text/javascript">
        $("#save").click(function () {
            $(this).button('loading')
            $("#form").method="post";
            $("#form").submit();
        });
    </script>
</body>

</html>
