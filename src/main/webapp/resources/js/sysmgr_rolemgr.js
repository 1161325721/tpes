
var path;
var cond;
var table;
var form;
var layer;
var editLayer;
var roleMenuLayer;
var transfer;
var departmentList;
var tree;
var saveList;

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
			,url: path+'sysmgr/reqRoleList' //数据接口
			,toolbar: '#barDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: []
			,page: true //开启分页
			,cols: [[ //表头
				{type:'checkbox'}
				,{field: 'rid', title: '角色ID', width:150, sort: true,align:'center'}
				,{field: 'rname', title: '角色名称', width:150,align:'center'}
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
						list.push(data[i].rid);
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
				list.push(data.rid);
				del(list);
			}
			if(obj.event === 'roleMenuEdit'){
				openRoleMenuDiv(data);
			}
		});

		$("#search").click(function () {
			cond = form.val("condition");
			cond.rname = "%"+cond.rname+"%";
			alert(JSON.stringify(cond));
			tableReload();

		});

		$("#divClose").click(function () {
			layer.close(editLayer);
		});

		$("#roleMenuSave").click(function () {
			var checkedData = tree.getChecked('roleMenuTree');
			console.log(checkedData);
			roleMenuSave(checkedData);
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

function roleMenuSave(data) {
	saveList = new Array();
	save(data);
	console.log(saveList);
	console.log(form.val("editRoleMenuForm").rid);

	var sendData = {
		list:saveList,
		rid:form.val("editRoleMenuForm").rid
	};
	//获取
	$.ajax({
		type:"POST",
		url:path+'sysmgr/editRoleMenu',
		dataType:"text",
		data:{
			condition:JSON.stringify(sendData)
		},
		success:function (msg) {
			if (msg==="true"){
				layer.msg('分配成功');
			}else{
				layer.msg('分配失败');
			}
		},
		error:function () {
			alert("服务器正忙");
		}
	});



}


function save(data) {


	for (var i = 0; i < data.length; i++) {

		if (data[i].children.length>0){
			save(data[i].children);
		}else {
			saveList.push(data[i].id);
		}

	}

}


function openRoleMenuDiv(data) {

	form.val("editRoleMenuForm",data);
	var allMenuList;
	var roleMenuList = new Array();

	//获取所有菜单总表
	$.ajax({
		type:"POST",
		url:path+'sysmgr/reqAllMenuList',
		dataType:"text",
		data:{},
		success:function (msg) {
			var list = JSON.parse(msg).data;


			//获取
			$.ajax({
				type:"POST",
				url:path+'sysmgr/reqRoleMenu',
				dataType:"text",
				data:{
					condition:JSON.stringify(data)
				},
				success:function (msg) {
					var obj = JSON.parse(msg);

					for (var i = 0; i < obj.length; i++) {
						roleMenuList.push(obj[i].mid);
					}

					allMenuList = arrayToTree(list,0,roleMenuList);

					roleMenuLayer=layer.open({
						type:1,
						title:"角色菜单编辑",
						content:$("#roleMenuDiv"),
						area:['500px','600px'],
						success:function (index) {
							initTree(allMenuList,roleMenuList);
						}
					});

				},
				error:function () {
					alert("服务器正忙");
				}
			});



		},
		error:function () {
			alert("服务器正忙");
		}
	});
}


function initTree(data,list) {

	tree.render({
		elem: '#treeDiv'
		,data: data
		,showCheckbox: true  //是否显示复选框
		,id: 'roleMenuTree'
	});

	// console.log(data);
	// console.log(list);
	// tree.setChecked('treeDiv',1);
}



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
		title:"角色编辑",
		content:$("#editDiv"),
		area:['400px','auto'],
		success:function (index) {
		}
	});
}

function add(data) {
	var sendData = {
		condition:JSON.stringify(data.field)
	};

	$.ajax({
		type:"POST",
		url:path+'sysmgr/addRole',
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
			url:path+'sysmgr/editRole',
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
			url:path+'sysmgr/delRole',
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
		url:path+'sysmgr/reqRoleList' //数据接口
		,where: { //设定异步数据接口的额外参数，任意设
			condition:JSON.stringify(cond)
		}
	}); //只重载数据
}


//获取菜单列表
function reqMenuList(mid) {
	$.ajax({
		type:"POST",
		url:path+'sysmgr/reqAllMenuList',
		dataType:"text",
		data:{},
		success:function (msg) {
			departmentList = JSON.parse(msg).data;
			$("#menuSelect").empty();
			$("#menuSelect").append(new Option('无','0'));
			for (var i = 0; i < departmentList.length; i++) {
				var id = departmentList[i].mid+'';
				var mname = departmentList[i].mname;

				if (id===mid){
					continue;
				}

				$("#menuSelect").append(new Option(mname,id));
			}
			form.render('select');
			openDiv();
		},
		error:function () {
			alert("服务器正忙");
		}
	});
}


function arrayToTree(arr, parentId,checkedlist) {

	var list = new Array();

	for (var i = 0; i < arr.length; i++) {

		if (arr[i].mparentid===parentId){

			var chick = false;
			if (checkedlist.indexOf(arr[i].mid)!==-1){
				chick = true;
			}

			list.push({
				id:arr[i].mid,
				title: arr[i].mname,
				children:arrayToTree(arr,arr[i].mid,checkedlist),
				checked:chick
			});
		}
	}

	return list;

}
