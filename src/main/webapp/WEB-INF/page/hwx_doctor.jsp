<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<%
	String path = application.getContextPath() + "/";
	//	String cssPath = request.getContextPath() + "/css/";
	//	String jsPath = request.getContextPath() + "/js/";
	//	String layuiCssPath = request.getContextPath() +"/layui/css/layui.css";
	//	String layuiJsPath = request.getContextPath() +"/layui/layui.js";
%>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>${sessionScope.rname}工作窗口</title>
	<title>医生工作窗口</title>
	<link rel="stylesheet" href=<%=path+"resources/layui/css/layui.css"%> >
	<script src=<%=path+"resources/layui/layui.js"%>></script>
	<script src=<%=path+"resources/json2.js"%>></script>
	<script src=<%=path+"resources/jquery/jquery-3.4.1.js"%>></script>

</head>
<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">${sessionScope.rname}工作窗口</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-left">
<%--			<li class="layui-nav-item"><a title="<%=path+"/test/to/fileupload.do"%>" onclick="fileUpload(this)">文件上传(form)</a></li>--%>
<%--			<li class="layui-nav-item"><a title="<%=path+"/test/to/fileupload2.do"%>" onclick="fileUpload(this)">文件上传（ajax）</a></li>--%>
<%--			<li class="layui-nav-item"><a href="<%=path+"/test/filedownload.do?filename=DB.db&filetype=db"%>">文件下载(测试)</a></li>--%>
<%--			<li class="layui-nav-item">--%>
<%--				<a href="javascript:">1111</a>--%>
<%--				<dl class="layui-nav-child">--%>
<%--					<dd><a href="">1111</a></dd>--%>
<%--					<dd><a href="">1111</a></dd>--%>
<%--					<dd><a href="">1111</a></dd>--%>
<%--				</dl>--%>
<%--			</li>--%>
		</ul>
		<ul class="layui-nav layui-layout-right">
<%--			<li class="layui-nav-item">--%>
<%--				&lt;%&ndash;				头像设置&ndash;%&gt;--%>
<%--				<a href="javascript:;">--%>
<%--					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">--%>
<%--					111--%>
<%--				</a>--%>
<%--				&lt;%&ndash;					头像下方信息设置&ndash;%&gt;--%>
<%--				<dl class="layui-nav-child">--%>
<%--					<dd><a href="">111</a></dd>--%>
<%--					<dd><a href="">111</a></dd>--%>
<%--				</dl>--%>
<%--			</li>--%>
			<%--			注销设置--%>
<%--	todo 填入session--%>
	<li class="layui-nav-item" onclick="editpwd()">修改密码&nbsp&nbsp&nbsp</li>
	<li class="layui-nav-item">${sessionScope.dname}&nbsp; &nbsp; &nbsp;</li>
<%--   todo填入医生  --%>
	<li class="layui-nav-item">欢迎你,${sessionScope.wname}${sessionScope.rname}</li>
	<li class="layui-nav-item"><a href="<%=path+"logout"%>">退出</a></li>
		</ul>
	</div>
	<%--	菜单显示区域--%>
	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree"  lay-filter="test">
				<c:if test="${sessionScope.menuMap!=null}">
					<c:forEach items="${sessionScope.menuMap}" begin="0" var="i">
						<li class="layui-nav-item layui-nav-itemed">
							<a class="" href="javascript:;" >${i.key}</a>
							<dl class="layui-nav-child">
								<c:forEach items="${i.value}" begin="0" var="j">
									<dd><a  title="${j.mpath}" onclick="openThis(this)">${j.secondname}</a></dd>
								</c:forEach>
							</dl>
						</li>
					</c:forEach>
				</c:if>
			</ul>

		</div>
	</div>

	<div class="layui-body">
		<!-- 内容主体区域 -->
		<div style="height: 100%;width: 100%">
			<%--			内容主体区域--%>
<%--			//接收项目--%>
<%--			<iframe style="height: 100%;width: 100%"  title="<%=path%>" src="<%=path+"receitem"%>" frameborder="0" id="myframe" name="myframe"></iframe>--%>
			<%--			编写总结报告--%>
			<iframe style="height: 100%;width: 100%"  title="<%=path%>" src="" frameborder="0" id="myframe" name="myframe"></iframe>
