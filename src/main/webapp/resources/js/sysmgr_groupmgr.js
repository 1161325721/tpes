
var path;
var cond;
var table;
var form;
var layer;
var editLayer;
$(function () {

	path = $("#path").val();

	layui.use(['table','form','laydate'], function(){
		form = layui.form;
		var laydate = layui.laydate;
		table = layui.table;
		layer = layui.layer;
		table.render({
			elem: '#demo'
			,height: 300
			,limit:10
			,url: path+'sysmgr/reqGroupList' //数据接口
			,toolbar: '#barDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: []
			,page: true //开启分页
			,cols: [[ //表头
				{field: 'gcid', title: '团检客户ID', width:150, sort: true, fixed: 'left',align:'center'}
				,{field: 'gcname', title: '客户名称', width:150,align:'center'}
				,{field: 'gcaccount', title: '账号', width:150,align:'center'}
				,{field: 'gcpass', title: '密码', width:150,align:'center'}
				,{field: 'gcphone', title: '联系电话', width:150,align:'center'}
				,{field: 'gcbalance', title: '账户余额', width:100,align:'center'}
				,{field: 'gcregisterdate', title: '注册时间', width:200,align:'center',templet :function(d){
							return createTime(d.gcregisterdate);
						}}
				,{field: 'gcstate', title: '状态', width:80,align:'center',templet :function(d){
							return d.gcstate == 1?'正常':'禁用';
						}}
				,{fixed: 'right', title:'操作', toolbar: '#tableBar', width:200,align:'center'}
			]]
		});


		//监听行工具事件
		table.on('tool(test)', function(obj){
			var gdata = obj.data;
			console.log(obj);
			if(obj.event === 'ok'){
				groupEdit(gdata,1)
			}
			if(obj.event === 'ban'){
				groupEdit(gdata,2)
			}
			if(obj.event === 'del'){
				groupEdit(gdata,3)
			}
			if(obj.event === 'edit'){
				openEdit(gdata);
			}
		});

		//头工具栏事件
		table.on('toolbar(test)', function(obj){
			var checkStatus = table.checkStatus(obj.config.id);
			switch(obj.event){
				case 'add':
					openAdd();
					break;
			};
		});


		$("#search").click(function () {
			cond = form.val("condition");
			cond.gcname = "%"+cond.gcname+"%";
			alert(JSON.stringify(cond));
			table.reload('demo', {
				url:path+'sysmgr/reqGroupList' //数据接口
				,where: { //设定异步数据接口的额外参数，任意设
					condition:JSON.stringify(cond)
				}
				,page: {
					curr: 1 //重新从第 1 页开始
				}
			}); //只重载数据

		});

		form.on('submit(editSubmit)', function(data){
			edit(data);
			return false;
		});
		form.on('submit(addSubmit)', function(data){
			add(data);
			return false;
		});

		laydate.render({
			elem: '#startdate'
		});
		laydate.render({
			elem: '#enddate'
		});

	});

});


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
		url:path+'sysmgr/addGroup',
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
			url:path+'sysmgr/editGroup',
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


function groupEdit(data,gcstate) {

	layer.confirm('操作确认', function(index){
		var sendData = {
			gcid:''+data.gcid,
			gcstate:''+gcstate
		};
		$.ajax({
			type:"POST",
			url:path+'sysmgr/editGroup',
			dataType:"text",
			data:{
				condition:JSON.stringify(sendData)
			},
			success:function (msg) {
				if (msg==="true"){
					layer.msg("操作成功");
					tableReload();
					// table.reload('demo', {
					// 	url:path+'sysmgr/reqGroupList' //数据接口
					// 	,where: { //设定异步数据接口的额外参数，任意设
					// 		condition:JSON.stringify(cond)
					// 	}
					// }); //只重载数据
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
		url:path+'sysmgr/reqGroupList' //数据接口
		,where: { //设定异步数据接口的额外参数，任意设
			condition:JSON.stringify(cond)
		}
	}); //只重载数据
}
