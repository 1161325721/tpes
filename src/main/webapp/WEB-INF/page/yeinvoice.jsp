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
	<title>Title</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="<%=layuiCssPath%>">
	<link rel="stylesheet" type="text/css" href="<%=cssPath+"yelogin.css"%>">
	<script  type="text/javascript" src="<%=jsPath+"json2.js"%>"></script>
	<script type="text/javascript" src="<%=layuiJsPath%>"></script>
</head>
<style>
	ul,
	ul li {
		list-style: none;
		margin: 0;
		padding: 0;
	}

	label {
		color: #9c5223;
	}

	.invoicMain {
		width: 920px;
		margin: 0 auto;
		font-size: 14px;
		color: #000;
	}

	.invoiceHeader {
		height: 126px;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.headerLeft li:nth-child(1) {
		text-indent: 1em;
	}

	.headerLeft li img {
		width: 105px;
		height: 105px;

	}

	.headerMiddle h1 {
		font-size: 32px;
		color: #9c5223;
		font-family: 'kaiti';
		margin: 5px 12px;
	}

	.line {
		height: 2px;
		border-top: #9c5223 1px solid;
		border-bottom: #9c5223 1px solid;
	}

	.headerRight li {
		line-height: 24px;
	}

	.invoiceBody {
		border: 1px solid #9c5223;
	}

	.userInfo {
		width: 100%;
		display: flex;
		align-items: center;
		height: 96px;
		border-bottom: 1px solid #9c5223;
	}

	.userInfo ul {
		width: 50%;
		margin: 0 5px;
		padding: 0;

	}

	.userInfo ul li {
		line-height: 24px;
	}

	.buy {
		width: 20px;
		border-right: 1px solid #9c5223;
		padding: 0 10px;
		text-align: center;
		height: 100%;
		display: flex;
		align-items: center;
		color: #9c5223;
	}

	.password {
		width: 20px;
		padding: 0 10px;
		border-right: 1px solid #9c5223;
		border-left: 1px solid #9c5223;
		text-align: center;
		height: 100%;
		display: flex;
		align-items: center;
		color: #9c5223;
	}

	.pwdInfo {
		flex: 1;
		padding-left: 5px;
	}

	.goodsInfo {
		height: 210px;
		margin: 0;
		padding: 0;

	}

	.goodsInfo li {
		display: flex;
		color: #9c5223;
		text-align: center;
	}

	.name {
		width: 260px;
		border-right: 1px solid #9c5223;
	}

	.spec {
		width: 140px;
		border-right: 1px solid #9c5223;
	}

	.qty {
		width: 108px;
		border-right: 1px solid #9c5223;
	}

	.unit,
	.taxRate {
		width: 65px;
		border-right: 1px solid #9c5223;
	}

	.qty,
	.price {
		width: 160px;
		border-right: 1px solid #9c5223;
	}

	.money {
		flex: 1;
		border-right: 1px solid #9c5223;
	}

	.taxAmount {
		flex: 1;
	}

	.GoodsTable {
		height: 210px;
		width: 100%;
		border-collapse: collapse;
	}

	.GoodsTable td {
		border-right: 1px solid #9c5223;
		color: #9c5223;
	}

	.GoodsTable tr:nth-child(1),
	.GoodsTable tr:nth-last-child(2) {
		height: 24px;
	}

	.GoodsTable tr:nth-last-child(1) {
		height: 34px;
	}

	.GoodsTable tr:nth-child(1) td {
		text-align: center;
	}

	.GoodsTotal {
		border-top: 1px solid #9c5223;
		border-bottom: 1px solid #9c5223;
	}

	.total td:nth-child(1) {
		text-align: center;
	}

	.invoicetFooter {
		margin: 0;
		padding: 0;
		display: flex;
		justify-content: space-between;
	}

	.invoicetFooter li {
		width: 25%;
	}
</style>

<body id="body">

<div class="invoicMain">
	<%--<div>--%>
	<%--	<button type="button" class="layui-btn" onclick="print()" id="test1">发票打印</button>--%>
	<%--</div>--%>
	<div class="invoiceHeader">

		<ul class="headerLeft">
			<li>
				<!--                <img src="./qrcode.png" alt="">-->
			</li>
			<li>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><span></span>
			</li>
		</ul>
		<div class="headerMiddle">
			<h1>XX医院专用普通发票</h1>
			<div class="line"></div>
		</div>
		<ul class="headerRight">
			<li>
				<label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </label><span></span>
			</li>
			<li>
				<label> 订单号码：&nbsp;</label><span>${requestScope.goid}</span>
			</li>
			<li>
				<label> 发票号码： </label><span>${requestScope.goinvoicingnumber}</span>
			</li>
			<li>
				<label> 开票日期： </label><span>${requestScope.godate}</span>
			</li>
		</ul>
	</div>
	<div class="invoiceBody">
		<div class="userInfo">
			<div class="buy">购买方</div>
			<ul>
				<li>
					<label>名&nbsp;&nbsp;&nbsp;&nbsp;称：</label><span>${requestScope.gcname}</span>
				</li>
				<li>
					<label>团体账号：</label><span>${requestScope.gcaccount}</span>
				</li>
			</ul>
			<div class="password">备注</div>
			<div class="pwdInfo"></div>
		</div>
		<div>
			<table class="GoodsTable" cellpadding="0" cellspacing="0">
				<tr>
					<td style="width: 24%">货物或应税劳务、服务名称</td>
					<td style="width: 10%">规格型号</td>
					<td style="width: 10%">数量</td>
					<td style="width: 10%">单价</td>
					<td style="width: 16%">金额</td>
				</tr>
				<tr>
					<td>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;团体体检</td>
					<td></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${requestScope.goprepay}</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${requestScope.goprepay}</td>
				</tr>
				<tr class="GoodsTotal">
					<td>总计：${requestScope.goprepay}</td>
					<td colspan="7">
						<div style="width: 100%;display:flex">
							<div type="text" style="width: 60%">实收：${requestScope.goreceivedpay}</div>
							<div type="text" style="width: 30%"> 退款：${requestScope.gorefundpay}</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<ul class="invoicetFooter">
		<li>
			<label>收费单位：XXX医院</label>
		</li>
		<li>
			<label>复核：</label>
		</li>
		<li>
			<label>开票人：${requestScope.waccount}</label>
		</li>
		<li>
			<label>销售方：（章）</label>
		</li>
	</ul>
</div>

<script>
	window.onload= function () {
		window.print();
	}
	// function print(){
	// 	// var oldHtml = document.getElementById("body").innerHTML;
	// 	// var printPart = document.getElementById("invoicMain").innerHTML;
	// 	// document.getElementById("body").innerHTML = printbox;
	// 	window.print();
	// 	// document.getElementById("body").innerHTML = oldHtml;
	// }
</script>

</body>
</html>
