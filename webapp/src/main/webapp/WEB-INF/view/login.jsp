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

    <title>OA</title>

    <%--TODO 这里为什么不用pageContext.request.contextPath --%>
    <!-- Bootstrap Core CSS -->
    <link href="/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/assets/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/assets/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        input.captchar_style {
            height: 34px;
            padding: 6px 12px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555;
            background-color: #fff;
            background-image: none;
            border: 1px solid #ccc;
            border-radius: 4px;
            -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
            -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
            -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
            transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
        }

        #footer{
            position: absolute;  bottom: 0; /* 关键 */
            left:45%; /* IE下一定要记得 */
            height: 20px;         /* footer的高度一定要是固定值*/
        }
    </style>
</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">OA</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" action="${pageContext.request.contextPath}/login" method="post">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="请输入手机号" value="${phone}" name="phone" required  autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="请输入密码" name="password" type="password" required value="">
                                </div>
                                <div class="form-group">
                                    <input class="captchar_style" required
                                           id="mcode" name="mcode" value="" placeholder="请输入右边的验证码"
                                           class="hst2"  style="vertical-align:middle"/>
                                    <img id=""  style="vertical-align:middle" alt="验证码"
                                         onclick="this.src='${pageContext.request.contextPath}/kaptcha/getKaptchaImage?e='+new Date()"
                                         src="${pageContext.request.contextPath}/kaptcha/getKaptchaImage"/>
                                    <img id="mcodecheck" src="" alt=""  style="vertical-align:middle"/>
                                </div>
                                <button type="submit" class="btn btn-lg btn-success btn-block">登录</button>
                            </fieldset>
                        </form>
                    </div>
                    <c:if test="${success == false}">
                        <div class="alert alert-danger" role="alert">${message}</div>
                    </c:if>
                    <c:if test="${success == true}">
                        <div class="alert alert-success" role="alert">${message}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <div id="footer">
        <a target="_blank" href="http://www.miitbeian.gov.cn/" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;">
            <img style="float: left; width: 20px;" src="//img.alicdn.com/tfs/TB1..50QpXXXXX7XpXXXXXXXXXX-40-40.png"><span style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">苏ICP备17048723号</span>
        </a>
    </div>

    <!-- jQuery -->
    <script src="/assets/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/assets/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/assets/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/assets/dist/js/sb-admin-2.js"></script>

</body>

</html>
