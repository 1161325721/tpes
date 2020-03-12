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
	<title>导检单</title>
	<link rel="stylesheet" href=<%=path + "resources/layui/css/layui.css"%> >
	<script type="text/javascript" src=<%=path + "resources/layui/layui.js"%>></script>
	<script type="text/javascript" src=<%=path + "resources/jquery/jquery-3.4.1.js"%>></script>
	<script type="text/javascript" src=<%=path + "resources/js/FileSaver.js"%>></script>
	<script type="text/javascript" src=<%=path + "resources/js/jquery.wordexport.js"%>></script>
	<script type="text/javascript" src=<%=path + "resources/js/check_guide.js"%>></script>
	<script type="text/javascript" src=<%=path + "resources/js/html2canvas.js"%>></script>
	<script type="text/javascript" src=<%=path + "resources/jquery/jQuery.print.min.js"%>></script>

	<style>
		.layui-table-cell .layui-form-checkbox[lay-skin="primary"]{
			top: 50%;
			transform: translateY(-50%);
		}
	</style>
</head>
<body>

<%--
--%>
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
				<label class="layui-form-label">卡号：</label>
				<div class="layui-input-inline">
					<input type="text" name="pcardnumber" lay-verify="required" placeholder="请输入卡号" autocomplete="off" class="layui-input">
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

	<%--列表--%>
	<table id="demo" lay-filter="test" class="layui-table"></table>



	<%--操作栏   --%>
	<div style="display: none" id="tableBar">
		<div class="layui-btn-group">
			<a class="layui-btn layui-btn-xs" lay-event="look">打印导检单</a>
			<a class="layui-btn layui-btn-xs" lay-event="print">打印条码</a>
		</div>
	</div>



	<%--添加与编辑界面--%>
	<div style="display:none;" id="editDiv">

		<div id="content" class="content">
			<form action="" method="post" class="layui-form" id="editForm" lay-filter="editForm">
<%--				<button type="button" class="layui-btn layui-btn-sm" id="print">打印</button>--%>

				<div class="layui-form-item" id="idDiv">
					<label class="layui-form-label">导检ID：</label>
					<div class="layui-input-inline">
						<input type="text" name="gcid" class="layui-input" style="border-style: none" disabled>
					</div>
					<label class="layui-form-label">时间：</label>
					<div class="layui-input-inline">
						<input type="text" name="gcdate" class="layui-input" style="border-style: none" disabled>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">姓名：</label>
					<div class="layui-input-inline">
						<input type="text" name="pname" class="layui-input" style="border-style: none" disabled>
					</div>
					<label class="layui-form-label">性别：</label>
					<div class="layui-input-inline">
						<input type="text" name="pgender" class="layui-input" style="border-style: none" disabled>
					</div>
				</div>
			</form>
			<table id="demo2" lay-filter="test2" class="layui-table"></table>
		</div>



	</div>

	<div style="display: none" id="code">
		<div class="layui-form-item">
			<label class="layui-form-label">条码预览：</label>
			<div class="layui-input-inline">
				<img id="projectImage" style="width: 200px;height: auto" src=<%=path + "resources/images//test.png"%>>
			</div>
		</div>
		<div class="layui-input-inline">
			<button type="button" class="layui-btn layui-btn-lg" id="print">打印</button>
		</div>
	</div>


	<%--操作栏   --%>
	<div style="display: none" id="tableBar2">
		<div class="layui-btn-group">
			<a class="layui-btn layui-btn-xs" lay-event="print">打印条码</a>
		</div>
	</div>

</body>
</html>
