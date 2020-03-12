
var path;
var cond;
var table;
var form;
var layer;
var editLayer;
var itemEditLayer;
var transfer;
var departmentList;

$(function () {

	path = $("#path").val();

	layui.use(['table','form','layer','transfer'], function(){
		form = layui.form;
		table = layui.table;
		layer = layui.layer;
		transfer = layui.transfer;
		table.render({
			elem: '#demo'
			,height: 400
			,limit:10
			,url: path+'sysmgr/reqProjectList' //数据接口
			,toolbar: '#barDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: []
			,page: true //开启分页
			,cols: [[ //表头
				{type:'checkbox'}
				,{field: 'proid', title: '项目ID', width:150, sort: true,align:'center'}
				,{field: 'pname', title: '项目名称',align:'center'}
				,{field: 'pcharge', title: '项目价格', align:'center'}
				,{field: 'pgender', title: '适用性别', align:'center'}
				,{field: 'did', title: '科室', align:'center'}
				,{fixed: 'right', title:'操作', toolbar: '#tableBar', align:'center'}
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
						list.push(data[i].proid);
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
			console.log(obj);
			if(obj.event === 'edit'){
				openEdit(data);
			}
			if(obj.event === 'del'){

				var list = new Array();
				list.push(data.proid);
				del(list);
			}
			if(obj.event === 'itemEdit'){
				openItemEditDiv(data);
			}
		});

		$("#search").click(function () {
			cond = form.val("condition");
			cond.pname = "%"+cond.pname+"%";
			alert(JSON.stringify(cond));
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

		//保存细项分配
		form.on('submit(itemSave)', function(data){
			editProjectItem();
			return false;
		});
		//关闭细项分配界面
		$("#itemDivClose").click(function () {
			layer.close(itemEditLayer);
		});

	});

	//获取科室列表
	reqDepartmentList();

});



function editProjectItem() {
	var getData = transfer.getData('itemTransfer'); //获取右侧数据

	var list = new Array();
	for (var i = 0; i < getData.length; i++) {
		list.push(getData[i].value);
	}
	console.log(list);

	var sendData = {
		list:list,
		proid:form.val("editItemForm").proid
	};
	//获取
	$.ajax({
		type:"POST",
		url:path+'sysmgr/editProjectItem',
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

//打开细项分配div
function openItemEditDiv(data) {

	form.val("editItemForm",data);
	var allItemList;
	var projectItemList = new Array();

	//获取细项总表
	$.ajax({
		type:"POST",
		url:path+'sysmgr/reqAllItemList',
		dataType:"text",
		data:{},
		success:function (msg) {
			allItemList = JSON.parse(msg).data;


			//获取
			$.ajax({
				type:"POST",
				url:path+'sysmgr/reqProjectItemList',
				dataType:"text",
				data:{
					condition:JSON.stringify(data)
				},
				success:function (msg) {
					var obj = JSON.parse(msg);

					for (var i = 0; i < obj.length; i++) {
						projectItemList.push(obj[i].iid);
					}

					console.log(allItemList);
					console.log(projectItemList);
					itemEditLayer=layer.open({
						type:1,
						title:"细项编辑",
						content:$("#editItemDiv"),
						area:['500px','600px'],
						success:function (index) {
							initTransfer(allItemList,projectItemList)
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

//细项分配穿梭框
function initTransfer(data,value) {
	transfer.render({
		elem: '#itemTransfer'
		,id:'itemTransfer'
		,parseData: function(res){
			return {
				"value": res.iid //数据值
				,"title": res.iname //数据标题
				,"disabled": res.disabled  //是否禁用
				,"checked": res.checked //是否选中
			}
		}
		,data:data
		,value:value
		,title: ['细项列表', '已分配']
		,showSearch: true
		// ,width: 150 //定义宽度
		,height: 400 //定义高度
	});

}


function openAdd() {
	$("#editSubmit").hide();
	$("#pidDiv").hide();
	$("#addSubmit").show();
	$("#editForm")[0].reset();
	openDiv();
}

function openEdit(data) {
	$("#editSubmit").show();
	$("#pidDiv").show();
	$("#addSubmit").hide();
	form.val("editForm",data);
	openDiv();
}

function openDiv() {
	editLayer=layer.open({
		type:1,
		title:"细项编辑",
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
		url:path+'sysmgr/addProject',
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
			url:path+'sysmgr/editProject',
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
			url:path+'sysmgr/delProject',
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
		url:path+'sysmgr/reqProjectList' //数据接口
		,where: { //设定异步数据接口的额外参数，任意设
			condition:JSON.stringify(cond)
		}
	}); //只重载数据
}


//获取科室列表
function reqDepartmentList() {
	$.ajax({
		type:"POST",
		url:path+'sysmgr/reqAllDepartmentList',
		dataType:"text",
		data:{},
		success:function (msg) {
			departmentList = JSON.parse(msg);
			for (var i = 0; i < departmentList.length; i++) {
				$("#departmentSelect").append(new Option(departmentList[i].dname,departmentList[i].did));
			}
		},
		error:function () {
			alert("服务器正忙");
		}
	});
}