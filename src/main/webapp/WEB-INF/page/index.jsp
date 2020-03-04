
<%--
  Created by IntelliJ IDEA.
  User: success
  Date: 2020/2/17
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<%
	String path = application.getContextPath() + "/";
	//	String cssPath = request.getContextPath() + "/css/";
	//	String jsPath = request.getContextPath() + "/js/";
	//	String layuiCssPath = request.getContextPath() +"/layui/css/layui.css";
	//	String layuiJsPath = request.getContextPath() +"/layui/layui.js";
%>
	<meta charset="utf-8">
	<script src=<%=path+"resources/jquery/jquery-3.4.1.js"%>></script>
	<script src=<%=path+"resources/js/login.js"%>></script>
	<link rel="stylesheet" href=<%=path+"resources/layui/css/layui.css"%> >
	<style type="text/css">
		.container{
			width: 420px;
			height: 320px;
			min-height: 320px;
			max-height: 320px;
			position: absolute;
			top: 0;
			left: 0;
			bottom: 0;
			right: 0;
			margin: auto;
			padding: 20px;
			z-index: 130;
			border-radius: 8px;
			background-color: #fff;
			box-shadow: 0 3px 18px rgba(125, 125, 125, .5);
			font-size: 16px;
		}
		.close{
			background-color: white;
			border: none;
			font-size: 18px;
			margin-left: 410px;
			margin-top: -10px;
		}

		.layui-input{
			border-radius: 5px;
			width: 300px;
			height: 40px;
			font-size: 15px;
		}
		.layui-form-item{
			margin-left: -20px;
		}
		#logoid{
			margin-top: -16px;
			padding-left:150px;
			padding-bottom: 15px;
		}
		.layui-btn{
			margin-left: -50px;
			border-radius: 5px;
			width: 350px;
			height: 40px;
			font-size: 15px;
		}
		.verity{
			width: 120px;
		}
		.font-set{
			font-size: 13px;
			text-decoration: none;
			margin-left: 120px;
		}
		a:hover{
			text-decoration: underline;
		}

	</style>
<body>
<form class="layui-form" >
	<div class="container">
		<button class="close" title="关闭">X</button>
		<div class="layui-form-mid layui-word-aux">
			<%--			添加图片--%>
			<%--			<img id="logoid" src="06.png" height="35">--%>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block">
				<input type="text" id="login_account" name="login_account" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密 &nbsp;&nbsp;码</label>
			<div class="layui-input-inline">
				<input id="login_password" type="password" name="login_password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
			</div>
			<!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">验证码</label>
			<div class="layui-input-inline">
				<input type="text" id="login_code" required lay-verify="required" placeholder="验证码" autocomplete="off"
				       class="layui-input" height="50px" style="width: 45%; float: left;">
				<canvas id="canvas" width="150px" height="50px" onclick="drawPic()" style=" width:55% ;float: left;"></canvas>
			</div>
			<!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
		</div>
		<!-- 			  <div class="layui-form-item">
							<label class="layui-form-label">记住密码</label>
							<div class="layui-input-block">
							  <input type="checkbox" name="close" lay-skin="switch" lay-text="ON|OFF">
							</div>
					  </div> -->

		<div class="layui-form-item">
			<div class="layui-input-block">
				<button  class="layui-btn" lay-submit="" lay-filter="login">登陆</button>
			</div>
		</div>
		<%--		<a href="" class="font-set">忘记密码?</a>  <a href="" class="font-set">立即注册</a>--%>
	</div>
</form>
</body>l
