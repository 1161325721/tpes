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
    $(function () {
        $.post(path +"/queryAllCardProposer",
            {'rid': 4},
            function (msg) {
                $('#applyWid').append(new Option('全部', 0));
                $.each(msg, function (index, item) {
                    $('#applyWid').append(new Option(item.wname, item.wid))
                });
                form.render('select');
            });
    });
    var tableins = table.render({
        elem: '#dataTable'
        , url: path +'/queryCardApplyToAudit'
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
                url:path + '/queryCardApplyToAudit',
                where: data.field,
                page: {curr: 1},
                done: function (res, curr, count) {
                    this.where = {};
                }

            }
        );
        return false;
    });
    table.on('tool(dataTable)', function (obj) {
        var tableData = obj.data;
        if (obj.event === 'audit') {
            var index1 = layer.open({
                type: 1,
                content: $('#popupface'),
                area: ['400px'],
                title: '申请审核页面'
            });
            $('#applyWname').text(tableData.applyWname);
            $('#applyDate').text(tableData.applyDate);
            $('#applayQuat').text(tableData.applayQuat);

            form.on('submit(commit)', function (formdata) {
                $.post(path +"/manageCardApply", {
                    applyState: 2001,
                    id: tableData.capplyId,
                    cardPrefix: formdata.field.cardPrefix
                }, function (msg) {
                    if (msg === "success") {
                        layer.alert("审核成功");
                        tableins.reload({
                            url: path +'/queryCardApplyToAudit',
                            where: {'id': tableData.capplyId},
                            page: {curr: 1},
                            done: function (res, curr, count) {
                                this.where = {};
                            }
                        });
                    } else if (msg === "cannot") {
                        layer.alert("卡片库存不足,请安排相关事宜");
                    } else {
                        layer.alert("审核失败");
                    }
                    layer.close(index1);
                });
                return false;
            });


        } else if (obj.event === 'detail') {
            var index2 = layer.open({
                type: 1,
                content: $('#popupface2'),
                area: ['400px , 500px'],
                title: '申请详情页面'
            });
            $('#applyWname2').text(tableData.applyWname);
            $('#applyDate2').text(tableData.applyDate);
            $('#applayQuat2').text(tableData.applayQuat);
            $.post(path +"/showCardInfoToAudit", {
                capplyId: tableData.capplyId,
            }, function (msg) {
                $('#showData').empty();
                $.each(msg, function (index, item) {
                    $('#showData').append( item.cardNo)
                });
            })
        }

    });

});
