
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<%
	String path = request.getContextPath();
	String cssPath = request.getContextPath() + "/resources/css/";
	String jsPath = request.getContextPath() + "/resources/js/";
	String layuiCssPath = request.getContextPath() +"/resources/layui/css/layui.css";
	String layuiJsPath = request.getContextPath() +"/resources/layui/layui.js";
%>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="<%=layuiCssPath%>">
	<script type="text/javascript" src=<%=jsPath+"jquery-3.4.1.js"%>></script>
	<script  type="text/javascript" src="<%=jsPath+"json2.js"%>"></script>
	<script type="text/javascript" src="<%=layuiJsPath%>"></script>
	<%--	<script type="text/javascript" src="<%=path+"/js/maintest.js"%>"></script>--%>
</head>
<body>

<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<div class="demoTable">
	<br>
	&nbsp;&nbsp;&nbsp;&nbsp;
	姓名：
	<div class="layui-inline">
		<input class="layui-input" name="name" id="name" autocomplete="off">
	</div>
	单位名称：
	<div class="layui-inline">
		<input class="layui-input" name="gcname" id="gcname" autocomplete="off">
	</div>
	手机号：
	<div class="layui-inline">
		<input type="tel" class="layui-input" name="phone" id="phone" autocomplete="off">
	</div>
	体检时间：
	<div class="layui-inline">
		<input type="date" name="date" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
	</div>

<%--	<div class="layui-inline">--%>
<%--		上传时间：--%>
<%--		<input class="layui-input" type="date" name="beginDate" id="beginDate" autocomplete="off">--%>
<%--		至--%>
<%--		<input class="layui-input" type="date" name="endDate" id="endDate" autocomplete="off">--%>
<%--	</div>--%>
	<input type="hidden" value="<%=path%>" id="path">
	<button class="layui-btn" id="query" data-type="reload">搜索</button>
</div>

<table class="layui-hide" id="LAY_table_user" lay-filter="test"></table>

<script>
	layui.use(['table','jquery'], function(){
		var table = layui.table;
		var $2 = layui.jquery;
		//方法级渲染
		table.render({
			elem: '#LAY_table_user'
			,url: $2('#path').val()+'/yePatientTable.do'
			,cols: [[ //表头
				{field: 'pname', title: '姓名', width:10+'%'}
				// templet:function (d) {
				// 	return d.id ==1?"男":"女";}
				,{field: 'pphone', title: '电话', width:15+'%'}
				,{field: 'gcaccount', title: '单位账号', width:13+'%'}
				,{field: 'gcname', title: '单位名称', width:13+'%'}
				,{field: 'pgender', title: '性别', width:5+'%' ,templet:function (d) {return d.pgender ==1?"男":"女";}}
				,{field: 'pidentitynumber', title: '身份证号', width:20+'%'}
				,{field: 'packname', title: '最新购买套餐', width:12+'%'}
				,{field: 'sign_date', title: '最新签到时间', width:12+'%'}
				// ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
			]]
			,id: 'testReload'
			,page: true
			,limit:5
			,height: 310
		});

		var $ = layui.$, active = {
			reload: function(){
				//执行重载
				table.reload('testReload', {
						page: {
							curr: 1 //重新从第 1 页开始
						}
						,where: {
							name:$('#name').val(),
							date:$('#date').val(),
							phone:$('#phone').val(),
							gcname:$("#gcname").val()
						}
					},
					'data');
			}
		};

		$('#query').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});

	//将表单转为js对象数据
	function serializeObject($, array){
		var obj=new Object();
		$.each(array, function(index,param){
			if(!(param.name in obj)){
				obj[param.name]=param.value;
			}
		});
		return obj;
	}
</script>
</body>
</html>
