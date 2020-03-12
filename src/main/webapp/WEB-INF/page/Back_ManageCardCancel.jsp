<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/27
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = application.getContextPath(); %>
<html>
<head>
    <title>销卡页面</title>
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
                <label class="layui-form-label">销卡卡号</label>
                <div class="layui-input-inline">
                    <input type="text" name="cardNo"  id="cardNo" placeholder="请输入卡号" class="layui-input">
                </div>
            </div>
            <div>
                <label class="layui-form-label">销卡时间</label>
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

        </div>
        <div class="layui-form-item">
            <div>
                <label class="layui-form-label">申请人</label>
                <div class="layui-input-inline">
                    <select name="cancelWid" lay-filter="cancelWid" id="cancelWid">
                        <option value="0"selected=""></option>
                    </select>
                </div>
            </div>
            <div>
                <label class="layui-form-label">审核状态</label>
                <div class="layui-input-inline">
                    <select name="applyState" lay-filter="applyState">
                        <option value=""selected=""></option>
                        <option value="2001">待审核</option>
                        <option value="2002" >已审核</option>
                        <option value="2003" >已退回</option>
                    </select>
                </div>
            </div>
            <div>
                <div class="layui-input-inline" style="margin-left: 75px">
                    <button class="layui-btn" lay-submit lay-filter="query" id="query">查询</button>
                </div>
            </div>
        </div>
    </form>
    <table class="layui-table" id="dataTable" lay-filter="dataTable"></table>

    <script type="text/html" id="barDemo">
        {{# if(d.applyState == 2001){ }}
        <a class="layui-btn  layui-btn-xs" lay-event="detail">详情</a>
        <a class="layui-btn layui-btn-warm layui-btn-sm" lay-event="pass">批准</a>
        <a class="layui-btn  layui-btn-danger layui-btn-sm" lay-event="back">退回</a>
        {{# } }}
        {{# if(d.applyState == 2002){ }}
        <a class="layui-btn  layui-btn-sm" lay-event="detail">详情</a>
        {{# } }}
        {{# if(d.applyState == 2003){ }}
        <a class="layui-btn  layui-btn-sm" lay-event="detail">详情</a>
        {{# } }}
    </script>

</div>
<div style="display: none ;padding-right: 25px" id="popupface">
    <div id="layout1">
        <form class="layui-form" action="" id="commitform">
            <div class="layui-form-item">
                <label class="layui-form-label">申请人:</label>
                <div class="layui-input-block">
                    <div class=" layui-form-mid layui-word">
                        <span id="cancelWname"></span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">销卡时间:</label>
                <div class="layui-input-block">
                    <div class=" layui-form-mid layui-word">
                        <span id="cancelDate"></span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">销卡卡号:</label>
                <div class="layui-input-block">
                    <div class=" layui-form-mid layui-word">
                        <span id="popupCardNo"></span>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">销卡原因:</label>
                <div class="layui-input-block">
                    <div class=" layui-form-mid layui-word">
                        <span id="cancelCause"></span>
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
<script src=<%=path + "/resources/js/Back_ManageCardCancel.js"%>>
    //JavaScript代码区域
</script>
</html>
