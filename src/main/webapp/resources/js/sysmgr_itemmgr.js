
var path;
var cond;
var table;
var form;
var layer;
var editLayer;
var addLayer;

$(function () {

	path = $("#path").val();

	layui.use(['table','form','layer'], function(){
		form = layui.form;
		table = layui.table;
		layer = layui.layer;
		table.render({
			elem: '#demo'
			,height: 400
			,limit:10
			,url: path+'sysmgr/reqItemList' //数据接口
			,toolbar: '#barDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: []
			,page: true //开启分页
			,cols: [[ //表头
				{type:'checkbox'}
				,{field: 'iid', title: '细项ID', width:150, sort: true,align:'center'}
				,{field: 'iname', title: '细项名称', width:150,align:'center'}
				,{field: 'iunit', title: '计量单位', width:150,align:'center'}
				,{field: 'imax', title: '上限值', width:150,align:'center'}
				,{field: 'imin', title: '下限值', width:150,align:'center'}
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
						list.push(data[i].iid);
					}
					del(list);
					break;
				case 'addItem':
					openAddDiv();
					break;
			};
		});

		//监听行工具栏事件
		table.on('tool(test)', function(obj){
			var data = obj.data;
			console.log(obj);
			if(obj.event === 'edit'){
				edit(data);
			}
			if(obj.event === 'del'){

				var list = new Array();
				list.push(data.iid);
				del(list);
			}
		});

		$("#search").click(function () {
			cond = form.val("condition");
			cond.iname = "%"+cond.iname+"%";
			alert(JSON.stringify(cond));
			tableReload();

		});

		$("#editClose").click(function () {
			layer.close(editLayer);
		});

		$("#addClose").click(function () {
			layer.close(addLayer);
		});

		form.on('submit(editSubmit)', function(data){

			layer.confirm('确认提交', function(index) {
				layer.msg(JSON.stringify(data.field));
				var idata = JSON.stringify(data.field);
				var sendData = {
					condition:idata
				};
				$.ajax({
					type:"POST",
					url:path+'sysmgr/editItem',
					dataType:"text",
					data:sendData,
					success:function (msg) {
						if (msg=='true'){
							layer.close(editLayer);
							tableReload();
							layer.msg("编辑成功");
						}else {
							layer.msg("编辑失败");
						}
					},
					error:function () {
						alert("服务器正忙");
					}
				});
			});

			return false;
		});

		form.on('submit(addSubmit)', function(data){

			layer.confirm('确认提交', function(index) {
				add(data);
			});

			return false;
		});

	});

});

function openAddDiv() {
	addLayer=layer.open({
		type:1,
		title:"细项编辑",
		content:$("#addDiv"),
		area:['400px','auto'],
		success:function (index) {
			$("#addForm")[0].reset();
		}
	});
}

function add(data) {

	var idata = JSON.stringify(data.field);
	var sendData = {
		condition:idata
	};

	$.ajax({
		type:"POST",
		url:path+'sysmgr/addItem',
		dataType:"text",
		data:sendData,
		success:function (msg) {
			if (msg==='true'){
				tableReload();
				layer.msg('添加成功');
				layer.close(addLayer);
			} else{
				layer.msg('添加失败');
			}
		},
		error:function () {
			alert("服务器正忙");
		}
	});

}

function edit(data) {
	console.log(data);
	editLayer=layer.open({
		type:1,
		title:"细项编辑",
		content:$("#editDiv"),
		area:['400px','auto'],
		success:function (index) {
			form.val("editForm",data);
		}
	});

}


function del(list) {

	layer.confirm('操作确认', function(index){
		var sendData = {
			delList:list
		};
		$.ajax({
			type:"POST",
			url:path+'sysmgr/delItem',
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
		url:path+'sysmgr/reqItemList' //数据接口
		,where: { //设定异步数据接口的额外参数，任意设
			condition:JSON.stringify(cond)
		}
	}); //只重载数据
}