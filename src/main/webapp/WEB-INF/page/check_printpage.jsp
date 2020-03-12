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
	<script type="text/javascript" src=<%=path + "resources/jquery/jquery-3.4.1.js"%>></script>
	<script type="text/javascript" src=<%=path + "resources/layui/layui.js"%>></script>
	<script type="text/javascript" src=<%=path + "resources/jquery/jQuery.print.min.js"%>></script>
	<style>
		.layui-table-cell .layui-form-checkbox[lay-skin="primary"]{
			top: 50%;
			transform: translateY(-50%);
		}
	</style>
</head>
<body>

	<%--工程路径--%>
	<form hidden>
		<input type="hidden" id="path" value=<%=path%>>
		<input type="text" id="gcid" value=${requestScope.checkInfo.gcid}>
	</form>

	<div id="page">

		<div class="layui-form-item">

			<label class="layui-form-label">导检ID：</label>
			<div  class="layui-input-inline">
				<p class="layui-form-mid">${requestScope.checkInfo.gcid}</p>
			</div>

			<label class="layui-form-label">时间：</label>
			<div  class="layui-input-inline">
				<p class="layui-form-mid">${requestScope.checkInfo.gcdate}</p>
			</div>
		</div>
		<div class="layui-form-item">

			<label class="layui-form-label">姓名：</label>
			<div  class="layui-input-inline">
				<p class="layui-form-mid">${requestScope.checkInfo.patient.pname}</p>
			</div>

			<label class="layui-form-label">卡号：</label>
			<div class="layui-input-inline">
				<p class="layui-form-mid">${requestScope.checkInfo.patient.pidentitynumber}</p>
			</div>

			<div class="layui-input-inline">
				<button type="button" class="layui-btn layui-btn-lg" id="print">打印</button>
			</div>

		</div>


		<table id="demo" lay-filter="test" class="layui-table"></table>

	</div>
	<script type="text/javascript">


		layui.use(['table','form','laydate'], function(){

			var gcid = $("#gcid").val();;
			var table = layui.table;
			var path = $("#path").val();

			table.render({
				elem: '#demo'
				,height: 400
				,limit:10
				,url: path+'check/reqCheckList' //数据接口
				// ,toolbar: '#barDemot' //开启头部工具栏，并为其绑定左侧模板
				,defaultToolbar: []
				,cols: [[ //表头
					// {type:'checkbox'}
					{field: 'pname', title: '项目名称', align:'center'}
					,{field: 'iname', title: '细项名称', align:'center'}
					,{field: 'dname', title: '科室', align:'center'}
					,{fixed: 'right', title:'医生签名', align:'center'}
				]]
				,where: { //设定异步数据接口的额外参数，任意设
					gcid:gcid
				}
			});

			$("#print").click(function () {
				$("#print").hide();


				$("#page").print();


				$("#print").show();
			});


		});



	</script>

</body>
</html>
