<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = application.getContextPath();
%>
<html>
<head>
	<title>测试主页</title>
	<link rel="stylesheet" href=<%=path+"/resources/layui/css/layui.css"%> >
	<script src=<%=path+"/resources/layui/layui.js"%>></script>
</head>
<body>


<ul class="layui-nav" lay-filter="">
	<li class="layui-nav-item"><a href=<%=path%>>测试</a></li>
</ul>

<div>
	<input type="hidden" id="path" name="path" value="<%=path%>">
</div>


<div class="layui-upload">
	<%--	表格的文件上传--%>
	<button type="button" class="layui-btn layui-btn-primary" id="test4">文件类型（xls/xlsx）</button>
</div>

<div>
<%--	单位注册--%>
	<button type="button" value="/to/yeregister.do" class="layui-btn" onclick="turnWeb(this)" id="addDepartmentAccount" >单位注册</button>
</div>

<div>
	<%--	单人开单--%>
	<button type="button" value="/yesinglebill.do" class="layui-btn" onclick="turnWeb(this)" id="singlebill" >单人开单</button>
</div>

<div>
	<%--	导出单位正在处理人员名单名单--%>
	<button type="button" value="/to/yeexcelout.do" class="layui-btn" onclick="turnWeb(this)" id="outexcle" >导出单位excel名单</button>
</div>



<script>
<%--	单位注册--%>
	function turnWeb(node) {
		var path = document.getElementById('path').value;
		alert(path+node.value);
		window.location.href = path+node.value;
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