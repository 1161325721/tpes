
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
	<title>Title</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=layuiCssPath%>">
	<link rel="stylesheet" type="text/css" href="<%=cssPath+"yelogin.css"%>">
	<script  type="text/javascript" src="<%=jsPath+"json2.js"%>"></script>
	<script type="text/javascript" src="<%=layuiJsPath%>"></script>
</head>
<body>
<%
	if(session.getAttribute("loginResult")!=null){
		String result = (String) session.getAttribute("loginResult");
		System.out.println("result:"+result);
		out.write("<script> alert('账号或密码错误')</script>");
	}
%>
<div id="divAll">
	<span id="title" class="layui-title"><strong>后台登录</strong></span>
	<br>
	<input type="hidden" id="path" value="<%=path%>">
	<form class="layui-form" action="<%=path+"/yelogin.do"%>" method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">输入框</label>
			<div class="layui-input-inline">
				<input type="text" name="waccount" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密码框</label>
			<div class="layui-input-inline">
				<input type="password" name="wpass" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
			</div>
			<%--			<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<%--				表单提交登录按钮--%>
				<button class="layui-btn" lay-submit lay-filter="formDemo">登录</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
<%--				<button class="layui-btn" lay-filter="register" id="register">注册</button>--%>
			</div>
		</div>
	</form>
</div>

<script>
	//Demo
	layui.use('form', function(){
		var form = layui.form;

		//监听提交
		form.on('submit(formDemo)', function(data){
			layer.msg(JSON.stringify(data.field));
			return true;
		});
	});

	// layui.use(['jquery','layer','form'],function (index) {
	// 	var $ = layui.jquery;
	// 	var layer = layui.layer;
	// 	$('#register').click(function () {
	// 		layer.open({
	// 			// offset: 'auto',
	// 			type: 2,
	// 			title:'注册',
	// 			area: ['600px', '400px'],
	// 			btn: ['确认注册', '取消'],
	// 			btn1: function(index, layero){
	// 				//layer.getChildFrame("form", index)获取iframe的表单
	// 				//serializeArray jquery方法，将表单对象序列化为数组
	// 				var formData = serializeObject($, layer.getChildFrame("form", index).serializeArray());
	// 				$.ajax({
	// 					url:$('#path').val()+'/test/register.do',
	// 					type:'post',
	// 					data: formData,
	// 					dataType:"text",
	// 					success:function(msg){
	// 						// var re = JSON.parse(msg);
	// 						// alert(re.result);
	// 						if(msg=="success"){
	// 							alert("注册成功");
	// 						} else {
	// 							alert("注册失败");
	// 						}
	//
	// 						layer.close(index);
	// 					},
	// 					error:function (msg) {
	// 						alert("错误");
	// 						layer.close(index);
	// 					}
	// 				});
	// 			},
	// 			content: [$('#path').val()+'/jsp/form.jsp','no'] //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
	// 			,success: function(layero, index){
	// 				console.log(layero, index);
	// 			}
	// 		});
	// 	})
	// });


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
