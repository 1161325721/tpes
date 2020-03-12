<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/28
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = application.getContextPath(); %>
<html>
<head>
    <title>前台卡管理</title>
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
                <label class="layui-form-label">卡号</label>
                <div class="layui-input-inline">
                    <input type="text" name="cardNo"  id="cardNo" placeholder="请输入卡号" class="layui-input">
                </div>
            </div>
            <div>
                <label class="layui-form-label">申请时间</label>
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
                <label class="layui-form-label">领用人</label>
                <div class="layui-input-inline">
                    <select name="applyWid" lay-filter="applyWid" id="applyWid">
                        <option value="0"selected=""></option>
                    </select>
                </div>
            </div>
            <div>
                <label class="layui-form-label">卡状态</label>
                <div class="layui-input-inline">
                    <select name="cardState" lay-filter="cardState" id="cardState">
                        <option value=""selected=""></option>
                        <option value="1000">销卡锁定</option>
                        <option value="1001">已入库</option>
                        <option value="1002" >已领用</option>
                        <option value="1003" >已使用</option>
                        <option value="1004" >替换弃用</option>
                        <option value="1005" >销卡弃用</option>
                    </select>
                </div>
            </div>
            <div>
                <div class="layui-input-inline" style="margin-left: 65px">
                    <button class="layui-btn" lay-submit lay-filter="query" id="query">查询</button>
                </div>
            </div>
        </div>
    </form>
    <table class="layui-table" id="dataTable" lay-filter="dataTable"></table>

    <script type="text/html" id="barDemo">
        <a class="layui-btn  layui-btn-sm" lay-event="detail">详情</a>
    </script>
    <div style="display: none ;padding-right: 25px" id="popupface">
        <div id="layout1">
            <table class="layui-table">
                <tr>
                    <th>
                        卡号:
                    </th>
                    <th colspan="3">
                       <span id="showCardNo" class="show"></span>
                    </th>
                </tr>
                <tr>
                    <th>
                        就诊人:
                    </th>
                    <th>
                        <span id="showPatientName" class="show"></span>
                    </th>
                    <th>
                        所属单位:
                    </th>
                    <th>
                        <span id="showCompanyName" class="show"></span>

                    </th>
                </tr>
                <tr>
                    <th>
                        领用人:
                    </th>
                    <th>
                        <span id="showApplyWname" class="show"></span>
                    </th>
                    <th>
                        领用时间:
                    </th>
                    <th>
                        <span id="showGetDate" class="show"></span>
                    </th>
                </tr>
                <tr>
                    <th>
                        发卡人:
                    </th>
                    <th>
                        <span id="showIssueWname" class="show"></span>
                    </th>
                    <th>
                        发卡时间:
                    </th>
                    <th>
                        <span id="showIssueDate" class="show"></span>
                    </th>
                </tr>
            </table>
        </div>
    </div>

</div>
<div style="display: none">
    <input type="hidden" id="path" value=<%=path%>>
</div>
</body>
<script src=<%=path + "/resources/layui/layui.js"%>></script>
<script src=<%=path + "/resources/js/Back_ManageCardAllInfo.js"%>>
    //JavaScript代码区域
</script>
</html>
