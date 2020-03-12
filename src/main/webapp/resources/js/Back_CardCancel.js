layui.use(['form', 'laydate', 'table'], function () {

    var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
    var laydate = layui.laydate;
    var table = layui.table;
    var $ = layui.jquery;
    var path = $('#path').val();
    laydate.render({
        elem: '#fdate'
    });
    laydate.render({
        elem: '#edate'
    });
    var tableins = table.render({
        elem: '#dataTable'
        , url: path +'/queryCardCancel'
        , cellMinWidth: 90
        , limit: 5
        , cols: [[
            {type: 'numbers', title: '序号'}
            , {field: 'ccancelId', title: '申请ID', hide: true}
            , {field: 'cardNo', title: '销卡卡号', sort: true}
            , {field: 'cancelWname', title: '销卡人', sort: true}
            , {field: 'cancelDate', title: '销卡日期'}
            , {
                field: 'applyState', title: '审核状态', sort: true, templet: function (d) {
                    if (d.applyState === 2001) {
                        return '待审核'
                    } else if (d.applyState === 2002) {
                        return '已审核'
                    } else if (d.applyState === 2003) {
                        return '已退回'
                    }else {
                        return '未知'
                    }
                }
            }
            , {field: 'auditWname', title: '审核人'}
            , {field: 'auditDate', title: '审核时间'}
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
        ]]
        , page: true
    });

    form.on('submit(query)', function (data) {
        tableins.reload({
                url: path +'/queryCardCancel',
                where: data.field,
                page: {curr: 1},
                done: function (res, curr, count) {
                    this.where = {};
                }

            }
        );
        return false;
    });


    $('#popup').click(function () {
        var index = layer.open({
            type: 1,
            content: $('#popupface'),
            area: ['500px'],
            title: '销卡申请页面'
        });
        $('#commitform')[0].reset();
        var mydate = new Date().toLocaleString();
        $('#cancelDate').text(mydate);
        form.on('submit(commit)', function (data) {
            var popupCardNo = data.field.popupCardNo;
            var cancelCause = data.field.cancelCause;

            $.ajax({
                url: path +'/cardCancelInsert',
                type: 'post',
                data: {"cardNo": popupCardNo, "cancelCause": cancelCause},
                dataType: "text",
                success: function (cardResponseMsg) {
                    var rmsg = $.parseJSON(cardResponseMsg);
                    if (rmsg.msg === "success") {
                        layer.alert("销卡申请提交成功");
                        layer.close(index);
                        tableins.reload({
                                url: path +'/queryCardCancel',
                                where: {"id": rmsg.obj},
                                page: {curr: 1},
                                done: function (res, curr, count) {
                                    this.where = {};
                                }
                            }
                        )
                    } else if (rmsg.msg === "cannot") {
                        layer.alert("请换一张卡")
                    } else {
                        layer.alert("销卡申请提交失败")
                    }
                }
            });
            return false;
        })
    });
    table.on('tool(dataTable)', function (obj) {
        var tableData = obj.data;
        if (obj.event === 'backout') {
            layer.confirm("是否撤销申请?", {btn: ['确定', '取消'], title: "提示"}, function () {

                $.post(path +"/cardCancelBackout", {
                    'ccancelId': tableData.ccancelId,
                    'cardNo': tableData.cardNo
                }, function (msg) {
                    if (msg === "success") {
                        layer.alert("撤销成功");
                        tableins.reload({
                            url: path +'/queryCardCancel',
                            page: {curr: 1},
                        });
                    } else {
                        layer.alert("撤销失败");
                    }
                });


            });

        }else if (obj.event === 'detail'){
            var index = layer.open({
                type: 1,
                content: $('#popupface2'),
                area: ['500px'],
                title: '销卡信息详情'
            });

            $('#cancelWname2').text(tableData.cancelWname);
            $('#cancelDate2').text(tableData.cancelDate);
            $('#popupCardNo2').text(tableData.cardNo);
            $('#cancelCause2').text(tableData.cancelCause);

        }
    });


});
