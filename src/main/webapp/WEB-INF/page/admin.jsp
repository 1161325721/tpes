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
	<title>后台管理员操作窗口</title>
	<link rel="stylesheet" href=<%=path+"resources/layui/css/layui.css"%> >
	<script src=<%=path+"resources/layui/layui.js"%>></script>
	<script src=<%=path+"resources/json2.js"%>></script>
	<script src=<%=path+"resources/jquery/jquery-3.4.1.js"%>></script>


</head>
<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<div class="layui-logo">后台管理员操作窗口</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-left">
<%--						<li class="layui-nav-item"><a title="<%=path+"/sysmgr/page/sysmgr_groupmgr"%>" onclick="fileUpload(this)">文件上传(form)</a></li>--%>
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
									<dd><a  title="${j.mpath}" onclick="openThis(this)">${j.mname}</a></dd>
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

			<iframe style="height: 100%;width: 100%"  title="<%=path%>" src="<%=path+"/test"%>" frameborder="0" id="myframe" name="myframe"></iframe>
		</div>
	</div>
	<input type="hidden" id="path"  value="<%=path%>">
	<div class="layui-footer">
		<!-- 底部固定区域 -->
		© mytest.com - 底部固定区域
		<%--		<input type="text" name="test" id="test" value="<%=path%>">--%>
	</div>
</div>
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
</body>
</html>