layui.use(['form', 'laydate', 'table'], function () {

    var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
    var $ = layui.jquery;
    var cardId;
    var pcardnumber;
    var startTime ;
    var endTime;
    var path = $('#path').val();

    $('#query').click(function () {
        startTime = new Date().valueOf();
        pcardnumber = "";
         pidentitynumber = $('#pidentitynumber').val();
        $.post(path +"/queryPatientInfo", {
            pidentitynumber: pidentitynumber
        }, function (cardResponseMsg) {
            if (cardResponseMsg.msg === "success") {
                form.val("issueform", {
                    "pname": cardResponseMsg.obj.pname
                    , "pgender": cardResponseMsg.obj.pgender
                    , "origoName": cardResponseMsg.obj.origoName
                    , "pphone": cardResponseMsg.obj.pphone
                    , "gcname": cardResponseMsg.obj.gcname
                });
                pcardnumber = cardResponseMsg.obj.pcardnumber
            } else {
                layer.alert('未查询到该体检人员信息');

            }
        });

    });
    $('#readcard').click(function () {
        cardId = "";
        var cardNo = $('#cardNo').val();
        $.post(path +"/queryCardInfo", {
            cardNo: cardNo
        }, function (cardResponseMsg) {
            if (cardResponseMsg.msg === "success") {
                layer.alert('读卡成功');
                cardId = cardResponseMsg.obj.cardId;
                $('#commit').removeClass('layui-btn-disabled').removeAttr('disabled',"true")
            } else {
                layer.alert('请换一张卡');
            }
        });

    });
    form.on('submit(commit)', function (formdata) {
        endTime = new Date().valueOf();
        var timeCost = endTime - startTime;
            $.post(path +"/cardIssue", {
                cardId: cardId,
                pcardnumber: pcardnumber,
                timeCost: timeCost
            }, function (msg) {
                if (msg === "success") {
                   layer.alert("发卡成功");
                    $('#issueform')[0].reset();
                } else {
                    layer.alert("发卡失败");
                }
            });
        return false;
    });


});
