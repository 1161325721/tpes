<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/3/4
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = application.getContextPath(); %>
<html>
<head>
    <title>一卡通采购流程人员设置</title>
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
                <label class="layui-form-label">工作人员</label>
                <div class="layui-input-inline">
                    <input type="text" name="userName" id="userName" placeholder="请输入工作人员名称" class="layui-input">
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
    <script type="text/html" id="barDemo">
        <a class="layui-btn  layui-btn-sm" lay-event="setting">设置</a>
    </script>
</div>
<div style="display: none ;padding-right: 25px" id="popupface">
    <div id="layout1">
        <form class="layui-form" action="" id="commitform">
            <div class="layui-form-item">
                <label class="layui-form-label">人员姓名:</label>
                <div class="layui-input-block">
                    <div class=" layui-form-mid layui-word">
                        <span id="showUserName"></span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">权限节点:</label>
                <div class="layui-input-inline">
                    <select name="groupId" lay-filter="groupId">
                        <option value=""selected=""></option>
                        <option value="empGroup">仓管组</option>
                        <option value="manageGroup" >经理组</option>
                        <option value="dirGroup" >总监组</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item" style="padding-left: 20%">
                <div class="layui-input-block" id="btgrouplogin" >
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
<script src=<%=path + "/resources/js/Back_ProcurementProcessSetting.js"%>>
    //JavaScript代码区域
</script>
</html>
