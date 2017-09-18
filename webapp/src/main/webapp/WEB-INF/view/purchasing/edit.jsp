<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                        <h1 class="page-header">编辑采供单</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <form role="form" id="form" method="post" action="${pageContext.request.contextPath}/purchasing/edit">
                    <c:if test="${success == false}">
                        <div class="alert alert-danger" role="alert">${message}</div>
                    </c:if>
                    <div class="row">
                        <div class="col-lg-12" >
                            <input value="${data.excelName}" id="excelName" name="excelName" class="form-control" placeholder="请填写采供单名称" style="width: 500px">
                            <br>
                            <div class="form-group">
                                <form:select path="projectList" class="form-control" style="width: 500px" name="projectId" id="projectId">
                                    <c:forEach items="${projectList}" var="item">
                                        <form:option value="${item.id}">${item.projectName}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
                        <%--<div class="col-lg-12" >--%>
                            <%--<input id="owner" name="owner" autocomplete="off">&nbsp;&nbsp;*请填写项目负责人，可以填多个，以半角逗号分隔--%>
                            <%--<input type="hidden" name="ownerName" id="ownerName">--%>
                        <%--</div>--%>
                        <div class="col-lg-12" >
                            <label>工期：</label>
                            <input style="width: 250px" type="text" name="progressRange" readonly class="form-control active"
                                   id="progressRange">
                        </div>
                        <div class="col-lg-12" >
                            <br>
                            <label class="radio-inline">
                                <input type="radio" name="status" id="optionsRadiosInline1" value="0" <c:if test="${data.status == 0}">checked="checked"</c:if>>进行中
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="status" id="optionsRadiosInline2" value="1" <c:if test="${data.status == 1}">checked="checked"</c:if>>已完成
                            </label>
                            <input id="dataJson" name="dataJson" type="hidden" value="${data.dataJson}">
                            <%--<input id="status" name="status" type="hidden" value="${data.status}">--%>
                            <input id="id" name="id" type="hidden" value="${data.id}">
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
                            <button type="button" id="save" class="btn btn-success">保存</button>
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
    <script src="${pageContext.request.contextPath}/datepicker/bootstrap-datepicker.js"></script>

    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-manifest-master/build/parts/jquery.ui.widget.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-manifest-master/build/jquery.manifest.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jquery-manifest-master/build/parts/jquery.marcopolo.js"></script>


    <script type="text/javascript">

        function negativeValueRenderer(instance, td, row, col, prop, value, cellProperties) {
            Handsontable.renderers.TextRenderer.apply(this, arguments);

            if (value === 'OK' || value === '可行') {
                td.style.background = '#CEC';
            }

            if (value === 'NG' || value === '不可行') {
                td.style.background = '#F08080';
            }

            if (value === '需要延误') {
                td.style.background = 'yellow';
            }
        }

        Handsontable.renderers.registerRenderer('negativeValueRenderer', negativeValueRenderer);

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
            minRows: 25,
            minCols: 18,
            manualColumnResize: true,
            manualRowResize: true,
            rowHeaders: true,
            colHeaders: true,
            colHeaders: ['名称','品牌','规格','单位','合同内数量','合同外数量','要求到货时间','采购部反馈意见','备注','工程部反馈意见','最终协商决议','最终发货时间','确认','备注','最终收货时间','确认','备注','发货确认人','收货确认人'],
            minSpareRows: 1,
//            manualColumnMove: true,
//            manualRowMove: true,
            columns: [
                {},
                {},
                {},
                {},
                {},
                {},
                {
                    type: 'date',
                    dateFormat: 'YYYY/MM/DD',
                    correctFormat: true
                },
                {
                    type: 'dropdown',
                    source: ['可行', '需要延误', '不可行']
                },
                {},
                {},
                {},
                {
                    type: 'date',
                    dateFormat: 'YYYY/MM/DD',
                    correctFormat: true
                },
                {
                    type: 'dropdown',
                    source: ['OK', 'NG']
                },
                {},
                {
                    type: 'date',
                    dateFormat: 'YYYY/MM/DD',
                    correctFormat: true
                },
                {
                    type: 'dropdown',
                    source: ['OK', 'NG']
                },
                {},
                {},
                {}
            ],
            cells: function (row, col, prop) {
                var cellProperties = {};

                if (col === 7 || col === 12 || col === 15) {
                    cellProperties.renderer = "negativeValueRenderer"; // uses lookup map
                }
                return cellProperties;
            },
            mergeCells: [],
            //       contextMenu:true
            contextMenu: ['row_above', 'row_below', '---------','col_left','col_right','---------','remove_row','remove_col','---------','mergeCells','---------','undo','redo']
        };
        hot = new Handsontable(excelShow, settings1);


        $(document).ready(function () {
            $("#projectId").val(${data.projectId});

           load()

            $('#progressRange').daterangepicker({
                <c:if test="${data.planStartDate!=null and data.planEndDate!=null}">
                    startDate: "<fmt:formatDate value="${data.planStartDate}" type="date" pattern="yyyy-MM-dd"/>",
                    endDate: "<fmt:formatDate value="${data.planEndDate}" type="date" pattern="yyyy-MM-dd"/>",
                </c:if>
                timePicker: true,
                timePickerIncrement: 1,
                locale: {
                    format: 'YYYY-MM-DD'
                }
            });
            <%--$("#owner").val("${data.ownerName}")--%>
            <%--$('#owner').manifest();--%>
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
            if($("input[name='status']:checked").val() == 1) {
                if(hot.getData()[0][17] == "" || hot.getData()[0][17] == null || hot.getData()[0][17] == undefined){
                    alert("请填写发货确认人");
                    return;
                }

                if(hot.getData()[0][18] == "" || hot.getData()[0][18] == null || hot.getData()[0][18] == undefined){
                    alert("请填写收货确认人");
                    return;
                }
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
