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
        , url: path +'/queryCardApply'
        , cellMinWidth: 90
        , limit: 5
        , cols: [[
            {type: 'numbers', title: '序号'}
            , {field: 'capplyId', title: '申请ID', hide: true}
            , {field: 'applayQuat', title: '申请数量', sort: true}
            , {field: 'applyWname', title: '申请人', sort: true}
            , {field: 'applyDate', title: '申请日期'}
            , {
                field: 'applyState', title: '审核状态', sort: true, templet: function (d) {
                    if (d.applyState === 2001) {
                        return '待审核'
                    } else if (d.applyState === 2002) {
                        return '已审核'
                    } else {
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
                url: path +'/queryCardApply',
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
            title: '卡申请页面'
        });
        $('#commitform')[0].reset();
        var mydate = new Date().toLocaleString();
        $('#applyDate').text(mydate);
        form.on('submit(commit)', function (data) {
            var applayQuat = data.field.applayQuat;

            $.ajax({
                url:path + '/cardApplyInsert',
                type: 'post',
                data: {"applayQuat": applayQuat},
                dataType: "text",
                success: function (cardResponseMsg) {
                    var rmsg = $.parseJSON(cardResponseMsg);
                    if (rmsg.msg === "success") {
                        layer.alert("申请提交成功");
                        layer.close(index);
                        tableins.reload({
                                url:path + '/queryCardApply',
                                where: {'id': rmsg.obj},
                                page: {curr: 1},
                                done: function (res, curr, count) {
                                    this.where = {};
                                }
                            }
                        )
                    } else {
                        layer.alert("申请提交失败")
                    }
                }
            });
            return false;
        })
    });
    table.on('tool(dataTable)', function (obj) {
        var tableData = obj.data;
        if (obj.event === 'alter') {
            var index1 = layer.open({
                type: 1,
                content: $('#popupface2'),
                area: ['400px'],
                title: '申请修改页面'
            });
            $('#applyWname2').text(tableData.applyWname);
            $('#applyDate2').text(tableData.applyDate);
            $('#applayQuat2').val(tableData.applayQuat);

            form.on('submit(commit2)', function (formdata) {
                $.post(path +"/cardApplyUpdateByProposer", {
                    capplyId: tableData.capplyId,
                    applayQuat: formdata.field.applayQuat2,
                }, function (msg) {
                    if (msg === "success") {
                        layer.alert("修改成功");
                        layer.close(index1);
                        tableins.reload({
                            url: path +'/queryCardApply',
                            where: {'id': tableData.capplyId},
                            page: {curr: 1},
                            done: function (res, curr, count) {
                                this.where = {};
                            }
                        });
                    } else {
                        layer.alert("修改失败");
                    }
                });
                return false;
            });


        }

    });

});
