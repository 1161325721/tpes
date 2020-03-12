<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/20
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = application.getContextPath(); %>
<html>
<head>
    <title>卡片入库</title>
    <link rel="stylesheet" href=<%=path+"/resources/layui/css/layui.css"%>>
    <style>
        #layout{
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
                <label class="layui-form-label">卡前缀</label>
                <div class="layui-input-inline">
                    <input type="text" name="cardPrefix" placeholder="请输入卡前缀" class="layui-input">
                </div>
            </div>
            <div>
                <label class="layui-form-label">开始编号</label>
                <div class="layui-input-inline">
                    <input type="text" name="cardSfxstart" placeholder="请输入开始编号" class="layui-input">
                </div>
            </div>
            <div>
                <label class="layui-form-label">截止编号</label>
                <div class="layui-input-inline">
                    <input type="text" name="cardSfxend" placeholder="请输入截止编号" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div>
                <label class="layui-form-label">导入时间</label>
                <div class="layui-input-inline">
                    <input type="text" name="fdate" id="fdate" placeholder="请选择日期" class="layui-input" readonly>
                </div>
            </div>
            <div>
                <label class="layui-form-label">至</label>
                <div class="layui-input-inline">
                    <input type="text" name="edate" id="edate" placeholder="请选择日期" class="layui-input"
                           readonly>
                </div>
            </div>
            <div>
                <div class="layui-input-inline"  style="margin-left: 35px ">
                    <button class="layui-btn" lay-submit lay-filter="query" id="query"><span class="layui-icon layui-icon-search"></span>查询</button>
                    <button type="button" class="layui-btn  layui-btn-warm"   id="popup" >卡入库</button>
                </div>
            </div>
        </div>
    </form>
    <table class="layui-table" id="dataTable" lay-filter="dataTable"></table>

    <script type="text/html" id="barDemo">
        <a class="layui-btn  layui-btn-sm" lay-event="detail">详情</a>
    </script>
</div>
<div style="display: none ;padding-right: 25px" id="popupface">
    <div id="layout1">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">卡前缀:</label>
                <div class="layui-input-block">
                    <input type="text" name="cardPrefix" required lay-verify="required" placeholder="请输入前缀" autocomplete="off"
                           class="layui-input"  >
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">开始卡号:</label>
                <div class="layui-input-block">
                    <input type="text" name="cardSfxstart" required lay-verify="number" placeholder="请输入开始卡号"
                           autocomplete="off" class="layui-input"  >
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">截止卡号:</label>
                <div class="layui-input-block">
                    <input type="text" name="cardSfxend" required lay-verify="number" placeholder="请输入截止卡号"
                           autocomplete="off" class="layui-input" >
                </div>
            </div>
            <div class="layui-form-item" style="padding-left: 20%">
                <div class="layui-input-block" id="btgrouplogin">
                    <button class="layui-btn" lay-submit lay-filter="commit" id="commit" >入库</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div style="display: none ;padding-right: 25px" id="popupface2">
    <div id="layout2">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <label class="layui-form-label">卡前缀:</label>
                <div class="layui-input-block">
                    <div class=" layui-form-mid layui-word">
                        <span id="cardprefix"></span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">开始卡号:</label>
                <div class="layui-input-block">
                    <div class=" layui-form-mid layui-word">
                        <span id="cardsfxstart"></span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">截止卡号:</label>
                <div class="layui-input-block">
                    <div class=" layui-form-mid layui-word">
                        <span id="cardsfxend"></span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">入库数量:</label>
                <div class="layui-input-block">
                    <div class=" layui-form-mid layui-word">
                        <span id="cardquat"></span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">入库人:</label>
                <div class="layui-input-block">
                    <div class=" layui-form-mid layui-word">
                        <span id="storagewname"></span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">入库日期:</label>
                <div class="layui-input-block">
                    <div class=" layui-form-mid layui-word">
                        <span id="storagedate"></span>
                    </div>
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
<script src=<%=path + "/resources/js/Back_ManageCardWarehousing.js"%>>
    //JavaScript代码区域
</script>
</html>
