
var path;
var cond;
var table;
var form;
var layer;
var editLayer;
var transfer;
var departmentList;
var tree;
var uploadPage;
var upload;
var faceid;

$(function () {

	path = $("#path").val();

	layui.use(['table','form','layer','transfer','tree','upload'], function(){
		form = layui.form;
		table = layui.table;
		layer = layui.layer;
		tree = layui.tree;
		transfer = layui.transfer;
		upload = layui.upload;
		table.render({
			elem: '#demo'
			,height: 400
			,limit:10
			,url: path+'sysmgr/reqWorkerList' //数据接口
			,toolbar: '#barDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: []
			,page: true //开启分页
			,cols: [[ //表头
				{type:'checkbox'}
				,{field: 'wid', title: '人员ID',  sort: true,align:'center'}
				,{field: 'waccount', title: '账号', align:'center'}
				,{field: 'wpass', title: '密码', align:'center'}
				,{field: 'wname', title: '姓名', align:'center'}
				,{field: 'rid', title: '角色',align:'center'}
				,{field: 'did', title: '科室', align:'center'}
				,{field: 'wphone', title: '联系电话', align:'center'}
				,{field: 'wstate', title: '状态', align:'center'}
				,{field: 'wtime', title: '注册时间', align:'center',templet :function(d){
						return createTime(d.wtime);
					}}
				,{fixed: 'right', title:'操作', toolbar: '#tableBar', width:250,align:'center'}
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
				list.push(data.wid);
				del(list);
			}
			if(obj.event === 'ok'){
				groupEdit(data,1);
			}
			if(obj.event === 'face'){
				faceid = data.wid;
				openUpload();
			}
			if(obj.event === 'ban'){
				groupEdit(data,2);
			}
		});

		$("#search").click(function () {
			cond = form.val("condition");
			cond.wname = "%"+cond.wname+"%";
			cond.waccount = "%"+cond.waccount+"%";
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

	});


	reqDepartmentList();
	reqRoleList();

});


function openUpload() {

	uploadLay=layer.open({
		type:1,
		title:"人脸设置",
		content:$("#uploadDiv"),
		area:['400px','auto'],
		success:function (index) {
			initUpload();
		}
	});

}

//图片上传
function initUpload() {
	console.log(uploadPage);
	if (uploadPage != undefined){
		return;
	}

	uploadPage = upload.render({
		elem: '#setImage'
		,url: path+'front/faceEdit' //改成您自己的上传接口
		,auto: false
		,size: 1024 //限制文件大小，单位 KB
		//,multiple: true
		,bindAction: '#toUpload'
		,before: function(obj){
			$("#toUpload").attr('disabled',true);

			this.data = {
				wid:form
			};
		}
		,done: function(res){
			// var obj = JSON.parse(res);
			if (res.code===1){
				layer.msg('设置成功');
			} else {
				layer.msg(res.msg+'设置失败!');
			}
			$("#toUpload").attr('disabled',false);

		}
	});
}



function groupEdit(data,wstate) {

	layer.confirm('操作确认', function(index){
		var sendData = {
			wid:''+data.wid,
			wstate:''+wstate
		};
		$.ajax({
			type:"POST",
			url:path+'sysmgr/editWorker',
			dataType:"text",
			data:{
				condition:JSON.stringify(sendData)
			},
			success:function (msg) {
				if (msg==="true"){
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
		title:"人员编辑",
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
		url:path+'sysmgr/addWorker',
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
			url:path+'sysmgr/editWorker',
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
			url:path+'sysmgr/delWorker',
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
		url:path+'sysmgr/reqWorkerList' //数据接口
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

//获取角色列表
function reqRoleList() {
	$.ajax({
		type:"POST",
		url:path+'sysmgr/reqAllRoleList',
		dataType:"text",
		data:{},
		success:function (msg) {
			list = JSON.parse(msg).data;
			for (var i = 0; i < list.length; i++) {
				$("#roleSelect").append(new Option(list[i].rname,list[i].rid));
			}
		},
		error:function () {
			alert("服务器正忙");
		}
	});
}

function createTime(v){

	if (v===undefined){
		return "无数据";
	}

	var date = new Date(v);
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	m = m<10?'0'+m:m;
	var d = date.getDate();
	d = d<10?("0"+d):d;
	var h = date.getHours();
	h = h<10?("0"+h):h;
	var M = date.getMinutes();
	M = M<10?("0"+M):M;
	var s = date.getSeconds();
	s = s<10?("0"+s):s;
	var str = y+"-"+m+"-"+d+" "+h+":"+M+":"+s;
	return str;
}