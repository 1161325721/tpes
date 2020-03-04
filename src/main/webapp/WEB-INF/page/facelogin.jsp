<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>人脸登录</title>

	<link rel="stylesheet" type="text/css" href=<%=path+"/resources/facelogin/css/style.css"%>>
	<link rel="stylesheet" type="text/css" href=<%=path+"/resources/facelogin/css/mdialog.css"%>>
	<script src=<%=path+"/resources/jquery/jquery-3.4.1.js"%>></script>
	<script type="text/javascript" src=<%=path+"/resources/facelogin/js/zepto.min.js"%>></script>
	<script type="text/javascript" src=<%=path+"/resources/facelogin/js/mdialog.js"%>></script>
<%--	<script type="text/javascript" src="js/mdialog.js"></script>--%>

	<style>
		body {
			height: 100%;
			background: #213838;
			overflow: hidden;
		}

		canvas {
			z-index: -1;
			position: absolute;
		}

	</style>


	<style type="text/css">
		* {
			margin: 0;
			padding: 0;
		}

		body {
			height: 100vh;
			background-position: center;
			overflow: hidden;
		}

		h1 {
			color: #fff;
			text-align: center;
			font-weight: 100;
			margin-top: 40px;
		}

		#media {
			width: 280px;
			height: 250px;
			margin: 10px auto 0;
			border-radius: 30px;
			overflow: hidden;
			opacity: 0.7px;
		}

		#video {

		}

		#canvas {
			display: none;
		}

		#btn {
			width: 160px;
			height: 50px;
			background: #03a9f4;
			margin: 70px auto 0;
			text-align: center;
			line-height: 50px;
			color: #fff;
			border-radius: 40px;
		}
	</style>
</head>

<body>

<form action="${pageContext.request.contextPath}/facelogin.action" method="get">
	<dl class="admin_login">
		<dt>
			<strong>人脸识别登录管理系统</strong><em>开启摄像头，调试角度</em> <strong>请把你的脸放摄像头面前</strong>
		</dt>

		<div id="media">
			<video id="video" width="350" height="200" autoplay></video>
			<canvas id="canvas" width="400" height="300"></canvas>
		</div>
		<dd>
			<input  type="button" onclick="loginuser()" value="立即登录" class="submit_btn">
		</dd>

	</dl>


<%--	<script src="js/jquery.js"></script>--%>
<%--	<script src="js/Particleground.js"></script>--%>

	<%--    <script type="text/javascript">--%>
	<%--        $(document).ready(function () {--%>
	<%--            //粒子背景特效--%>
	<%--            $('body').particleground({--%>
	<%--                dotColor: '#5cbdaa',--%>
	<%--                lineColor: '#5cbdaa'--%>
	<%--            });--%>
	<%--        });--%>
	<%--    </script>--%>


	<script type="text/javascript">

		var canvans = document.getElementById("canvas");
		var video = document.getElementById("video"); //获取video标签
		var context = canvas.getContext("2d");
		var con = {
			audio: false,
			video: {
				width: 1980,
				height: 1024,
			}
		};

		//导航 获取用户媒体对象
		navigator.mediaDevices.getUserMedia(con)
			.then(function (stream) {
				try {
					video.srcObject = stream;
				}catch (error) {
					video.src = window.URL.createObjectURL(stream);
				}
				video.onloadmetadate = function (e) {
					video.play();
				}
			});

		function loginuser() {

			//把流媒体数据画到convas画布上去
			context.drawImage(video, 0, 0, 400, 300);
			var imgData = canvans.toDataURL();
			var imgData1 = imgData.split("base64,")[1];

			$.ajax({
				type: "post",
				url: "${pageContext.request.contextPath}/front/faceLogin",
				data: {"img": imgData1},
				success: function (data) {
					var result = eval(data);
					alert(result);

					// if (result) {
					// 	new TipBox({type: 'success', str: '人脸识别成功', hasBtn: true});
					// } else {
					// 	//alert("面容识别失败,请继续验证");
					// 	new TipBox({
					// 		type: 'tip',
					// 		str: '面容识别失败,请继续验证',
					// 		clickDomCancel: true,
					// 		setTime: 10000500,
					// 		hasBtn: true
					// 	});
					// }
				}
			});
		}

	</script>


</form>

</body>

</html>
