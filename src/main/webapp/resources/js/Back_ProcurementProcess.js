layui.use(['form', 'laydate', 'table'], function () {
    //只有执行了这一步，部分表单元素才会自动修饰成功
    var table = layui.table;
    var form = layui.form;
    var $ = layui.jquery;
    var path = $('#path').val();
    var tableins1 = table.render({
        elem: '#dataTable1'
        , url: path + '/myVac'
        , cellMinWidth: 90
        , cols: [[
            {type: 'numbers', title: '序号'}
            , {
                field: 'applyTime',
                title: '申请时间',
                sort: true,
                templet: "<div>{{layui.util.toDateString(d.applyTime, 'yyyy-MM-dd HH:mm:ss')}}</div>"
            }
            , {field: 'days', title: '请购数量', sort: true}
            , {field: 'reason', title: '请购原因'}
            , {field: 'applyStatus', title: '申请状态'}
        ]]
    });
    var tableins2 = table.render({
        elem: '#dataTable2'
        , url: path + '/myVacRecord'
        , cellMinWidth: 90
        , cols: [[
            {type: 'numbers', title: '序号'}
            , {
                field: 'applyTime',
                title: '申请时间',
                sort: true,
                templet: "<div>{{layui.util.toDateString(d.applyTime, 'yyyy-MM-dd HH:mm:ss')}}</div>"
            }
            , {field: 'days', title: '请购数量', sort: true}
            , {field: 'reason', title: '请购原因'}
            , {field: 'applyStatus', title: '申请状态'}
            , {field: 'applyUser', title: '申请人'}
            , {field: 'auditor', title: '审核人'}
            , {field: 'result', title: '审核结果'}
            , {
                field: 'auditTime',
                title: '审核时间',
                templet: "<div>{{layui.util.toDateString(d.auditTime, 'yyyy-MM-dd HH:mm:ss')}}</div>"
            }
        ]]
    });
    $('#popup').click(function () {
        $.ajax({
            url: path + '/queryUserPower',
            type: 'post',
            dataType: "text",
            success: function (msg) {
                if (msg === "success") {
                    var index = layer.open({
                        type: 1,
                        content: $('#popupface'),
                        area: ['500px'],
                        title: '卡购买页面'
                    });
                    $('#commitform')[0].reset();
                    var mydate = new Date().toLocaleString();
                    $('#applyDate').text(mydate);
                    form.on('submit(commit)', function (data) {
                        var days = data.field.days;
                        var reason = data.field.reason;

                        $.ajax({
                            url: path + '/startVac',
                            type: 'post',
                            data: {"days": days, "reason": reason},
                            dataType: "text",
                            success: function (msg) {
                                if (msg === "success") {
                                    layer.alert("申请提交成功");
                                    layer.close(index);
                                    tableins1.reload({
                                            url: path + '/myVac',
                                            page: {curr: 1}
                                        }
                                    );
                                    tableins2.reload({
                                            url: path + '/myVacRecord',
                                            page: {curr: 1}
                                        }
                                    )
                                } else {
                                    layer.alert("申请提交失败")
                                }
                            }
                        });
                        return false;
                    })

                } else {
                    layer.alert("您的权限不足")
                }
            }
        });

    });


});

