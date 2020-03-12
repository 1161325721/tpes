<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/5
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = application.getContextPath(); %>
<html>
<head>
    <title>一卡通采购流程审核</title>
    <link rel="stylesheet" href=<%=path+"/resources/layui/css/layui.css"%>>
    <style>
        #layout {
            width: 1000px;
            height: 500px;
            margin: auto;
            padding-top: 20px;
        }
    </style>
</head>
<body>
<div id="layout">
    <div>
        <h2>待我审核的采购单</h2>
    </div>
    <div>
        <table class="layui-table" id="dataTable1" lay-filter="dataTable"></table>
    </div>
    <div>

    </div>
        <h2>我的审核记录</h2>
    <div>
        <table class="layui-table" id="dataTable2" lay-filter="dataTable"></table>
    </div>
    <script type="text/html" id="barDemo">
        <a class="layui-btn  layui-btn-warm layui-btn-sm" lay-event="pass">批准</a>
        <a class="layui-btn layui-btn-danger layui-btn-sm" lay-event="back">退回</a>
    </script>
</div>
<div style="display: none">
    <input type="hidden" id="path" value=<%=path%>>
</div>
</body>
<script src=<%=path + "/resources/layui/layui.js"%>></script>
<script src=<%=path + "/resources/js/Back_ManageProcurementProcess.js"%>>
    //JavaScript代码区域
</script>
</html>
