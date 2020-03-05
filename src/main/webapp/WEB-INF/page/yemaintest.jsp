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
	String layuiJsPath = request.getContextPath() +"/resources/layui/layui.all.js";
%>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<title>layout 后台大布局 - Layui</title>
	<link rel="stylesheet" href="<%=layuiCssPath%>" media="all">
	<script type="text/javascript" src=<%=jsPath+"jquery-3.4.1.js"%>></script>
	<script  type="text/javascript" src="<%=jsPath+"json2.js"%>"></script>
	<script type="text/javascript" src="<%=layuiJsPath%>" ></script>
	<%--	<script type="text/javascript" src="<%=path+"/js/maintest.js"%>"></script>--%>
</head>
<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">
	<div class="layui-header">
		<input type="hidden" id="path" value="<%=path%>">
		<div class="layui-logo">layui 后台布局</div>
		<!-- 头部区域（可配合layui已有的水平导航） -->
		<ul class="layui-nav layui-layout-left">
			<li class="layui-nav-item"><a href="javascript:" title="/to/yeregister.do"  onclick="turnWeb(this)" id="addDepartmentAccount" >单位注册</a></li>
			<li class="layui-nav-item"><a href="javascript:" title="/yesinglebill.do"  onclick="turnWeb(this)" id="singlebill" >单人开单</a></li>
			<li class="layui-nav-item"><a href="javascript:" id="test4">批量开单（xls/xlsx）</a></li>
			<li class="layui-nav-item"><a href="javascript:" title="/to/yereorder.do" id="test5" onclick="turnWeb(this)">重新上次订单</a></li>
			<li class="layui-nav-item"><a href="javascript:" title="/to/yeexcelout.do"  onclick="turnWeb(this)" id="outexcel" >导出excel名单</a></li>
			<li class="layui-nav-item"><a href="<%=path+"/yedownloadmodel.do"%>"   id="download" >下载excel模板</a></li>
		</ul>
		<ul class="layui-nav layui-layout-right">
			<li class="layui-nav-item">
				<a href="javascript:;">
					<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
					111
				</a>
				<dl class="layui-nav-child">
					<dd><a href="">111</a></dd>
					<dd><a href="">111</a></dd>
				</dl>
			</li>
			<li class="layui-nav-item"><a href="">退出</a></li>
		</ul>
	</div>

	<div class="layui-side layui-bg-black">
		<div class="layui-side-scroll">
			<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
			<ul class="layui-nav layui-nav-tree"  lay-filter="test">
				<c:if test="${requestScope.menuMap!=null}">
					<c:forEach items="${requestScope.menuMap}" begin="0" var="i">
						<li class="layui-nav-item layui-nav-itemed">
							<a class="javascript:;" href="javascript:;" >${i.key}</a>
							<dl class="layui-nav-child">
								<c:forEach items="${i.value}" begin="0" var="j">
									<dd><a  title="${j.url}" onclick="turnWeb(this)">${j.secondname}</a></dd>
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

			<iframe style="height: 100%;width: 100%"  title="<%=path%>" src="<%=path+"/to/yewelcome.do"%>" frameborder="0" id="myframe" name="myframe"></iframe>
		</div>
	</div>
	<div class="layui-footer">
		<!-- 底部固定区域 -->
		© mytest.com - 底部固定区域
		<%--		<input type="text" name="test" id="test" value="<%=path%>">--%>
	</div>
</div>

<script>
	<%--	单位注册--%>
	function turnWeb(node) {
		var url = node.title;
		var frame = document.getElementById("myframe");
		var path = document.getElementById('path').value;
		alert(path+url);
		frame.src = path+url;
		frame.setAttribute("src",path+url);
	}
</script>

<script>
	<%--	表格文件上传的js--%>
	layui.use('upload', function() {
		var $ = layui.jquery
			, upload = layui.upload;
		upload.render({ //允许上传的文件后缀
			elem: '#test4'
			,url: $('#path').val()+'/yefileupload.do' //改成您自己的上传接口
			,accept: 'file' //普通文件
			,exts: 'xls|xlsx' //只允许上传压缩文件
			,done: function(res){
				// alert(res);
				if(res.code===0){
					layer.msg('开单成功');
				} else{
					layer.msg(res.msg);
				}
			}
		});
	});

</script>


</body>
</html>