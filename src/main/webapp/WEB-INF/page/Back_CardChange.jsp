<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/26
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = application.getContextPath(); %>
<html>
<head>
    <title>换卡页面</title>
    <link rel="stylesheet" href=<%=path+"/resources/layui/css/layui.css"%>>
    <style>
        #layout2 {
            width: 700px;
            height: 450px;
            margin: auto;
            padding-top: 70px;
            padding-right: 30px;
            border: #9F9F9F solid 1px;
        }

    </style>
</head>
<body>
<div id="layout2">
    <form class="layui-form" action="" id="changeform">
        <div class="layui-form-item">
            <label class="layui-form-label">身份证:</label>
            <div class="layui-input-inline">
                <input type="text" name="pidentitynumber" required lay-verify="required" placeholder="请输入证件号"
                       autocomplete="off" class="layui-input" id="pidentitynumber" style="width: 300px">
            </div>
            <div class="layui-input-inline" style="margin-left: 180px">
                <button type="button" class="layui-btn" style="width: 120px" id="query"><span class="layui-icon layui-icon-search"></span>查询</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名:</label>
            <div class="layui-form-mid layui-word" id="pname">

            </div>

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别:</label>
            <div class=" layui-form-mid layui-word" id="pgender">

            </div>
            <label class="layui-form-label">籍贯:</label>
            <div class=" layui-form-mid layui-word" id="origoName">

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证号:</label>
            <div class="layui-form-mid layui-word" id="showpidentitynumber">

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">联系电话:</label>
            <div class="layui-form-mid layui-word" id="pphone">

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">所属单位:</label>
            <div class="layui-form-mid layui-word" id="gcname">

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新卡号:</label>
            <div class="layui-input-inline">
                <input type="text" name="registerPassword" required lay-verify="required" placeholder="请输入卡号"
                       autocomplete="off" class="layui-input" id="cardNo" style="width: 300px">
            </div>
            <div class="layui-input-inline" style="margin-left: 180px">
                <button type="button" class="layui-btn" style="width: 120px"  id="readcard"><span class="layui-icon layui-icon-templeate-1"></span>读卡</button>
            </div>
        </div>
        <div class="layui-form-item" style="padding-left: 26%">
            <div class="layui-input-block" id="btgroupreg">
                <button class="layui-btn layui-btn-disabled" lay-submit lay-filter="commit" id="commit" >换卡</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<div style="display: none">
    <input type="hidden" id="path" value=<%=path%>>
</div>
</body>
<script src=<%=path + "/resources/layui/layui.js"%>></script>
<script src=<%=path + "/resources/js/Back_CardChange.js"%>>
    //JavaScript代码区域
</script>
</html>
