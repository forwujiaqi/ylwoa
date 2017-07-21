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
    <script src="${pageContext.request.contextPath}/handsontable/dist/handsontable.full.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/handsontable/dist/handsontable.full.css"/>
    <style type="text/css">
        .handsontable {
            color: #000;
            font-size: 14px;
        }
    </style>
</head>

<body>
    <div id="wrapper">

        <!-- Navigation -->
        <%@ include file="../include/header.jsp"%>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">施工进度</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <form role="form" id="form" method="post" action="${pageContext.request.contextPath}/progress/edit">
                    <div class="row">
                        <div class="col-lg-12" >
                            <input value="${data.excelName}" id="excelName" name="excelName" class="form-control" placeholder="请填写施工进度名" style="width: 500px">
                            <br>
                            <label class="radio-inline">
                                <input type="radio" name="status" id="optionsRadiosInline1" value="0" <c:if test="${data.status == 0}">checked="checked"</c:if>>进行中
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="status" id="optionsRadiosInline2" value="1" <c:if test="${data.status == 1}">checked="checked"</c:if>>已完成
                            </label>
                            <br>
                            <input id="dataJson" name="dataJson" type="hidden" value="${data.dataJson}">
                            <input id="status" name="status" type="hidden" value="${data.status}">
                            <input id="id" name="id" type="hidden" value="${data.id}">
                            <br>
                        </div>
                        <div class="col-lg-12">
                            <div id="excelShow"></div>
                        </div>
                    <!-- /.col-lg-12 -->
                    </div>
                    <div class="row">
                        <div class="col-lg-12" style="text-align:center;width:1240px">
                            <br>
                            <button type="button" id="save" class="btn btn-success">保存</button>
                            <a href="/progress/list/0" class="btn btn-warning" role="button">退出</a>
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
    <script src="${pageContext.request.contextPath}/assets/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath}/assets/dist/js/sb-admin-2.js"></script>

    <script type="text/javascript">

        function getData() {
            return [[]];
        }

        var excelShow = document.getElementById('excelShow'),
            settings1,
            hot;

        settings1 = {
            data: getData(),
            startRows: 5,
            startCols: 5,
            minRows: 30,
            minCols: 26,
            manualColumnResize: true,
            manualRowResize: true,
            rowHeaders: true,
            colHeaders: true,
            minSpareRows: 1,
            manualColumnMove: true,
            manualRowMove: true,
            mergeCells: [],
            //       contextMenu:true
            contextMenu: ['row_above', 'row_below', '---------','col_left','col_right','---------','remove_row','remove_col','---------','mergeCells','---------','undo','redo']
        };
        hot = new Handsontable(excelShow, settings1);


        $(document).ready(function () {
           load()
        });

        function load() {
            var aa = ${data.dataJson}
            var mergeInfo = aa.data.mergeInfo
            for (var i = 0; i < mergeInfo.length; i++) {
                settings1.mergeCells.push(mergeInfo[i]);
            }
            settings1.data = aa.data.dataInfo
            hot.destroy()
            hot = new Handsontable(excelShow, settings1);
            hot.render()
        }

    </script>
    <script type="text/javascript">
        $("#save").click(function () {
            $("#save").attr("disabled",true)
            var cellJson = new Object();
            cellJson.dataInfo = hot.getData();
            cellJson.mergeInfo = hot.mergeCells.mergedCellInfoCollection;
            var dataJson = JSON.stringify({data: cellJson})
            $("#dataJson").val(dataJson);
            $("#form").method="post";
            $("#form").submit();
        });
    </script>
</body>

</html>
