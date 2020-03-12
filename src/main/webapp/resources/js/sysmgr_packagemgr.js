
var path;
var cond;
var table;
var form;
var layer;
var upload;
var editLayer;
var projectEditLayer;
var transfer;
var uploadLay;
var uploadPage;

$(function () {

	path = $("#path").val();

	layui.use(['table','form','layer','transfer','upload'], function(){
		form = layui.form;
		table = layui.table;
		layer = layui.layer;
		transfer = layui.transfer;
		upload = layui.upload;
		table.render({
			elem: '#demo'
			,height: 400
			,limit:10
			,url: path+'sysmgr/reqPackageList' //数据接口
			,toolbar: '#barDemo' //开启头部工具栏，并为其绑定左侧模板
			,defaultToolbar: []
			,page: true //开启分页
			,cols: [[ //表头
				{type:'checkbox'}
				,{field: 'packid', title: '套餐ID', width:150, sort: true,align:'center'}
				,{field: 'packname', title: '套餐名称', width:150,align:'center'}
				,{field: 'packintroduction', title: '套餐简介', align:'center'}
				,{field: 'packdiscount', title: '套餐折扣', width:150,align:'center'}
				,{field: 'imageurl', title: '套餐图片', align:'center'}
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
						list.push(data[i].packid);
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
				list.push(data.packid);
				del(list);
			}
			if(obj.event === 'projectEdit'){
				openProjectEditDiv(data);
			}
		});

		$("#search").click(function () {
			cond = form.val("condition");
			cond.packname = "%"+cond.packname+"%";
			alert(JSON.stringify(cond));
			tableReload();

		});

		$("#divClose").click(function () {
			layer.close(editLayer);
		});

		$("#editImage").click(function () {
			openUpload();
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
		form.on('submit(projectSave)', function(data){
			editPackageProject();
			return false;
		});
		//关闭细项分配界面
		$("#projectDivClose").click(function () {
			layer.close(projectEditLayer);
		});

	});






});



function openUpload() {

	uploadLay=layer.open({
		type:1,
		title:"套餐编辑",
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
		,url: path+'sysmgr/imageUpload' //改成您自己的上传接口
		,auto: false
		,size: 1024 //限制文件大小，单位 KB
		//,multiple: true
		,bindAction: '#toUpload'
		,done: function(res){
			// var obj = JSON.parse(res);
			if (res.code===1){
				layer.msg('上传成功');
				layer.msg(res.msg);
				layer.close(uploadLay);
				$("#imageurl").val(res.msg);
				$("#projectImage").attr('src',path+res.msg);
			} else {
				layer.msg('上传失败，请重试');
			}
		}
	});
}


function editPackageProject() {
	var getData = transfer.getData('projectTransfer'); //获取右侧数据

	var list = new Array();
	for (var i = 0; i < getData.length; i++) {
		list.push(getData[i].value);
	}
	console.log(list);

	var sendData = {
		list:list,
		packid:form.val("editProjectForm").packid
	};
	//获取
	$.ajax({
		type:"POST",
		url:path+'sysmgr/editPackageProject',
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
function openProjectEditDiv(data) {

	form.val("editProjectForm",data);
	var allProjectList;
	var rightList = new Array();

	//获取细项总表
	$.ajax({
		type:"POST",
		url:path+'sysmgr/reqAllProjectList',
		dataType:"text",
		data:{},
		success:function (msg) {
			allProjectList = JSON.parse(msg).data;


			//获取
			$.ajax({
				type:"POST",
				url:path+'sysmgr/reqPackageProjectList',
				dataType:"text",
				data:{
					condition:JSON.stringify(data)
				},
				success:function (msg) {
					var obj = JSON.parse(msg);

					for (var i = 0; i < obj.length; i++) {
						rightList.push(obj[i].proid);
					}

					projectEditLayer=layer.open({
						type:1,
						title:"套餐项目编辑",
						content:$("#editProjectDiv"),
						area:['500px','600px'],
						success:function (index) {
							initTransfer(allProjectList,rightList)
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

//项目分配穿梭框
function initTransfer(data,value) {
	transfer.render({
		elem: '#projectTransfer'
		,id:'projectTransfer'
		,parseData: function(res){
			return {
				"value": res.proid //数据值
				,"title": res.pname //数据标题
				,"disabled": res.disabled  //是否禁用
				,"checked": res.checked //是否选中
			}
		}
		,data:data
		,value:value
		,title: ['项目列表', '已分配']
		,showSearch: true
		// ,width: 150 //定义宽度
		,height: 400 //定义高度
	});

}


function openAdd() {
	$("#editSubmit").hide();
	$("#packid").hide();
	$("#addSubmit").show();
	$("#editForm")[0].reset();
	openDiv();
}

function openEdit(data) {
	$("#editSubmit").show();
	$("#packid").show();
	$("#addSubmit").hide();
	form.val("editForm",data);
	openDiv();
}

function openDiv() {

	$("#projectImage").attr('src',path+form.val("editForm").imageurl);
	editLayer=layer.open({
		type:1,
		title:"套餐编辑",
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
		url:path+'sysmgr/addPackage',
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
			url:path+'sysmgr/editPackage',
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
			url:path+'sysmgr/delPackage',
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
		url:path+'sysmgr/reqPackageList' //数据接口
		,where: { //设定异步数据接口的额外参数，任意设
			condition:JSON.stringify(cond)
		}
	}); //只重载数据
}

