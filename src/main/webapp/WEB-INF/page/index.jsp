<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = application.getContextPath() + "/";
%>
<html>
<head>
	<title>测试主页</title>
	<link rel="stylesheet" href=<%=path+"resources/layui/css/layui.css"%> >
	<script src=<%=path+"resources/layui/layui.js"%>></script>
</head>
<body>

<ul class="layui-nav" lay-filter="">
	<li class="layui-nav-item"><a href=<%=path%>>测试</a></li>
</ul>

测试el表达式：
1+1=${1+1}

${requestScope.list}

<c:if test="${requestScope.list != null}">
		<c:forEach items="${requestScope.list}" begin="0" var="i">
			${i.test1}
		</c:forEach>
</c:if>

</body>
</html>