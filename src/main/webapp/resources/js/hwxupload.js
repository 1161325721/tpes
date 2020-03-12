//普通图片上传

layui.use(['upload','layer','jquery'], function(){
	var upload = layui.upload;
	var $ = layui.jquery;
	var layer  = layui.layer;
	//执行实例
	var uploadInst = upload.render({
		elem: '#uploadbtn'
		, url: '/tpes/projectPictureUpload'
		,method:'post'
		,accept:'images'
		,acceptMime:'image/*'
		,field:'projectImg'
		,choose: function (obj) {
			//预读本地文件示例，不支持ie8
			obj.preview(function (index, file, result) {
			    //图片预览
			    $('#demo1').attr('src', result); //图片链接（base64）
			});
		}
		,done: function (msg) {
			//如果上传失败
			// var res =JSON.parse(msg);
			var res = msg;
			// layer.msg(res);
			// layer.msg(res.code);
			// alert(res);
			// alert(res.code);
			// console.log(msg);
			if (res.code==205) {
				return layer.msg('上传项目图片失败',{icon: 5});
			}else if (res.code ==0) {
				render2();
				return layer.msg('上传图片成功');
			}
			//上传成功,获得图片地址
			// $('#demo1').attr('src', res.data);
		}
		,error: function () {
			alert("上传失败");
			// //演示失败状态，并实现重传
			// var demoText = $('#demoText');
			// demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
			// demoText.find('.demo-reload').on('click', function () {
			// 	uploadInst.upload();
			// });
		}
	});
});

