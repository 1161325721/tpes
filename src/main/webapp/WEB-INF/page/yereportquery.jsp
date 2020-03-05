
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
		<input class="layui-input" name="pname" id="pname" autocomplete="off">
	</div>
	套餐名称：
	<div class="layui-inline">
		<input class="layui-input" name="packname" id="packname" autocomplete="off">
	</div>
	导检号：
	<div class="layui-inline">
		<input type="text" class="layui-input" name="goid" id="goid" autocomplete="off">
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
			,url: $2('#path').val()+'/yeReportTable.do'
			,cols: [[ //表头
				{field: 'pname', title: '姓名', width:15+'%'}
				// templet:function (d) {
				// 	return d.id ==1?"男":"女";}
				,{field: 'packname', title: '套餐', width:15+'%'}
				,{field: 'goid', title: '导检单号', width:13+'%'}
				,{field: 'crsuggest', title: '报告建议', width:13+'%'}
				,{field: 'crsummary', title: '报告总结', width:20+'%' }
				,{field: 'crlifeguide', title: '生活指导', width:20+'%' }
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
							pname:$('#pname').val(),
							packname:$('#packname').val(),
							goid:$('#goid').val()
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
