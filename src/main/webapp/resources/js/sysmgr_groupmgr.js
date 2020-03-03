
var path;
var cond;
var table;

$(function () {

	path = $("#path").val();

	layui.use(['table','form','laydate'], function(){
		var form = layui.form;
		var laydate = layui.laydate;
		table = layui.table;
		table.render({
			elem: '#demo'
			,height: 300
			,limit:10
			,url: path+'sysmgr/reqGroupList' //数据接口
			,page: true //开启分页
			,cols: [[ //表头
				{field: 'gcid', title: '团检客户ID', width:150, sort: true, fixed: 'left',align:'center'}
				,{field: 'gcname', title: '客户名称', width:150,align:'center'}
				,{field: 'gcaccount', title: '账号', width:150,align:'center'}
				,{field: 'gcpass', title: '密码', width:150,align:'center'}
				,{field: 'gcphone', title: '联系电话', width:150,align:'center'}
				,{field: 'gcbalance', title: '账户余额', width:100,align:'center'}
				,{field: 'gcregisterdate', title: '注册时间', width:200,align:'center'}
				,{field: 'gcstate', title: '状态', width:80,align:'center'}
				,{fixed: 'right', title:'操作', toolbar: '#tableBar', width:150,align:'center'}
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

		laydate.render({
			elem: '#startdate'
		});
		laydate.render({
			elem: '#enddate'
		});

	});

});


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
					table.reload('demo', {
						url:path+'sysmgr/reqGroupList' //数据接口
						,where: { //设定异步数据接口的额外参数，任意设
							condition:JSON.stringify(cond)
						}
					}); //只重载数据
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