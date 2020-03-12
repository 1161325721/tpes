<%--
  Created by IntelliJ IDEA.
  User: success
  Date: 2020/3/5
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html lang="en">
<%
	String path = application.getContextPath() + "/";
%>

<head>
	<meta charset="utf-8">
	<script src=<%=path+"resources/jquery/jquery-3.4.1.js"%>></script>
	<script src=<%=path+"resources/layui/layui.js"%>></script>
	<script src=<%=path+"resources/js/echarts.js"%>></script>
	<script src=<%=path+"resources/json2.js"%>></script>
	<script src=<%=path+"resources/js/hwx_department_stat.js"%>></script>

	<link rel="stylesheet" href=<%=path+"resources/layui/css/layui.css"%> >
	<title>Title</title>
</head>



<body>
<div>
	<input type="button" value="各科室占比" onclick="btnsearch1()">

</div>
<div id="echarts_div" style="height:100%;width:100%;"></div>
<div id="echarts_div2" style="height:100%;width:100%;"></div>
</body>

</html>
