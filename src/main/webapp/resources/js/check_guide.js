
var path;
var cond;
var table;
var form;
var layer;
var editLayer;
var transfer;
var tree;

$(function () {

	path = $("#path").val();

	layui.use(['table','form','layer','transfer','tree'], function(){
		form = layui.form;
		table = layui.table;
		layer = layui.layer;
		tree = layui.tree;
		transfer = layui.transfer;
		table.render({
			elem: '#demo'
			,height: 400
			,limit:10
			,url: path+'check/reqCheckByCard' //数据接口
			// ,toolbar: '#barDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: []
			,page: true //开启分页
			,cols: [[ //表头
				// {type:'checkbox'}
				{field: 'gcid', title: '导检单ID', width:120, sort: true,align:'center'}
				,{field: 'gcdate', title: '时间', width:120,align:'center'}
				,{fixed: 'right', title:'操作', toolbar: '#tableBar', width:150,align:'center'}
			]]
		});

		//监听行工具栏事件
		table.on('tool(test)', function(obj){
			var data = obj.data;
			// console.log(obj);
			if(obj.event === 'look'){
			}
			if(obj.event === 'print'){
				openDiv(data);
			}
		});

		$("#search").click(function () {
			cond = form.val("condition");
			tableReload();
		});

	});


	$("#print").click(function() {

		var rules = "table{border-collapse:collapse;margin:0 auto;text-align:center;width: 100%;}table td,table th{border:1px solid #cad9ea;color:#666;height:30px}table thead th{background-color:#F1F1F1;min-width:400px}table tr{background:#fff}",
			ss = document.styleSheets;
		for(var i = 0; i < ss.length; ++i) {
			for(var x = 0; x < ss[i].cssRules.length; ++x) {
				rules += ss[i].cssRules[x].cssText;
			}
		}

//先clone来避免影响页面显示
		var clone = $("#content").clone();
//找到所有echarts图表容器
		var charts = clone.find(".chart");
//隐藏无需导出的dom
		clone.find(".input_div").hide();
//简单控制流程
		var flag = charts.length;
		for(var i = 0; i < charts.length; i++) {
			//获取echarts对象
			var curEchart = echarts.getInstanceByDom(charts[i]);
			if(curEchart) {
				//将图表替换为图片
				var base = curEchart.getConnectedDataURL();
				var img = $('<img style="" src="' + base + '"/>');
				$(charts[i]).html(img);
				flag--;
			} else {
				flag--;
			}
		}
		var interval = setInterval(function() {
			if(!flag) {
				clearInterval(interval);
				//导出word
				clone.wordExport(name, rules);
			}
		}, 200)

		// $("#content").wordExport("fileName");                  //fileName为导出的word文件的命名,content为要导出的html内容容器
		// html2canvas(document.getElementById("content"), {
		// 	onrendered: function (canvas) {
		// 		//通过html2canvas将html渲染成canvas，然后获取图片数据
		// 		var imgData = canvas.toDataURL('image/jpeg');
		//
		// 		//初始化pdf，设置相应格式
		// 		var doc = new jsPDF("p", "mm", "a4");
		//
		// 		doc.setFillColor(0, 0, 0);
		//
		// 		//这里设置的是a4纸张尺寸
		// 		doc.addImage(imgData, 'JPEG', 0, 0, 210, 297);
		//
		// 		//输出保存命名为content的pdf
		// 		doc.save('content.pdf');
		// 	}
		// });
	});

});


function openDiv(data) {


	console.log(data);
	var sendData = {
		condition:JSON.stringify({gcid:data.gcid})
	};
	$.ajax({
		type:"POST",
		url:path+'check/reqCheck',
		dataType:"text",
		data:sendData,
		success:function (msg) {
			var ob = JSON.parse(msg).data;
			var pat = {
				gcid:ob[0].gcid,
				gcdate:ob[0].gcdate,
				pname:ob[0].patient.pname,
				pgender:function () {
					return ob[0].patient.pgender == 0 ? '男':'女';
				}
			};
			form.val("editForm",pat);
			tableItemReload({condition:JSON.stringify({gcid:ob[0].gcid})});


			editLayer=layer.open({
				type:1,
				title:"参数编辑",
				content:$("#editDiv"),
				area:['800px','auto'],
				success:function (index) {
				}
			});

		},
		error:function () {
			alert("服务器正忙");
		}
	});





}



function tableItemReload(con) {
	table.render({
		elem: '#demo2'
		,height: 400
		,limit:10
		,url: path+'check/reqCheckList' //数据接口
		// ,toolbar: '#barDemot' //开启头部工具栏，并为其绑定左侧模板
		,defaultToolbar: []
		,cols: [[ //表头
			// {type:'checkbox'}
			{field: 'pname', title: '项目名称', align:'center'}
			,{field: 'iname', title: '细项名称', align:'center'}
			,{field: 'dname', title: '科室', align:'center'}
			,{fixed: 'right', title:'操作', toolbar: '#tableBar2', width:150,align:'center'}
		]]
		,where: { //设定异步数据接口的额外参数，任意设
			condition:JSON.stringify(con)
		}
	});

	//监听行工具栏事件
	table.on('tool(test2)', function(obj){
		var data = obj.data;
		if(obj.event === 'print'){
			console.log(data);

			$.ajax({
				type:"POST",
				url:path+'check/barcode',
				dataType:"text",
				data:data,
				success:function (msg) {
					var res = JSON.parse(msg);
					if (res.code===1){
						layer.msg('打印成功');
						layer.msg(res.msg);
						$("#projectImage").attr('src',path+res.msg);
					} else {
						layer.msg('上传失败，请重试');
					}

				},
				error:function () {
					alert("服务器正忙");
				}
			});


		}
	});
}

function tableReload() {
	table.reload('demo', {
		url:path+'check/reqCheckByCard' //数据接口
		,where: { //设定异步数据接口的额外参数，任意设
			condition:JSON.stringify(cond)
		}
	}); //只重载数据
}

