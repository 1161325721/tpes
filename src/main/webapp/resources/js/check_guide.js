
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
			// ,url: path+'check/reqCheckByCard' //数据接口
			// ,toolbar: '#barDemo' //开启头部工具栏，并为其绑定左侧模板
			,data:[]
			,defaultToolbar: []
			,page: true //开启分页
			,cols: [[ //表头
				// {type:'checkbox'}
				{field: 'gcid', title: '导检单ID',  sort: true,align:'center'}
				,{field: 'gcdate', title: '时间', align:'center'}
				,{fixed: 'right', title:'操作', toolbar: '#tableBar', align:'center'}
			]]
		});

		//监听行工具栏事件
		table.on('tool(test)', function(obj){
			var data = obj.data;
			// console.log(obj);
			if(obj.event === 'look'){
				var url = path+"check/checkPage?gcid="+data.gcid;
				window.open(url);
			}
			if(obj.event === 'print'){
				openDiv(data);
			}
		});

		$("#search").click(function () {
			cond = form.val("condition");
			tableReload();
		});

		$("#print").click(function () {
			$("#print").hide();
			$("#page").print();
			$("#print").show();
		});

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
					title:"条码打印",
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
						// layer.msg('打印成功');


						layer.msg(res.msg);
						$("#projectImage").attr('src',path+res.msg);

						layer.open({
							type:1,
							title:"条码打印",
							content:$("#code"),
							area:['400px','auto'],
							success:function (index) {
							}
						});

					} else {
						layer.msg('获取条码失败，请重试');
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

