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

    <link href="${pageContext.request.contextPath}/daterangepicker/daterangepicker.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/jquery-manifest-master/build/manifest.css" rel="stylesheet">

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
                        <h1 class="page-header">新建采购单</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <form role="form" id="form" method="post" action="${pageContext.request.contextPath}/purchasing/add">
                    <div class="row">
                        <div class="col-lg-12" >
                            <input id="excelName" name="excelName" class="form-control" placeholder="请填写采购单名称" style="width: 500px">
                            <input id="dataJson" name="dataJson" type="hidden">
                            <input id="settingJson" name="settingJson" type="hidden">
                            <br>
                            <div class="form-group" hidden>
                                <label>工期：</label>
                                <input style="width: 250px" type="text" name="progressRange" readonly class="form-control active"
                                          id="progressRange">
                                <br>
                            </div>

                        </div>
                        <div class="col-lg-12" >
                            <input id="owner" name="owner" autocomplete="off">
                            <input type="hidden" name="ownerName" id="ownerName">
                        </div>
                        <div class="col-lg-12">
                            <br>
                            <div id="excelShow"></div>
                        </div>
                    <!-- /.col-lg-12 -->
                    </div>
                    <div class="row">
                        <div class="col-lg-12" style="text-align:center;width:100%">
                            <br>
                            <button type="button" id="save" class="btn btn-success">提交</button>
                            <a href="/purchasing/list/0" class="btn btn-warning" role="button">退出</a>
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

    <script src="${pageContext.request.contextPath}/daterangepicker/moment.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/daterangepicker/daterangepicker.js"></script>

    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-manifest-master/build/parts/jquery.ui.widget.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-manifest-master/build/jquery.manifest.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-manifest-master/build/parts/jquery.marcopolo.js"></script>


    <script type="text/javascript">

        function getData() {
            return [
                ['', 'Kia', 'Nissan', 'Toyota', 'Honda'],
                ['2008', 10, 11, 12, 13],
                ['2009', 20, 11, 14, 13],
                ['2010', 30, 15, 12, 13]
            ];
        }

        var excelShow = document.getElementById('excelShow'),
            settings1,
            hot;

        settings1 = {
            data: getData(),
            startRows: 5,
            startCols: 5,
            minRows: 25,
            minCols: 18,
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


        $('#progressRange').daterangepicker({
            timePicker: true,
            timePickerIncrement: 1,
            locale: {
                format: 'YYYY-MM-DD'
            }
        });

        $('#owner').manifest();

    </script>
    <script type="text/javascript">
        $("#save").click(function () {
            if($('#owner').manifest('values') == ""){
                alert("请填写项目负责人");
                return;
            }

            $(this).button('loading')
            var cellJson = new Object();
            cellJson.dataInfo = hot.getData();
            cellJson.mergeInfo = hot.mergeCells.mergedCellInfoCollection;
            var dataJson = JSON.stringify({data: cellJson})
            $("#dataJson").val(dataJson);
            $("#ownerName").val($('#owner').manifest('values'));
            $("#form").method="post";
            $("#form").submit();
        });
    </script>
</body>

</html>
