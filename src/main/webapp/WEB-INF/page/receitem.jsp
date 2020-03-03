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
	<title>项目接收页面</title>
	<link rel="stylesheet" href=<%=path+"resources/layui/css/layui.css"%> >
	<script src=<%=path+"resources/layui/layui.js"%>></script>
	<script src=<%=path+"resources/json2.js"%>></script>
	<script src=<%=path+"resources/jquery/jquery-3.4.1.js"%>></script>
	<script src=<%=path+"resources/js/hwxupload.js"%>></script>
</head>
<body class="layui-layout-body">

<form class="layui-form">
	<div class="layui-form-item">
		<div class="layui-inline">
			<label class="layui-form-label" style="margin-left: 400px">一卡通号</label>
			<div class="layui-input-inline">
				<input type="text" id="pcardnumber" name="pcardnumber" autocomplete="off" class="layui-input" placeholder="请输入一卡通号" lay-filter="pcardnumber">
			</div>
		</div>

		<div class="layui-inline">
			<div class="layui-input-inline">
				<button class="layui-btn" id="query_pa"  type="button">搜索</button>
			</div>
		</div>

	</div>
</form>
<div class="layui-collapse">
	<div class="layui-colla-item">
		<h2 class="layui-colla-title">病人信息</h2>
		<div class="layui-colla-content layui-show">
			<%--病人信息数据表格--%>
			<table class="layui-hide" id="table_pinfo" lay-filter="table_pinfo"></table>
		</div>
	</div>

	<div class="layui-colla-item">
		<h2 class="layui-colla-title">病人项目列表</h2>
		<div class="layui-colla-content layui-show">
			<%--病人的项目数据表格--%>
			<table class="layui-hide" id="table_pro" lay-filter="table_pro"></table>
		</div>
	</div>

</div>


<%--弹出层测试--%>
<%--<div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">--%>
<%--	<button data-method="offset" data-type="auto" class="layui-btn layui-btn-normal">居中弹出</button>--%>
<%--</div>--%>
<%--弹出层测试--%>
<%--<button id="tan" lay-filter="tan" class="layui-btn" >--%>
<%--我是弹出层按钮--%>
<%--</button>--%>

<div id="type-content" style="display: none">
<%--	弹出层搜索按钮--%>
<%--	<button class="layui-btn layui-btn-normal layui-btn-radius" id="query_item"  type="button">搜索</button>--%>
<%--	细项表格--%>
	<table class="layui-hide" id="itemlist" lay-filter="itemlist"></table>
<%--小结表单--%>
</div>


<div id="showsum" style="display: none;width:600px;height:800px ">
	<%--	弹出层搜索按钮--%>
	<%--	<button class="layui-btn layui-btn-normal layui-btn-radius" id="query_item"  type="button">搜索</button>--%>
	<%--	细项表格--%>
		<div>
<%--			项目    科室XX:人--%>
			<h3>
				<label id="pname1" class="layui-form-label" style="background-color: #95877c;width:600px;text-align: left">
				项目XXX
				</label>
			</h3>
		</div>
<%--		根据项目的类型进行选择 sum_type--%>


		<div  style="float: left;width: 300px;height: 300px;">
				<img class="layui-upload-img" id="sumimg" src="resources/hwxupload/default.jpg"  style="float: left;width: 300px;height: 300px;">
		</div>

		<div style="float: left;width: 300px;height: 300px;" >
			<%--			项目小结--%>
			<h3>
				<label id="sum1" class="layui-form-label" style="background-color: #95877c;width:300px;text-align: left">
					项目小结
				</label>
			</h3><br><br>
			<textarea class="layui-textarea" name="sumtext" id="sumtext1" cols="40" rows="11"  placeholder="小结内容" style="width: 300px;height: 300px;"></textarea>
		</div>
<%--		<img class="layui-upload-img" id="sumimg" src=" " alt="" width="25px">--%>

<%--		<div style="margin-left: 50px">--%>
<%--			<textarea class="layui-textarea" name="sumtext" id="sumtext" cols="30" rows="10"  placeholder="小结内容" style="width: 300px"></textarea>--%>
<%-- 		</div>--%>

		<div>
			<h3>
				<label id="wname1" class="layui-form-label" style="background-color: #95877c;width:600px;text-align: left">
					检测人：XXX
				</label>
			</h3>
		</div>
</div>


