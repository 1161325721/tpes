
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
			,url: path+'sysmgr/reqLogList' //数据接口
			,toolbar: '#barDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: []
			,page: true //开启分页
			,cols: [[ //表头
				{type:'checkbox'}
				,{field: 'lid', title: '科室ID', width:120, sort: true,align:'center'}
				,{field: 'wid', title: '工作员ID', width:120,align:'center'}
				,{field: 'ltype', title: '操作类型', width:120,align:'center'}
				,{field: 'ltime', title: '操作时间', width:120,align:'center'}
				,{field: 'lstate', title: '状态', width:120,align:'center'}
				,{field: 'lmessage', title: '信息', width:120,align:'center'}
				,{fixed: 'right', title:'操作', toolbar: '#tableBar', width:150,align:'center'}
			]]
		});

		//头工具栏事件
		table.on('toolbar(test)', function(obj){
			var checkStatus = table.checkStatus(obj.config.id);
			switch(obj.event){
				case 'delList':
					var data = checkStatus.data;
					if (data.length===0){
						break;
					}
					var list = new Array();
					for (var i = 0; i < data.length; i++) {
						list.push(data[i].did);
					}
					del(list);
					break;
			};
		});

		//监听行工具栏事件
		table.on('tool(test)', function(obj){
			var data = obj.data;
			// console.log(obj);
			if(obj.event === 'edit'){
				openEdit(data);
			}
			if(obj.event === 'del'){
				var list = new Array();
				list.push(data.did);
				del(list);
			}
		});

		$("#search").click(function () {
			cond = form.val("condition");
			tableReload();
		});

		laydate.render({
			elem: '#startdate'
		});
		laydate.render({
			elem: '#enddate'
		});

	});


});




function del(list) {

	layer.confirm('操作确认', function(index){
		var sendData = {
			delList:list
		};
		$.ajax({
			type:"POST",
			url:path+'sysmgr/delDepartment',
			dataType:"text",
			data:{
				condition:JSON.stringify(sendData)
			},
			success:function (msg) {
				if (msg!=undefined){
					layer.msg("操作成功");
					tableReload();
					layer.close(index);
				} else {
					layer.msg("操作失败");
				}
			},
			error:function () {
				alert("服务器正忙");
			}
		});
	});
}

function tableReload() {
	table.reload('demo', {
		url:path+'sysmgr/reqLogList' //数据接口
		,where: { //设定异步数据接口的额外参数，任意设
			condition:JSON.stringify(cond)
		}
	}); //只重载数据
}