<%--			<iframe style="height: 100%;width: 100%"  title="<%=path%>" src="<%=path+"receitem"%>" frameborder="0" id="myframe" name="myframe"></iframe>--%>
		</div>
	</div>
	<input type="hidden" id="path"  value="<%=path%>">
	<div class="layui-footer">
		<!-- 底部固定区域 -->
		© 健康团检体检系统
<%--				<input type="text" name="test" id="test" value="<%=path%>">--%>
	</div>
</div>

<%--修改密码div--%>
<div  class="layui-form" style="display: none" id="editpwd" name="editpwd">
	<div class="layui-form-item">
		<div style="margin-left: 80px;background-color: #FFB800">
			<label class="layui-form-label">修改密码</label>
		</div>
		<br><br>
		<div class="layui-input-block" style="width: 200px;height: 200px;margin-left: 50px" >
			<%--	原密码--%>
				<input type="password" id="oldpwd" name="oldpwd" required lay-verify="required" placeholder="请输入原密码" autocomplete="off" class="layui-input" style="width: 200px;">
				<br>
			<%--	现在密码--%>
				<input type="password" id="newpwd" name="newpwd" required lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input " style="width: 200px;">
				<br>
			<%--	重复输入密码--%>
				<input type="password" id="twicepwd" name="twicepwd" required lay-verify="required" placeholder="再次输入新密码" autocomplete="off" class="layui-input" style="width: 200px;">
				<br>
				<button id="pwdbtn" name="pwdbtn" style="margin-left: 80px">提交</button>
		</div>
	</div>
</div>
<%--修改按钮点击事件--%>
<script>
	layui.use(['form','layer','jquery','table'], function(){
		var table = layui.table;
		var form = layui.form;
		var $ = layui.jquery;
		var layer  = layui.layer;

		//打开弹窗，还要写一个监听按钮状态的 如果点击了就去发送ajax,然后要根据请求的结果进行提示是否成功
		$("#pwdbtn").click(function () {
			//发送ajax
			// 旧密码内容
			var flag ="";
			var oldpwd = $('#oldpwd').val();
			// 新密码内容
			var newpwd = $('#newpwd').val();
			var twicepwd = $('#twicepwd').val();

			//判断是否为空
			if (oldpwd.length<1){
				layer.msg("原密码未输入");
				return false;
			} else if (newpwd.length<1) {
				layer.msg("新密码未输入");
				return false;
			} else if (twicepwd.length<1){
				layer.msg("请输入再次输入密码");
				return false;
			}else if(twicepwd!=newpwd){
				layer.msg("两次输入密码不一致");
				return false;
			}
			if (oldpwd.length > 0 && newpwd.length > 0 && twicepwd.length > 0) {
				flag = "true";
			}

			if (flag){
				$.ajax({
					type:"post",
					url: "/tpes/resetpwd",
					dataType: "text",
					//发送的数据（同时也将数据发送出去）
					data: {oldpwd:oldpwd,newpwd:newpwd},
					success: function (msg) {
						// var res = msg.toString();
						layer.msg(msg);
						var res = msg;
						if (res == 'true'){
							layer.msg('密码修改成功');
							//todo 查看用户权限跳转到不同的界面去
						}else {
							layer.msg("原密码错误");
						}

					},
					error: function (msg) {
						alert("服务器正忙。。。。"+msg);
					}
				});
			}



		});


	});
</script>


<script>
function openThis(node){
	var url = node.title;
	var frame = document.getElementById("myframe");
	var path = $('#path').val()+url;
	frame.src = path;
	frame.setAttribute("src",path);
}


// JavaScript代码区域
layui.use(['element','jquery','form'], function(){
	var element = layui.element;
	var $ = layui.jquery;
});
</script>

<script>
	function fileUpload(node) {
		var path = node.title;
		var frame = document.getElementById("myframe");
		frame.src = path;
	}
</script>

	<%--监听按钮  如果按下 跳出修改密码的弹窗--%>
<script>
	function editpwd() {
		// 跳出弹窗
		layui.use(['form','layer','jquery','table'], function(){
			var table = layui.table;
			var form = layui.form;
			var $ = layui.jquery;
			var layer  = layui.layer;

			//打开弹窗，还要写一个监听按钮状态的 如果点击了就去发送ajax,然后要根据请求的结果进行提示是否成功
			layer.open({
				title:'修改密码',
				type: 1,
				area: ['300px', '300px'],
				shadeClose: true, //点击遮罩关闭
				content: $("#editpwd")
			});
		});
	}
</script>
</body>
</html>