<%--普通项目细项列表--%>
<div id="showlist" style="display: none;width:500px;height:800px ">
	<%--	弹出层搜索按钮--%>
	<%--	<button class="layui-btn layui-btn-normal layui-btn-radius" id="query_item"  type="button">搜索</button>--%>
	<%--	细项表格--%>
	<div>
		<%--			项目    科室XX:人--%>
		<h3>
			<label id="pname2" class="layui-form-label" style="background-color: #95877c;width:690px;text-align: left">
				项目XXX
			</label><br><br>
		</h3>
	</div>
	<%--		普通项目的细项细项列表--%>
		<div>
			<table  class="layui-hide" id="itemlist2" lay-filter="itemlist2"></table>
		</div>
		<br><br>
		<div>
			<%--			项目小结--%>
			<h3>
				<label class="layui-form-label" style="background-color: #95877c;width:690px;text-align: left">
					项目小结
				</label><br><br>
			</h3>
		</div>

	<div>
		<textarea class="layui-textarea" name="sumtext2" id="sumtext2" cols="30" rows="10" placeholder="小结内容" style="width: 600px;height: 200px;"> </textarea>
	</div>

	<div>
		<h3>
			<label id="wname2" class="layui-form-label" style="background-color: #95877c;width:690px;text-align: left">
				检测人：XXX
			</label>
		</h3>
	</div>
</div>



<%--上传影像图片、影像小结--%>
<div id="uploadpic" style="display: none;width:500px;height:800px " >
<%--	上传按钮--%>
	<div class="layui-upload">
<%--		上传按钮--%>
	<button class="layui-btn" id="uploadbtn" lay-event="uploadbtn"><i class="layui-icon"></i>上传图片</button>
	<div class="layui-upload-list">
<%--		预览图--%>
		<img class="layui-upload-img" id="demo1" width="200px" height="200px" >
		<p id="demoText"></p>
	</div>

	</div>
	<div>
<%--		文本编辑框--%>
	<textarea class="layui-textarea" id="summary" name="summary" lay-filter="summary" required lay-verify="required" placeholder="请输入小结内容"  class="layui-textarea"></textarea>
	</div>
	<div>
<%--		编辑 监听按钮状态 先询问是否修改 确认在发送ajax--%>
		<button class="layui-btn" id="editsum1" lay-event="editsum1">编辑</button>
<%--		提交 监听按钮状态 先验证（为空、提示最后一次无法在修改）在提交ajax --%>
		<button class="layui-btn" id="submit1" lay-event="submit1">提交</button>
	</div>
</div>

<script>
	// 编写函数 处理点击事件
	layui.use(['form','layer','jquery','table'], function(){
		var table = layui.table;
		var form = layui.form;
		var $ = layui.jquery;
		var layer  = layui.layer;
		//上传

		// 编辑
		$("#editsum1").click(function () {
			// layer.msg("编辑");
			// 文本框内容
			var textval = $("#summary").val();
			// 提交文本框的内容但是不改状态
			// layer.msg(textval);
			//更新项目小结
			$.ajax({
				type:"post",
				url: "/tpes/summary",
				dataType: "text",
				//发送的数据（同时也将数据发送出去）
				data: {textval: textval,type:"edit"},
				success: function (msg) {
					// layer.msg(msg);
					var res = msg;
					if (res === '编辑成功'){
						layer.msg('编辑成功');
						render1();
					}
					//刷新表格



				},
				error: function (msg) {
					alert("服务器正忙。。。"+msg);
				}
			});
		});
		// 提交
		$("#submit1").click(function () {
			// layer.msg("提交");
			var textval = $("#summary").val();
			// layer.msg(textval);
			//提交文本框的内容但是改状态
			$.ajax({
				type:"post",
				url: "/tpes/summary",
				dataType: "text",
				//发送的数据（同时也将数据发送出去）
				data: {textval: textval,type:"submit"},
				success: function (msg) {
					// layer.msg(msg);
					var res = msg;
					if (res === '提交成功'){
						layer.msg('提交成功');
						render1();
					}else if (res==="该项目已完结，无法在修改"){
						layer.msg('该项目已完结，无法在修改');
					}
				},
				error: function (msg) {
					alert("服务器正忙。。。。"+msg);
				}
			});
		});

		$("#uploadbtn").click(function () {
			layer.msg("上传图片")
			//上传文件、更新数据库图片地址
		});
	});




</script>



<%--弹出层js test--%>

<script>
	$('#tan').on('click', function(){
		layer.open({
			type: 1,
			area: ['600px', '360px'],
			shadeClose: true, //点击遮罩关闭
			content: $("#type-content")
		});
	});
