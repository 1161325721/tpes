var nameArr = [];
var valueArr = [];
var obArr = [];

$(function() {

	// myajax("DataServlet?methodName=doDraw", {}, function (msg) {
	// 	alert(msg);
	// 	var arr = JSON.parse(msg);
	// 	for (var i = 0; i < arr.length; i++) {
	// 		// 普通柱状图使用的数据
	// 		nameArr.push(arr[i].name);
	// 		valueArr.push(arr[i].record);
	// 		// 南丁格尔玫瑰圆饼图使用的数据
	// 		obArr.push({
	// 			value : arr[i].record,
	// 			name : arr[i].name
	// 		});
	// 	}
	// 	createEchars();// 创建普通柱状图
	// 	rose();// 创建南丁格尔玫瑰圆饼图
	// });

	//
	// $.ajax({
	// 	method : "POST",
	// 	url : "DataServlet?methodName=doDraw",
	// 	dataType : "text",
	// 	success : function(msg) {
	// 		var arr = JSON.parse(msg);
	//
	// 		for (var i = 0; i < arr.length; i++) {
	// 			// 普通柱状图使用的数据
	// 			nameArr.push(arr[i].name);
	// 			valueArr.push(arr[i].record);
	// 			// 南丁格尔玫瑰圆饼图使用的数据
	// 			obArr.push({
	// 				value : arr[i].record,
	// 				name : arr[i].name
	// 			});
	//
	// 		}
	// 		createEchars();// 创建普通柱状图
	// 		rose();// 创建南丁格尔玫瑰圆饼图
	// 	},
	// 	error : function() {
	// 		alert("服务器正忙");
	// 	}
	// });



});
//普通柱状图
function createEchars() {

	//基于准备好的dom，初始化echarts实例
	var myChart = echarts.init(document.getElementById('echarts_div'),'light');//dark为暗黑主题 不要可以去掉

	// 指定图表的配置项和数据
	var option = {
		title : {
			text : '接收量最大月份'
		},
		tooltip : {},
		legend : {
			data : [ '柱状数据表' ]
		},
		xAxis : {
			data : nameArr
		},
		yAxis : {},
		series : [ {
			name : '数据',
			type : 'bar',
			data : valueArr,
			itemStyle: {        //上方显示数值
				normal: {
					label: {
						show: true, //开启显示
						position: 'top', //在上方显示
						textStyle: { //数值样式
							color: 'green',
							fontSize: 16
						}
					}
				}
			}
		} ],


	};

	// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);

}
//南丁格尔玫瑰图
function rose() {

	//基于准备好的dom，初始化echarts实例
	var myChart2 = echarts.init(document.getElementById('echarts_div2'),"light");//dark为暗黑主题 不要可以去掉

	var option = {
		title : {
			text : '渠道量统计玫瑰图'
		},
		series : [ {
			name : '访问来源',
			type : 'pie',
			roseType: 'angle',//南丁格尔玫瑰图样式  去掉则显示基本圆饼图
			radius : '55%',
			data : obArr
		} ]
	};


	myChart2.setOption(option);
}


function btnsearch1() {




	//发送ajax
	$.ajax({
		type:"post",
		url: "/tpes/maxMonth",
		dataType: "text",
		//发送的数据（同时也将数据发送出去）
		data: {},
		success: function (msg) {
			// var res = msg.toString();
			// layer.msg(msg);
			var arr = JSON.parse(msg);
			//清空
			nameArr=[];
			valueArr=[];
			obArr=[];

			var count=Number(0) ;
			for (var i = 0; i < arr.length; i++) {
				// 普通柱状图使用的数据
				nameArr.push(arr[i].name);
				valueArr.push(arr[i].record);
				// 南丁格尔玫瑰圆饼图使用的数据
				count=count+Number(arr[i].record);
				obArr.push({
					value : arr[i].record,
					name : arr[i].name
				});
			}
			createEchars();// 创建普通柱状图
		},
		error: function (msg) {
			alert("服务器正忙。。。。"+msg);
		}
	});




}




