<%--
  Created by IntelliJ IDEA.
  User: success
  Date: 2020/2/17
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<%
	String path = application.getContextPath() + "/";
	//	String cssPath = request.getContextPath() + "/css/";
	//	String jsPath = request.getContextPath() + "/js/";
	//	String layuiCssPath = request.getContextPath() +"/layui/css/layui.css";
	//	String layuiJsPath = request.getContextPath() +"/layui/layui.js";
%>
<head>
	<meta charset="utf-8">
	<script src=<%=path+"resources/jquery/jquery-3.4.1.js"%>></script>
	<script src=<%=path+"resources/layui/layui.js"%>></script>
<%--	<script src=<%=path+"resources/js/login.js"%>></script>--%>
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
</head>
<body>
<form class="layui-form" action="<%=path+"/yelogin.do"%>" method="post" >
	<div class="container">
		<button class="close" title="关闭">X</button>
		<div class="layui-form-mid layui-word-aux">
			<%--			添加图片--%>
			<%--			<img id="logoid" src="06.png" height="35">--%>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-block">
				<input type="text" id="login_account" name="account" required  lay-verify="account" placeholder="请输入用户名" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">密 &nbsp;&nbsp;码</label>
			<div class="layui-input-inline">
				<input id="login_password" type="password" name="password" required lay-verify="password" placeholder="请输入密码" autocomplete="off" class="layui-input">
			</div>
			<!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">验证码</label>
			<div class="layui-input-inline">
				<input type="text" id="login_code" required lay-verify="checkCode" placeholder="验证码" autocomplete="off"
				       class="layui-input" height="50px" style="width: 45%; float: left;">
				<canvas id="canvas" width="150px" height="50px" onclick="drawPic()" style=" width:55% ;float: left;"></canvas>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button  class="layui-btn" lay-submit lay-filter="formDemo">登陆</button>
			</div>
		</div>
		<%--		<a href="" class="font-set">忘记密码?</a>  <a href="" class="font-set">立即注册</a>--%>
	</div>
</form>

<script>

	var code = "";
	window.onload = function(){
		// 页面出现的时候就加载
		$(drawPic());
		// alert("code:"+code);
	};
	//Demo
	layui.use('form', function(){
		var form = layui.form;
		form.verify({
			account: function(value){
				if(value.length < 6){
					return '账号至少为6个字符';
				}
			},
			password: function(value){
				if(value.length < 6){
					return '密码至少为6个字符';
				}
			}
		});
		//监听提交
		form.on('submit(formDemo)', function(data){
			layer.msg(JSON.stringify(data.field));
			if($('#login_code').val().toLowerCase()===code.toLowerCase()){
				return true;
			} else{
				// alert('input:'+$('#login_code').val());
				// alert('code:'+code);
				alert("验证码错误");
				$(drawPic());
				return false;
			}

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


	// 验证码
	//生成随机数
	function randomNum(min,max){
		return Math.floor(Math.random()*(max-min)+min);
	}
	//生成随机颜色RGB分量
	function randomColor(min,max){
		var _r = randomNum(min,max);
		var _g = randomNum(min,max);
		var _b = randomNum(min,max);
		return "rgb("+_r+","+_g+","+_b+")";
	}
	//先阻止画布默认点击发生的行为再执行drawPic()方法
	document.getElementById("canvas").onclick = function(e){
		e.preventDefault();
		drawPic();
	};



	function drawPic(){
		//获取到元素canvas
		var $canvas = document.getElementById("canvas");
		var code_str = "QWERTYPADFGHJLBNMqwertyipadfghjbnm123456789";//设置随机数库
		var codeStr = "";//最后得到的字符串
		var rd_num = 4;//4个随机数字
		var code_width = $canvas.width;
		var code_height = $canvas.height;
		var ctx = $canvas.getContext("2d");//获取 context 对象
		ctx.textBaseline = "bottom";//文字上下对齐方式--底部对齐
		ctx.fillStyle = randomColor(180,240);//填充画布颜色
		ctx.fillRect(0,0,code_width,code_height);//填充矩形--画画
		for(var i=0; i<rd_num; i++){
			var x = (code_width-10)/rd_num*i+10;
			var y = randomNum(code_height/2,code_height);
			var deg = randomNum(-45,45);
			var txt = code_str[randomNum(0,code_str.length)];
			codeStr += txt;//获取一个随机数
			ctx.fillStyle = randomColor(10,100);//填充随机颜色
			ctx.font = randomNum(16,35)+"px SimHei";//设置随机数大小，字体为SimHei
			ctx.translate(x,y);//将当前xy坐标作为原始坐标
			ctx.rotate(deg*Math.PI/180);//旋转随机角度
			ctx.fillText(txt, 0,0);//绘制填色的文本
			ctx.rotate(-deg*Math.PI/180);
			ctx.translate(-x,-y);
		}
		for(var i=0; i<rd_num; i++){
			//定义笔触颜色
			ctx.strokeStyle = randomColor(90,180);
			ctx.beginPath();
			//随机划线--4条路径
			ctx.moveTo(randomNum(0,code_width), randomNum(0,code_height));
			ctx.lineTo(randomNum(0,code_width), randomNum(0,code_height));
			ctx.stroke();
		}
		for(var i=0; i<rd_num*10; i++){
			ctx.fillStyle = randomColor(0,255);
			ctx.beginPath();
			//随机画原，填充颜色
			ctx.arc(randomNum(0,code_width),randomNum(0,code_height), 1, 0, 2*Math.PI);
			ctx.fill();
		}

		code = codeStr;
		return code;//返回随机数字符串
	}
</script>
</body>
</html>
