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
<body>
<%
	if(session.getAttribute("singlebill")!=null){
		String result = (String) session.getAttribute("singlebill");
		System.out.println("result:"+result);
		out.write("<script> alert('"+result+"')</script>");
	}
%>
<div id="divAll">
	<span id="title" class="layui-title"><strong>个人开单</strong></span>
	<div>
		<br>
	</div>
	<input type="hidden" id="path" value="<%=path%>">
	<form class="layui-form" action="<%=path+"/yesinglebillform.do"%>" method="post">

		<div class="layui-form-item">
			<label class="layui-form-label">单位账号</label>
			<div class="layui-input-inline">
				<input type="text" name="waccount" required   lay-verify="account" placeholder="请输入标题" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">单位密码</label>
			<div class="layui-input-inline">
				<input type="password" name="wpass" required lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
			</div>
			<%--			<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">个人姓名</label>
			<div class="layui-input-inline">
				<input type="text" name="sname" lay-verify="sname" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">个人手机号</label>
			<div class="layui-input-inline">
				<input type="text" name="sphone" lay-verify="phone" autocomplete="off" class="layui-input">
			</div>
		</div>


		<div class="layui-form-item">
			<label class="layui-form-label">身份证号</label>
			<div class="layui-input-inline">
				<input type="text" name="sid" lay-verify="sid" autocomplete="off" class="layui-input">
			</div>
		</div>

<%--		<div class="layui-form-item">--%>
<%--			<label class="layui-form-label">套餐</label>--%>
<%--			<div class="layui-input-inline">--%>
<%--				<input type="tel" name="spackage" required  lay-verify="required|phone" placeholder="请输入标题" autocomplete="off" class="layui-input">--%>
<%--			</div>--%>
<%--		</div>--%>

		<div class="layui-form-item">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-block">
				<input type="radio" name="sex" value="1" title="男" checked>
				<input type="radio" name="sex" value="0" title="女" >
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">套餐</label>
			<div class="layui-input-inline">
				<select name="spackage" lay-verify="package">
					<option value="noHave">请选择一个套餐</option>
					<c:if test="${sessionScope.packageList!=null}">
						<c:forEach items="${sessionScope.packageList}" begin="0" var="i">
							<option value="${i.packname}">${i.packname}</option>
						</c:forEach>
					</c:if>
				</select>
			</div>
		</div>

<%--			<option value="010">${}</option>--%>
<%--			<option value="021">上海</option>--%>
<%--			<option value="0571">杭州</option>--%>


		<div class="layui-form-item">
			<div class="layui-input-block">
				<%--				表单提交登录按钮--%>
				<button class="layui-btn" lay-submit lay-filter="formDemo" id="singlebill">开单</button>
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
			account: [
				/\d{6,}/
				,'账号必须至少为6位的数字'
			]
			,sname: function (value) {
				if(value.length<2){
					return '姓名至少为两个字符';
				}
			}
			,package: function (value) {
				if(value==='noHave'){
					return '请选择套餐';
				}
			}
			,pass: [
				/^[\S]{6,12}$/
				,'密码必须6到12位，且不能出现空格'
			]
			,phone: [
				/^[\S]{11}$/
				,'电话号码为十一位数'
			]
			,sid: [
				/^[\S]{18}$/
				,'请输入正确的身份证号'
			]
		});
		//监听提交
		form.on('submit(formDemo)', function(data){
			layer.msg(JSON.stringify(data.field));
			return true;
		});
	});



	// layui.use(['form', 'layedit', 'laydate'], function(){
	// 	var form = layui.form
	// 		,layer = layui.layer
	// 		,layedit = layui.layedit
	// 		,laydate = layui.laydate;
	//
	// 	//日期
	// 	laydate.render({
	// 		elem: '#date'
	// 	});
	// 	laydate.render({
	// 		elem: '#date1'
	// 	});
	//
	// 	//创建一个编辑器
	// 	var editIndex = layedit.build('LAY_demo_editor');
	//
	// 	//自定义验证规则
	// 	form.verify({
	// 		title: function(value){
	// 			if(value.length < 5){
	// 				return '标题至少得5个字符啊';
	// 			}
	// 		}
	// 		,pass: [
	// 			/^[\S]{6,12}$/
	// 			,'密码必须6到12位，且不能出现空格'
	// 		]
	// 		,content: function(value){
	// 			layedit.sync(editIndex);
	// 		}
	// 	});
	//
	// 	//监听指定开关
	// 	form.on('switch(switchTest)', function(data){
	// 		layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
	// 			offset: '6px'
	// 		});
	// 		layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
	// 	});
	//
	// 	//监听提交
	// 	form.on('submit(demo1)', function(data){
	// 		layer.alert(JSON.stringify(data.field), {
	// 			title: '最终的提交信息'
	// 		})
	// 		return false;
	// 	});
	//
	// 	//表单赋值
	// 	layui.$('#LAY-component-form-setval').on('click', function(){
	// 		form.val('example', {
	// 			"username": "贤心" // "name": "value"
	// 			,"password": "123456"
	// 			,"interest": 1
	// 			,"like[write]": true //复选框选中状态
	// 			,"close": true //开关状态
	// 			,"sex": "女"
	// 			,"desc": "我爱 layui"
	// 		});
	// 	});
	//
	// 	//表单取值
	// 	layui.$('#LAY-component-form-getval').on('click', function(){
	// 		var data = form.val('example');
	// 		alert(JSON.stringify(data));
	// 	});
	//
	// });
</script>

</body>
</html>
