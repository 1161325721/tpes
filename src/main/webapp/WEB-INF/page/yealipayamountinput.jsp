<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/27
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>

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
	<script  type="text/javascript" src="<%=jsPath+"jquery-3.4.1.js"%>"></script>
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
	<span id="title" class="layui-title"><strong>用户充值</strong></span>
	<input type="hidden" id="path" value="<%=path%>">
		<div >
			<label class="layui-form-label">单位账号</label>
			<div class="layui-input-inline">
				<input type="input" name="gcaccount" id="gcaccount" onblur="queryForGcAccount(this)" required  lay-verify="gcaccount" placeholder="请输入单位账号" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div>
			<br>
			<br>
		</div>
		<div >
			<label class="layui-form-label">充值金额</label>
			<div class="layui-input-inline">
				<input type="text" name="amount" onblur="checkAmount(this)" id="amount" required  lay-verify="amount" placeholder="请输入金额" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div>
			<br>
		</div>
		<div >
			<div class="layui-input-block">
				<%--				表单提交登录按钮--%>
				<button class="layui-btn"  lay-filter="test3" id="test3">充值</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				<%--				<button class="layui-btn" lay-filter="register" id="register">注册</button>--%>
			</div>
		</div>

</div>

<script>
	var tempForAccount = 1;
	var tempForAmount = 1;
	function checkAmount(node) {
		var amount = node.value;
		if(amount<=100){
			layer.msg('充值金额至少为100');
			tempForAmount = 1;
		} else{
			// layer.msg('金额合法');
			tempForAmount =0;
		}
	}

	function queryForGcAccount(node){
		var gcaccount1 = node.value;
		var reg = /^[a-zA-Z0-9]{6,12}$/;
		var isNum = reg.test(gcaccount1);
		var path = document.getElementById("path").value;

		var isLong = gcaccount1.length>5;
		if(gcaccount1.length>5&&isNum){
			$.ajax({
				url:path+'/checkForGcaccount.do',
				type:'post',
				dataType:"text",
				data:{
					gcaccount:gcaccount1
				},
				success:function(msg){
					var object = JSON.parse(msg);
					// alert(object.code);
					// alert(object.code===0);
					if(object.code===0){
						tempForAccount = 0;
						// layer.msg('账号存在');
					} else{
						tempForAccount = 1;
						layer.msg('账号不存在');
					}
				},error:function (err) {

				}
			});

		} else{
			layer.msg('账号至少为六位存数字');
			tempForAccount = 1;
		}
	}



	layui.use(['layer','jquery'],function () {
		var $ =layui.jquery;
		$('#test3').on('click',function () {
			// alert("11234");
			// alert($('#amount').val());
			if(tempForAccount===0&&tempForAmount===0){
				$.ajax({
					url:$('#path').val()+'/yealipay.do',
					type:'post',
					dataType:"text",
					data:{
						gcaccount:$('#gcaccount').val(),
						amount:$('#amount').val()
					},
					success:function(msg){
						// alert(msg);
						if(msg.length>0){
							var divForm = document.getElementById("divAll");
							if (divForm.length) {
								document.body.removeChild(divForm[0])
							}
							//改变js版本到1.8.5
							const div=document.createElement('divform');
							div.innerHTML=msg; // data就是接口返回的form 表单字符串
							document.body.appendChild(div);
							document.forms[0].setAttribute('target', '_blank'); // 新开窗口跳转
							document.forms[0].submit();
						}

						// innerHTML.write(msg);
					},error:function (err) {

					}
				});
			} else if(tempForAccount===1){
				layer.msg('请重新输入账号');
			} else {
				layer.msg('请重新输入金额')
			}
		});
	})
	//Demo
	// layui.use('form', function(){
	// 	var form = layui.form;
	// 	form.verify({
	// 		amount: [
	// 			/^[\S]{2,12}$/
	// 			,'金额必须2到12位，且不能出现空格'
	// 		]
	// 	});
	// 	//监听提交
	// 	form.on('submit(formDemo)', function(data){
	// 		layer.msg(JSON.stringify(data.field));
	// 		return true;
	// 	});
	// });
	//
	// //将表单转为js对象数据
	// function serializeObject($, array){
	// 	var obj=new Object();
	// 	$.each(array, function(index,param){
	// 		if(!(param.name in obj)){
	// 			obj[param.name]=param.value;
	// 		}
	// 	});
	// 	return obj;
	// }
</script>
</body>
</html>

