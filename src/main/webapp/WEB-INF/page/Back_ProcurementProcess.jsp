<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/4
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = application.getContextPath(); %>
<html>
<head>
    <title>一卡通采购流程</title>
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
    <button class="layui-btn" type="button" id="popup">新增采购</button>
    <div>
        <h2>我的采购申请</h2>
    <table class="layui-table" id="dataTable1" lay-filter="dataTable"></table>
    </div>
    <div>
        <h2>我的申请记录</h2>
    <table class="layui-table" id="dataTable2" lay-filter="dataTable"></table>
    </div>
</div>
<div style="display: none ;padding-right: 25px" id="popupface">
    <div id="layout1">
        <form class="layui-form" action="" id="commitform">
            <div class="layui-form-item">
                <label class="layui-form-label">申请日期:</label>
                <div class="layui-input-block">
                    <div class=" layui-form-mid layui-word">
                        <span id="applyDate"></span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">请购数量:</label>
                <div class="layui-input-block">
                    <input type="text" name="days" required lay-verify="required" placeholder="请输入请购数量"
                           autocomplete="off" class="layui-input" id="days">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">请购原因:</label>
                <div class="layui-input-block">
                    <input type="text" name="reason" required lay-verify="required" placeholder="请输入请购原因"
                           autocomplete="off" class="layui-input" id="reason">
                </div>
            </div>
            <div class="layui-form-item" style="padding-left: 20%">
                <div class="layui-input-block" id="btgrouplogin">
                    <button class="layui-btn" lay-submit lay-filter="commit" id="commit">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div style="display: none">
    <input type="hidden" id="path" value=<%=path%>>
</div>
</body>
<script src=<%=path + "/resources/layui/layui.js"%>></script>
<script src=<%=path + "/resources/js/Back_ProcurementProcess.js"%>>
    //JavaScript代码区域
</script>
</html>
