layui.use(['form', 'laydate', 'table'], function () {

    var table = layui.table;
    var $ = layui.jquery;
    var path = $('#path').val();


    var tableins = table.render({
        elem: '#dataTable'
        , url: path + '/queryNew'
        , cellMinWidth: 90
        , limit: 5
        , cols: [[
            {type: 'numbers', title: '序号'}
            , {field: 'newId', title: '新闻ID', hide: true}
            , {field: 'newTitel', title: '新闻标题', sort: true}
            , {
                field: 'newStatus', title: '状态', width:120,sort: true, templet: function (d) {
                    if (d.newStatus === 1001) {
                        return '推送'
                    } else if (d.newStatus === 1002) {
                        return '取消推送'
                    } else {
                        return '未知'
                    }
                }
            }
            , {fixed: 'right', title: '操作', align: 'center', width:200,toolbar: '#barDemo'}
        ]]
        , page: true
    });
    $('#popup').click(function () {
        $.ajax({
            url: path + '/searchNew',
            type: 'post',
            dataType: "text",
            success: function (msg) {
                if (msg === "success") {
                    layer.alert("行业动态更新成功");
                    tableins.reload({
                            url: path + '/queryNew',
                            page: {curr: 1},
                        }
                    )
                } else {
                    layer.alert("服务器忙.....")
                }
            }
        });
    })

    table.on('tool(dataTable)', function (obj) {
        var tableData = obj.data;
        if (obj.event === 'stop') {
            layer.confirm("是否取消推送?", {btn: ['确定', '返回'], title: "提示"}, function () {

                $.post(path + "/newStop", {
                    'newId': tableData.newId,
                    'newStatus': 1002
                }, function (msg) {
                    if (msg === "success") {
                        layer.alert("操作成功");
                        tableins.reload({
                            url: path + '/queryNew',
                            where: {"id": tableData.newId},
                            page: {curr: 1},
                            done: function (res, curr, count) {
                                this.where = {};
                            }
                        });
                    } else {
                        layer.alert("操作失败");
                    }
                });


            });

        }
    });


});
