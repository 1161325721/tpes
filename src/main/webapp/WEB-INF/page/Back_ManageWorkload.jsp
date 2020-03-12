<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/1
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = application.getContextPath(); %>
<html>
<head>
    <title>工作量统计</title>
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
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div>
                <label class="layui-form-label">统计日期</label>
                <div class="layui-input-inline">
                    <input type="text" name="fdate" id="fdate" placeholder="请选择日期" class="layui-input">
                </div>
            </div>
            <div>
                <label class="layui-form-label">至</label>
                <div class="layui-input-inline">
                    <input type="text" name="edate" id="edate" placeholder="请选择日期" class="layui-input">
                </div>
            </div>
            <div>
                <label class="layui-form-label">统计人员</label>
                <div class="layui-input-inline">
                    <select name="issueWid" lay-filter="issueWid" id="issueWid">
                        <option value="0" selected=""></option>
                    </select>
                </div>
            </div>
            <div>
                <div class="layui-input-inline" style="margin-left: 35px">
                    <button class="layui-btn" lay-submit lay-filter="query" id="query">查询</button>
                </div>
            </div>
        </div>
    </form>
    <table class="layui-table" id="dataTable" lay-filter="dataTable"></table>
</div>
<div style="display: none">
    <input type="hidden" id="path" value=<%=path%>>
</div>
</body>
<script src=<%=path + "/resources/layui/layui.js"%>></script>
<script src=<%=path + "/resources/js/Back_ManageWorkload.js"%>>
    //JavaScript代码区域
</script>
</html>