</script>

<%--按钮设置--%>
<script type="text/html" id="bar" lay-filter ="btnaction">
	{{#  if(d.cpstate ==1&&d.gostate==1&&d.sum_state==0 ){   }}
	<button class="layui-btn layui-btn-normal" lay-event="detail">录入细项信息</button>
	<button class="layui-btn" lay-event="upload">编写小结</button>
	<button class="layui-btn" lay-event="findsum">查看小结</button>
	{{# }else if(d.sum_state==1){             }}
	<button class="layui-btn" lay-event="findsum">查看小结</button>
	{{# }else if(d.cpstate == 0&&d.gostate==1){             }}
	<button class="layui-btn layui-btn-warm" lay-event="rece">项目接收</button>
	{{# }             }}
	<%--	<button class="layui-btn layui-btn-normal layui-btn-danger" lay-event="nopass">审核不通过</button>--%>
</script>
<%--病人信息 表格数据--%>
<script>
	layui.use(['form','layer','jquery','table'], function(){
		var table = layui.table;
		var form = layui.form;
		var $ = layui.jquery;
		var layer  = layui.layer;
		// 病人信息表
		table.render({
			elem: '#table_pinfo'    //标签id
			,title:'病人信息'
			,even: true
			,page: true
			,width: 1300
			,limit:5
			// ,limits:[5,10,15,20,25,30]
			,cols: [[
				// {type:'checkbox'}
				{field:'pid', width:150, title: '体检人id'}
				,{field:'pname', width:150, title: '名字'}
				,{field:'pphone', width:150, title: '联系电话'}
				,{field:'gcid', width:150, title: '单位id'}
				,{field:'pgender', width:150, title: '性别',templet :function(d){
						return d.pgender == 1 ? '男' : '女';
					}}
				,{field:'pcardnumber', width:150, title: '一卡通号'}
				,{field:'pidentitynumber', width:400, title: '身份证号'}
			]]
			,where:{
				pcardnumber:"",
				all:"all"}
			,url:"/tpes/getPatientInfo"  //先给空数据
			,id:'table_pinfo'

		});



		<%--用于带条件查询--%>
		$("#query_pa").click(function () {
			table.reload('table_pinfo',{
				url:"/tpes/getPatientInfo"
				,where: { //设定异步数据接口的额外参数，任意设
					pcardnumber:$('#pcardnumber').val(),
					all:"all"
				}
				,page: {
					curr: 1 //重新从第 1 页开始
				}
			});
		});
	});
</script>

<script>
	layui.use(['form','layer','jquery','table'], function(){
		var table = layui.table;
		var form = layui.form;
		var $ = layui.jquery;
		var layer  = layui.layer;
		var layuiTable = layui.table;

		table.render({
			elem: '#table_pro'    //标签id
			,title:'项目列表'
			,even: true
			,page: true
			,width: 1300
			,limit:5
			,limits:[5,10,15,20,25,30]
			,cols: [[
				{type:'checkbox'}
				,{field:'cpid', width:100, title: '报告id'}
				,{field:'proid', width:100, title: '项目id'}
				,{field:'pname', width:100, title: '项目名称'}
				,{field:'sum_type', width:100, title: '项目类型',templet :function(d){
							return d.sum_type== 'img' ? '影像' : '普通';
						}}
				,{field:'gostate', width:150, title: '结算状态',templet :function(d){
						return d.gostate == 1 ? '已结算' : '未结算';
					}}
				,{field:'cpstate', width:150, title: '接收状态',templet :function(d){
						return d.cpstate == 1 ? '已接收' : '未接收';
					}}
				,{field:'sum_state', width:100, title: '是否小结',templet :function(d){
						return d.sum_state == 1 ? '是' : '否';
					}}
				,{field:'status', width:500, title: '操作',toolbar:"#bar"}
			]]
			,where:{
				pcardnumber:"",
				wid:"",
				all:"all"}
			,url:"/tpes/getProjectList"    //先给空数据
			,id:'table_pro'
		});

				<%--用于带条件查询--%>
				$("#query_pa").click(function () {
					table.reload('table_pro',{
						url:"/tpes/getProjectList"
						,where: { //设定异步数据接口的额外参数，任意设
							pcardnumber:$('#pcardnumber').val(),
							wid:${sessionScope.worker.wid},
							all:"all"
						}
						,page: {
							curr: 1 //重新从第 1 页开始
						}
					});
				});


		table.on('tool(table_pro)', function(obj){
			// var checkStatus = table.checkStatus(obj.config.id);
			var data = JSON.stringify(obj.data);//获取当前行的数据
			switch(obj.event){
				case 'rece':
							// layer.alert(data);
							//发送ajax请求改变 项目接收状态
					$.ajax({
						type:"post",
						url: "/tpes/receProj",
						dataType: "text",
						//发送的数据（同时也将数据发送出去）
						data: {data: data},
						success: function (msg) {
							// var res = msg.toString();
							var res = msg;
							if (res == 'true'){
								layer.msg('接收成功');
								//刷新表格 reload
								table.reload('table_pro',{
									url:"/tpes/getProjectList"
									,where: { //设定异步数据接口的额外参数，任意设
										pcardnumber:$('#pcardnumber').val(),
										wid:${sessionScope.worker.wid},
										all:"all"
									}
									,page: {
										curr: 1 //重新从第 1 页开始
									}
								});
							}else if (res=='nopay') {
								layer.msg("无法接收：该订单未付款成功")
							}else if (res=="already"){
								layer.msg("无法接收：该用户已经接收")
							}
						},
						error: function (msg) {
							alert("服务器正忙。。。。"+msg);
						}
					});




							//刷新表格：修改表格发送的数据是session中的数据
							break;
						case 'detail':
							// layer.alert(data[0]);

							/*
							获取session中的数据 wid ,proid ,pcardnumber ajax发送给后台 查询所有细项收到后，
							成功，1.跳出弹窗：显示所有的细项信息
							获取数据，渲染表格

							失败，1.显示，没有细项数据
							 */
							table.reload('itemlist',{
								url:"/tpes/getItemList"
								,where: { //设定异步数据接口的额外参数，任意设
									data:data,
									all:"all"
								}
								,page: {
								curr: 1 //重新从第 1 页开始
								}
								});

								layer.open({
									title:'细项信息',
									type: 1,
									area: ['800px', '360px'],
									shadeClose: true, //点击遮罩关闭
									content: $("#type-content")
								});
							break;

				case "upload":
					// layer.msg("上传图片");
					//ajax上传cpid，上传图片，图片绑定cpid,小结
					//显示div,上边上传图片，下边编写小结
					//todo 更新cpid
					//设置小结内容
					//清楚div中给的值
					$('#demo1').attr('src', ""); //图片链接（base64）

					$.ajax({
						type:"post",
						url: "/tpes/getCpid",
						dataType: "text",
						//发送的数据（同时也将数据发送出去）
						data: {data: data},
						success: function (msg) {
							// var res = msg.toString();
							var res = JSON.parse(msg);
							var cpsummary = res.cpsummary;
							var cpid = res.cpid;
							// layer.msg("cpsummary" + cpsummary);
							// layer.msg("cpsummary类型："+typeof (cpsummary));
							// layer.msg("cpid" + cpid);
							// layer.msg("cpid类型"+typeof(cpid));
							//是否为空 如果不是怎么赋值
							$("#summary").val(cpsummary);
						},
						error: function (msg) {
							alert("服务器正忙。。。。"+msg);
						}
					});


					layer.open({
						title:'编写小结',
						type: 1,
						area: ['600px', '600px'],
						shadeClose: true, //点击遮罩关闭
						content: $("#uploadpic")
					});

							break;
				case "findsum":
					//发送ajax查询是否为影像项目
					layer.msg("data" + data);
					 data = eval("("+data+")");
					 // ajax发送数据刷新列表

					//总结表格设置内容
					objdata=JSON.stringify(data);
					table.reload('itemlist2',{
						url:"/tpes/getItemList"
						,where: { //设定异步数据接口的额外参数，任意设
							data:objdata,
							all:"all"
						}
						,page: {
							curr: 1 //重新从第 1 页开始
						}
					});
					if (data.sum_type === "img") {
						//设置一个表格带图片的 详情 刷新表格
						//设置 设置科室名 检测人 检测项目
						//项目名pname
						var pname = data.pname;
						//科室名dname
						var dname = data.dname;
						//检测人wname
						var wname =data.wname;
						var cpsummary = data.cpsummary;
						$('#pname1').text(pname);
						$('#sumtext1').text(cpsummary);
						$('#wname1').text(dname+"  "+wname);

						layer.open({
							title:"查看影像小结",
							type: 1,
							area: ['600px', '600px'],
							shadeClose: true, //点击遮罩关闭
							content: $("#showsum")
						});
						//layer.open影像模版
					}else{
						//设置一个细项表格 刷新
						//设置 设置科室名 检测人 检测项目
						var pname = data.pname;
						//科室名dname
						var dname = data.dname;
						//检测人wname
						var wname =data.wname;
						var cpsummary = data.cpsummary;
						$('#pname2').text(pname);
						$('#sumtext2').text(cpsummary);
						$('#wname2').text("检查人： "+ dname+"  "+wname);


						//刷新表格
						//layer.open
						layer.open({
							title:'查看普通体检小结',
							type: 1,
							area: ['600px', '600px'],
							shadeClose: true, //点击遮罩关闭
							content: $("#showlist")
						});
					}

					// $.ajax({
					// 	type:"post",
					// 	url: "tpes/",
					// 	dataType: "text",
					// 	//发送的数据（同时也将数据发送出去）
					// 	data: {useraccount: useraccount,password:password},
					// 	success: function (msg) {
					// 		// var res = msg.toString();
					// 		layer.msg(msg);
					// 		var res = msg;
					// 		if (res == 'true'){
					// 			layer.msg('登录成功');
					// 			//todo 查看用户权限跳转到不同的界面去
					// 			window.location.href="jump/goIndex";
					// 		}
					//
					// 	},
					// 	error: function (msg) {
					// 		alert("服务器正忙。。。。"+msg);
					// 	}
					// });


					break;
			};
		});

		layuiTable.on('edit(itemlist)', function (obj) {
			var data = obj.data;
			rowdata = data;
			editvalue = true;
		});
	});

</script>



<%--测试弹窗表格--%>
<script>
layui.use(['form','layer','jquery','table'], function(){
var table = layui.table;
var form = layui.form;
var $ = layui.jquery;
var layer  = layui.layer;
// 细项列表
table.render({
elem: '#itemlist'    //标签id
,even: true
,page: true
,width: 1000
,limit:5
,limits:[5,10,15,20,25,30]
,cols: [[
// {type:'checkbox'}
{field:'iid', width:150, title: '细项id'}
,{field:'iname', width:150, title: '细项'}
,{field:'cicompareresult', width:150, title: '结果',templet: '#cicompareresultTpl'}
,{field:'cicheckvalue', width:150, title: '检测值', edit: 'text'}
,{field:'iunit', width:150, title: '单位'}
// ,{field:'range', width:150, title: '参考值'}
]]

,where:{
	//session中获取参数
		data:"",
		all:"all"}
,url:"/tpes/getItemList"  //先给空数据
,id:'itemlist'
});

//修改表格数据，并同步后台数据库
	table.on('edit(itemlist)', function(obj){
		var value = obj.value //得到修改后的值
			,data = obj.data //得到所在行所有键值
			,field = obj.field; //得到字段
		layui.use('jquery',function(){
			var $=layui.$;
			$.ajax({
				type: 'post',
				url: "/tpes/updateItem", // ajax请求路径
				data: {
					data:JSON.stringify(data),
					field:field,
					value:value
				},
				success: function(data){
					layer.msg('修改成功');//测试成功 删掉提示
				}
			});
		});
	});

});
</script>


<%--折叠面板--%>
<script>
	//注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
	layui.use('element', function(){
		var element = layui.element;
	});
</script>

<script>
	function render1() {

		layui.use(['form','layer','jquery','table'], function(){
			var table = layui.table;
			var form = layui.form;
			var $ = layui.jquery;
			var layer  = layui.layer;
			var layuiTable = layui.table;

			// 病人信息表

			<%--用于带条件查询--%>
			$("#editsum1").click(function () {
				table.reload('table_pinfo',{
					url:"/tpes/getPatientInfo"
					,where: { //设定异步数据接口的额外参数，任意设
						pcardnumber:$('#pcardnumber').val(),
						all:"all"
					}
					,page: {
						curr: 1 //重新从第 1 页开始
					}
				});
			});

			<%--用于带条件查询--%>
			$("#editsum1").click(function () {
				table.reload('table_pro',{
					url:"/tpes/getProjectList"
					,where: { //设定异步数据接口的额外参数，任意设
						pcardnumber:$('#pcardnumber').val(),
						wid:${sessionScope.worker.wid},
						all:"all"
					}
					,page: {
						curr: 1 //重新从第 1 页开始
					}
				});
			});

			<%--用于带条件查询--%>
			$("#submit1").click(function () {
				table.reload('table_pinfo',{
					url:"/tpes/getPatientInfo"
					,where: { //设定异步数据接口的额外参数，任意设
						pcardnumber:$('#pcardnumber').val(),
						all:"all"
					}
					,page: {
						curr: 1 //重新从第 1 页开始
					}
				});
			});

			<%--用于带条件查询--%>
			$("#submit1").click(function () {
				table.reload('table_pro',{
					url:"/tpes/getProjectList"
					,where: { //设定异步数据接口的额外参数，任意设
						pcardnumber:$('#pcardnumber').val(),
						wid:${sessionScope.worker.wid},
						all:"all"
					}
					,page: {
						curr: 1 //重新从第 1 页开始
					}
				});
			});



		});
	}

	function render2() {
		layui.use(['form','layer','jquery','table'], function(){
			var table = layui.table;
			var form = layui.form;
			var $ = layui.jquery;
			var layer  = layui.layer;
			var layuiTable = layui.table;

			table.reload('table_pinfo',{
				url:"/tpes/getPatientInfo"
				,where: { //设定异步数据接口的额外参数，任意设
					pcardnumber:$('#pcardnumber').val(),
					all:"all"
				}
				,page: {
					curr: 1 //重新从第 1 页开始
				}
			});

			table.reload('table_pro',{
				url:"/tpes/getProjectList"
				,where: { //设定异步数据接口的额外参数，任意设
					pcardnumber:$('#pcardnumber').val(),
					wid:${sessionScope.worker.wid},
					all:"all"
				}
				,page: {
					curr: 1 //重新从第 1 页开始
				}
			});


			table.reload('table_pinfo',{
				url:"/tpes/getPatientInfo"
				,where: { //设定异步数据接口的额外参数，任意设
					pcardnumber:$('#pcardnumber').val(),
					all:"all"
				}
				,page: {
					curr: 1 //重新从第 1 页开始
				}
			});

			table.reload('table_pro',{
				url:"/tpes/getProjectList"
				,where: { //设定异步数据接口的额外参数，任意设
					pcardnumber:$('#pcardnumber').val(),
					wid:${sessionScope.worker.wid},
					all:"all"
				}
				,page: {
					curr: 1 //重新从第 1 页开始
				}
			});



		});


	}

</script>
<%--细项列表 用于小结--%>
<script>
	// 细想表格 （项目名、结果、单位、参考值、提示）  cpid 要什么数据
	layui.use(['form','layer','jquery','table'], function(){
		var table = layui.table;
		var form = layui.form;
		var $ = layui.jquery;
		var layer  = layui.layer;
// 细项列表
		table.render({
			elem: '#itemlist2'    //标签id
			,even: true
			,page: true
			,width: 600
			,limit:5
			,limits:[5,10,15,20,25,30]
			,cols: [[
// {type:'checkbox'}
				{field:'iid', width:100, title: '细项id'},
				{field:'iname', width:100, title: '细项id'},
				{field:'cicheckvalue', width:100, title: '检测值', edit: 'text'},
				{field:'iunit', width:100, title: '单位'},
				{field:'range', width:100, title: '参考值'},
				{field:'cicompareresult', width:100, title: '结果'}
			]]

			,where:{
				//session中获取参数
				data:"",
				all:"all"}
			,url:"/tpes/getItemList"  //先给空数据
			,id:'itemlist2'
		});

//修改表格数据，并同步后台数据库
		table.on('edit(itemlist)', function(obj){
			var value = obj.value //得到修改后的值
				,data = obj.data //得到所在行所有键值
				,field = obj.field; //得到字段
			layui.use('jquery',function(){
				var $=layui.$;
				$.ajax({
					type: 'post',
					url: "/tpes/updateItem", // ajax请求路径
					data: {
						data:JSON.stringify(data),
						field:field,
						value:value
					},
					success: function(data){
						layer.msg('修改成功');//测试成功 删掉提示
					}
				});
			});
		});
	});

</script>

<script type="text/html" id="cicompareresultTpl">
	{{#  if(d.cicompareresult === '↓'){ }}
	<span style="background: #1E9FFF;color: #555555;">{{ d.cicompareresult }}</span>
	{{#  } else if(d.cicompareresult === '↑') { }}
	<span style=" background: #FFB800;color: #555555;">{{ d.cicompareresult }}</span>
	{{#  } }}
</script>


<input type="hidden" name="cpid1" value="">
<input type="hidden" name="wid1" value="">
</body>
</html>
