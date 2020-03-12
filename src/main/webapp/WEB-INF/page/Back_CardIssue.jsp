<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/26
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = application.getContextPath(); %>
<html>
<head>
    <title>发卡页面</title>
    <link rel="stylesheet" href=<%=path+"/resources/layui/css/layui.css"%>>
    <style>
        #layout2 {
            width: 700px;
            height:400px;
            margin: auto;
            padding-top: 70px;
            padding-right: 30px;
            border: #9F9F9F solid 1px;
        }

    </style>
</head>
<body>
<div id="layout2">
    <form class="layui-form" action="" lay-filter="issueform" id="issueform">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名:</label>
            <div class="layui-input-block" >
                <input type="text" name="pname" required lay-verify="required" placeholder="请输入名称"
                       class="layui-input" id="pname" style="width: 500px" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-inline">
                <input type="radio" name="pgender" value="1" title="男">
                <input type="radio" name="pgender" value="0" title="女" checked>
            </div>
            <label class="layui-form-label">籍贯:</label>
            <div class="layui-input-inline">
                <input type="text" name="origoName" required lay-verify="required" placeholder="请输入籍贯"

                       class="layui-input" id="origoName">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证号:</label>
            <div class="layui-input-inline">
                <input type="text" name="pidentitynumber" required lay-verify="required" placeholder="请输入身份证号"
                       class="layui-input" id="pidentitynumber" style="width: 350px" >
            </div>
            <div class="layui-input-inline" style="margin-left: 180px" >
                <button type="button" class="layui-btn"  style="width: 120px" id="query"   ><span class="layui-icon layui-icon-search"></span>查询</button>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机:</label>
            <div class="layui-input-block">
                <input type="tel" name="pphone" required lay-verify="phone" placeholder="请输入手机"
                       class="layui-input" id="pphone" style="width: 500px" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">所属单位:</label>
            <div class="layui-input-block">
                <input type="text" name="gcname" required lay-verify="required" placeholder="请输入单位名称"
                       class="layui-input" id="gcname" style="width: 500px" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">卡号:</label>
            <div class="layui-input-inline">
                <input type="text" name="cardNo" required lay-verify="required" placeholder="请输入卡号"
                       class="layui-input" id="cardNo" style="width: 350px">
            </div>
            <div class="layui-input-inline" style="margin-left: 180px" >
                <button type="button" class="layui-btn"  style="width: 120px" id="readcard"><span class="layui-icon layui-icon-templeate-1"></span>读卡</button>
            </div>
        </div>
        <div class="layui-form-item">
            <span id="showMsg"></span>
        </div>
        <div class="layui-form-item" style="padding-left: 26%">
            <div class="layui-input-block" id="btgroupreg">
                <button class="layui-btn layui-btn-disabled" lay-submit lay-filter="commit" id="commit" >发卡</button>
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
<script src=<%=path + "/resources/js/Back_CardIssue.js"%>>
    //JavaScript代码区域
</script>
</html>
