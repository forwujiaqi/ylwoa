<%@ page language="java" pageEncoding="utf-8"%>
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
        <%@ include file="include/header.jsp"%>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">明仁信息管理系统</h1>
                    </div>
                    <div><p>请尽快修改默认密码。（用户管理-&gt;修改密码）</p></div>
                    <br>
                    <div><p><span style="color: rgb(255, 0, 0);">2017.10.11发布  version 1.6</span></p></div>
                    <div><p><span style="color: rgb(255, 0, 0);">1.施工日志增加领导批阅功能，批阅内容可以点查看按钮查看</span></p></div>
                    <div><p><span style="color: rgb(255, 0, 0);">2.施工日志列表增加状态、更新人、更新时间栏，已阅日志显示绿色</span></p></div>
                    <br>
                    <div><p><span>2017.9.26发布  version 1.5</span></p></div>
                    <div><p><span>1.数据自动化备份策略修改：</span></p></div>
                    <div><p><span>数据每天17:30分自动在阿里云本地备份一份</span></p></div>
                    <div><p><span>数据每天17:45分自动复制一份到七牛云存储</span></p></div>
                    <div><p><span>2.用户体验方面修改：</span></p></div>
                    <div><p><span>列表排序方式变更：先按状态排序，相同的情况按修改时间降序排列</span></p></div>
                    <br>
                    <div><p><span>2017.9.17发布  version 1.4</span></p></div>
                    <div><p><span>采供单layout调整</span></p></div>
                    <div><p><span>搜索文字改为筛选</span></p></div>
                    <div><p><span>已完成保存时，添加发货收货确认人check</span></p></div>
                    <br>
                    <div><p><span>2017.9.8发布  version 1.3</span></p></div>
                    <div><p><span>验证码输错时，不需要再重新输入手机号</span></p></div>
                    <br>
                    <div><p><span>2017.9.7发布  version 1.2</span></p></div>
                    <div><p><span>数据库备份功能上线。每天17:30分备份一次</span></p></div>
                    <br>
                    <div><p><span>2017.9.4发布  version 1.1</span></p></div>
                    <div><p><span>采供系统颜色功能添加</span></p></div>
                    <div><p><span>工程管理模块功能添加</span></p></div>
                    <div><p><span>原有功能关联工程</span></p></div>
                    <br>
                    <div><p><span>2017.9.3发布 version 1.0</span></p></div>
                    <div><p><span>采购模块和到货模块合并为采供模块</span></p></div>
                    <div><p><span>采供模块提供模板功能</span></p></div>
                    <div><p><span>采供模块excel添加下拉框选择功能</span></p></div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/jquery/jquery-1.9.1.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src=".${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/dist/js/sb-admin-2.js"></script>

</body>

</html>
