<%--
  Created by IntelliJ IDEA.
  User: 127
  Date: 2020/2/16
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<title>团检单位设置</title>
	<link rel="stylesheet" href=<%=path + "resources/layui/css/layui.css"%> >
	<script type="text/javascript" src=<%=path + "resources/jquery/jquery-3.4.1.js"%>></script>
	<script type="text/javascript" src=<%=path + "resources/layui/layui.js"%>></script>
	<script type="text/javascript" src=<%=path + "resources/js/sysmgr_groupmgr.js"%>></script>
</head>
<body>

	<%--工程路径--%>
	<form hidden>
		<input type="hidden" id="path" value=<%=path%>>
	</form>

	<%--查询条件--%>
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;" >
		<legend>搜索条件</legend>
	</fieldset>

	<form action="" class="layui-form" method="post" id="condition" lay-filter="condition">

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">客户名称：</label>
				<div class="layui-input-inline">
					<input type="text" name="gcname" lay-verify="required" placeholder="请输入客户名称" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">账号状态</label>
			<div class="layui-input-inline">
				<select name="gcstate" lay-filter="aihao">
					<option value=""></option>
					<option value="1">正常</option>
					<option value="2">禁用</option>
					<option value="3">删除</option>
				</select>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">注册日期：</label>
				<div class="layui-input-inline">
					<input type="text" name="startdate" id="startdate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-input-inline">
					<label class="layui-form-label">-</label>
				</div>
				<div class="layui-input-inline">
					<input type="text" name="enddate" id="enddate" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
				</div>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button type="button" class="layui-btn layui-btn-sm" lay-filter="demo1" id="search">搜索</button>
				<button type="reset" class="layui-btn layui-btn-sm">重置</button>
			</div>
		</div>
	</form>

	<%--单位列表--%>
	<table id="demo" lay-filter="test" class="layui-table"></table>

	<%--操作栏   --%>
	<div style="display: none" id="tableBar">
		<div class="layui-btn-group">
			<a class="layui-btn layui-btn-xs" lay-event="ok">正常</a>
			<a class="layui-btn layui-btn-xs" lay-event="ban">禁用</a>
			<a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
		</div>
	</div>
</body>
</html>
