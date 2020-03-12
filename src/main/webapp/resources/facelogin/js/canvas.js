//var 是定义变量
var canvans = document.getElementById("canvas");
var video = document.getElementById("video"); //获取video标签
var context = canvas.getContext("2d");
var con  ={
    audio:false,
    video:{
        width:1980,
        height:1024,
    }
};

//导航 获取用户媒体对象
navigator.mediaDevices.getUserMedia(con)
    .then(function(stream){
        video.src = window.URL.createObjectURL(stream);
        video.onloadmetadate = function(e){
            video.play();
        }
    });

function query(){

    //把流媒体数据画到convas画布上去
    context.drawImage(video,0,0,400,300);
    var imgData = canvans.toDataURL();
    var imgData1 = imgData.split("base64,")[1];

    $.ajax({
        type:"post",
        url:"${pageContext.request.contextPath}/facelogin.action",
        data:{"img":imgData1},
        success:function(data) {
            var result = eval(data);
            if (result) {
                //location.href = 'success.jsp';
                new TipBox({type:'success',str:'人脸识别成功',hasBtn:true});
            } else {
                //alert("面容识别失败,请继续验证");
                new TipBox({type:'tip',str:'面容识别失败,请继续验证',clickDomCancel:true,setTime:10000500,hasBtn:true});
            }
        }
    });
}


function logininfo(){

    location.href = 'success.jsp';

}