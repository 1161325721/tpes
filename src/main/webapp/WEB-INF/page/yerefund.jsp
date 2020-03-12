<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String cssPath = request.getContextPath() + "/resources/css/";
	String jsPath = request.getContextPath() + "/resources/js/";
	String layuiCssPath = request.getContextPath() +"/resources/layui/css/layui.css";
	String layuiJsPath = request.getContextPath() +"/resources/layui/layui.js";
%>
<html>
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
	if(request.getAttribute("msg")!=null){
		String result = (String) request.getAttribute("msg");
		System.out.println("result:"+result);
		out.write("<script> alert('"+result+"')</script>");
	}
%>
<div id="divAll">
	<form class="layui-form" action="<%=path+"/yerefund.do"%>" method="post">
		<input type="hidden" id="path" value="<%=path%>">

		<div class="layui-form-item">
			<label class="layui-form-label">订单号</label>
			<div class="layui-input-inline">
				<input type="input" name="order" id="order" required  lay-verify="order" placeholder="请输入订单号" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">单位账号</label>
			<div class="layui-input-inline">
				<input type="input" name="gcaccount" id="gcaccount" required  lay-verify="gcaccount" placeholder="请输入单位账号" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">单位密码</label>
			<div class="layui-input-inline">
				<input type="input" name="gcpass" id="gcpass" required  lay-verify="gcpass" placeholder="请输入单位密码" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
				<%--				表单提交登录按钮--%>
				<button class="layui-btn" lay-submit lay-filter="formDemo">申请退款</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				<%--				<button class="layui-btn" lay-filter="register" id="register">注册</button>--%>
			</div>
		</div>
	</form>

</div>

<script>
	layui.use('form', function(){
		var form = layui.form;
		form.verify({
			gcaccount: [
				/\d{6,}/
				,'账号必须至少为6位的数字'
			]
			,order: [
				/\d{1,}/
				,'订单号必须至少为1位的数字'
			]
			,gcpass:function (value) {
				if(value.length<6){
					return '提示：密码大于六位'
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
