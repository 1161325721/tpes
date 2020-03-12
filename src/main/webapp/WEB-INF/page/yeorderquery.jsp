
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
	<title>单位体检订单查询</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link rel="stylesheet" href="<%=layuiCssPath%>">
	<script type="text/javascript" src=<%=jsPath+"jquery-3.4.1.js"%>></script>
	<script  type="text/javascript" src="<%=jsPath+"json2.js"%>"></script>
	<script type="text/javascript" src="<%=layuiJsPath%>"></script>
	<%--	<script type="text/javascript" src="<%=path+"/js/maintest.js"%>"></script>--%>
</head>
<body>
<%
	if(request.getAttribute("msg")!=null){
		String result = (String) request.getAttribute("msg");
		System.out.println("result:"+result);
		out.write("<script> alert('"+result+"')</script>");
	}
%>
<script type="text/html" id="barDemo">
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="print">打印发票</a>
	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="editAppointmentDate">延长预约时间</a>
<%--	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
</script>

<div class="demoTable">
	<br>
	&nbsp;&nbsp;&nbsp;&nbsp;
	单位名称：
	<div class="layui-inline">
		<input class="layui-input" name="name" id="name" autocomplete="off">
	</div>
	单位账号：
	<div class="layui-inline">
		<input class="layui-input" name="account" id="account" verify="required|number" autocomplete="off">
	</div>
	开单时间：
	<div class="layui-inline">
		<input type="date" name="date" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
	</div>

	<input type="hidden" value="<%=path%>" id="path">
	<button class="layui-btn" id="query" data-type="reload">搜索</button>
</div>

<table class="layui-hide" id="LAY_table_user" lay-filter="test"></table>

<script>
	layui.use(['table','jquery'], function(){
		var table = layui.table;
		var $2 = layui.jquery;
		//方法级渲染
		table.render({
			elem: '#LAY_table_user'
			,url: $2('#path').val()+'/yeOrderTable.do'
			,cols: [[ //表头
				{field: 'gcname', title: '单位名称', width:10+'%'}
				// templet:function (d) {
				// 	return d.id ==1?"男":"女";}
				,{field: 'gcaccount', title: '单位账号', width:10+'%'}
				,{field: 'goid', title: '订单号', width:10+'%' }
				,{field: 'goprepay', title: '预收金额', width:10+'%'}
				,{field: 'gorefundpay', title: '退款金额', width:10+'%'}
				,{field: 'goreceivedpay', title: '实收金额', width:10+'%' }
				,{field: 'goinvoicingstate', title: '发票状态', width:8+'%' ,
					templet:function (d) {return d.goinvoicingstate ===1?"未开":"已开";}}

				,{field: 'sgoordertime', title: '开单时间', width:15+'%' }
				,{field: 'wname', title: '开单人员', width:10+'%' }
				,{field: 'goappointdate', title: '预约时间', width:10+'%' }
				,{field: 'golasttime', title: '预约有效期', width:10+'%' }
				,{field: 'gostate', title: '订单状态', width:10+'%' ,
					templet:function (d) {return d.gostate ==='1'?"处理中":"已结束";}}
				,{fixed: 'right',title: '操作', width: 18+'%', align:'center', toolbar: '#barDemo'}

			]]
			,id: 'testReload'
			,page: true
			,limit:5
			,height: 310
		});

		var $ = layui.$, active = {
			reload: function(){
				//执行重载
				table.reload('testReload', {
						page: {
							curr: 1 //重新从第 1 页开始
						}
						,where: {
							name:$('#name').val(),
							date:$('#date').val(),
							account:$("#account").val()
						}
					},
					'data');
			}
		};

		$('#query').on('click', function(){
			var type = $(this).data('type');
			active[type] ? active[type].call(this) : '';
		});
	});

	//将表单转为js对象数据
	function serializeObject($, array){
		var obj=new Object();
		$.each(array, function(index,param){
			if(!(param.name in obj)){
				obj[param.name]=param.value;
			}
		});
		return obj;
	}


	layui.use(["table","jquery","layer",'form'],function () {
		var table = layui.table;
		var $ = layui.jquery;
		var layer = layui.layer;

		//监听行工具事件
		table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			var data = obj.data //获得当前行数据
				,layEvent = obj.event; //获得 lay-event 对应的值
			//打印发票（只有订单结束和未开过发票的订单才可以打印发票）
			if(layEvent === 'print'){
				layer.confirm('确认打印发票',{icon:1,title:"提示"}, function(index){
					//跳转打印界面
					if(data.goinvoicingstate===0){
						layer.msg("提示：订单发票已开");
					} else if(data.gostate==='1'){
						layer.msg("提示：订单未结束");
					} else{
						window.location.href = $('#path').val() +"/print.do?goid="+data.goid;
					}
					// obj.del(); //删除对应行（tr）的DOM结构
					layer.close(index);
					//向服务端发送删除指令
				});
			} else if(layEvent="editAppointmentDate"){
				//只有未结束的订单才可以修改订单
				if(data.gostate==='0'){
					layer.msg("提示：订单已结束");
				} else{
					layer.open({
						offset: 'auto',
						type: 2,
						title: '延长预约',
						shade: 0,
						maxmin: true,
						area: ['500px', '480px'],
						btn: ['确认修改', '取消'],
						btn1: function(index, layero){
							//layer.getChildFrame("form", index)获取iframe的表单
							//serializeArray jquery方法，将表单对象序列化为数组

							var formData = serializeObject($, layer.getChildFrame("form", index).serializeArray());
							$.ajax({
								url:$('#path').val()+'/editAppointDayMethod.do',
								type:'post',
								dataType:"text",
								data: {data:JSON.stringify(data),formData:JSON.stringify(formData)},
								success:function(msg){
									// var obj = JSON.parse(msg);
									// var i = obj.i;

									if(msg==='success'){
										alert("修改成功");
										var demoReload = $('#demoReload');
										table.reload('testReload', {
											page: {
												curr: $(".layui-laypage-em").next().html()  //重新从第 1 页开始
											}
											,where: {
												name:demoReload.val()
											}
										});
									} else{
										alert("修改失败");
									}
									layer.close(index);
								},error:function (err) {
									console.log(err);
								}
							});
						},
						content: [$('#path').val()+'/to/yeappointmentedit.do','no'] //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
						,success: function(layero, index){
							console.log(layero, index);
						}
					});
				}
			}

		});
	});


</script>

</body>
</html>
