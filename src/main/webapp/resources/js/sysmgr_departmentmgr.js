
var path;
var cond;
var table;
var form;
var layer;
var editLayer;
var transfer;
var departmentList;
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
			,url: path+'sysmgr/reqDepartmentList' //数据接口
			,toolbar: '#barDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: []
			,page: true //开启分页
			,cols: [[ //表头
				{type:'checkbox'}
				,{field: 'did', title: '科室ID', width:120, sort: true,align:'center'}
				,{field: 'dname', title: '科室名称', width:120,align:'center'}
				,{field: 'ddescription', title: '科室描述', width:120,align:'center'}
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
				case 'add':
					openAdd();
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

		$("#divClose").click(function () {
			layer.close(editLayer);
		});

		form.on('submit(editSubmit)', function(data){
			edit(data);
			return false;
		});
		form.on('submit(addSubmit)', function(data){
			add(data);
			return false;
		});

	});


});


function openAdd() {
	$("#editSubmit").hide();
	$("#idDiv").hide();
	$("#addSubmit").show();
	$("#editForm")[0].reset();
	openDiv();
}

function openEdit(data) {
	$("#editSubmit").show();
	$("#idDiv").show();
	$("#addSubmit").hide();
	$("#editForm")[0].reset();
	form.val("editForm",data);
	openDiv();
}

function openDiv() {
	editLayer=layer.open({
		type:1,
		title:"参数编辑",
		content:$("#editDiv"),
		area:['400px','auto'],
		success:function (index) {
		}
	});
}

function add(data) {
	data.wstate='1';
	var sendData = {
		condition:JSON.stringify(data.field)
	};

	$.ajax({
		type:"POST",
		url:path+'sysmgr/addDepartment',
		dataType:"text",
		data:sendData,
		success:function (msg) {
			if (msg==='true'){
				tableReload();
				layer.msg('添加成功');
				layer.close(editLayer);
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
	layer.confirm('确认提交', function(index) {
		layer.msg(JSON.stringify(data.field));
		var sendData = {
			condition:JSON.stringify(data.field)
		};
		$.ajax({
			type:"POST",
			url:path+'sysmgr/editDepartment',
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
}


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
		url:path+'sysmgr/reqDepartmentList' //数据接口
		,where: { //设定异步数据接口的额外参数，任意设
			condition:JSON.stringify(cond)
		}
	}); //只重载数据
}

