<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/18
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<%
	String path = request.getContextPath();
	String cssPath = request.getContextPath() + "/resources/css/";
	String jsPath = request.getContextPath() + "/resources/js/";
	String layuiCssPath = request.getContextPath() +"/resources/layui/css/layui.css";
	String layuiJsPath = request.getContextPath() +"/resources/layui/layui.js";
%>
<head>
	<title>注册页</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=layuiCssPath%>">
	<link rel="stylesheet" type="text/css" href="<%=cssPath+"yelogin.css"%>">
	<script  type="text/javascript" src="<%=jsPath+"json2.js"%>"></script>
	<script type="text/javascript" src="<%=layuiJsPath%>"></script>
</head>
<style>
	#divAllLone{
		margin-left: 11%;
		margin-top: 5%;
	}
	#title{
		margin-left: 32%;
	}
</style>
<body>
<%
	if(session.getAttribute("singlebill")!=null){
		String result = (String) session.getAttribute("singlebill");
		System.out.println("result:"+result);
		out.write("<script> alert('"+result+"')</script>");
	}
%>
<div id="divAllLone">
	<span id="title" class="layui-title"><strong>个人开单</strong></span>
	<div>
		<br>
	</div>
	<input type="hidden" id="path" value="<%=path%>">
	<form class="layui-form" action="<%=path+"/yesinglebillform.do"%>" method="post">

		<div class="layui-form-item">
			<label class="layui-form-label">套餐:</label>
			<div class="layui-input-inline">
				<select name="days" lay-verify="days">
					<option value="0">请选择天数</option>
					<option value="1">1天</option>
					<option value="2">2天</option>
					<option value="3">3天</option>
					<option value="4">4天</option>
					<option value="5">5天</option>
				</select>
			</div>
		</div>

	</form>
</div>


<script>

	layui.use('form', function(){
		var form = layui.form;
		form.verify({
			days:function(value){
				if(value===0){
					return '请选择天数';
				}
			}
		});
		//监听提交
		form.on('submit(formDemo)', function(data){
			layer.msg(JSON.stringify(data.field));
			return true;
		});
	});
</script>

</body>
</html>
