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
                $('#cancelWid').append(new Option('全部', 0));
                $.each(msg, function (index, item) {
                    $('#cancelWid').append(new Option(item.wname, item.wid))
                });
                form.render('select');
            });
    });
    var tableins = table.render({
        elem: '#dataTable'
        , url: path +'/queryCardCancelToAudit'
        , cellMinWidth: 90
        , limit: 5
        , cols: [[
            {type: 'numbers', title: '序号'}
            , {field: 'ccancelId', title: '申请ID', hide: true}
            , {field: 'cancelCause', title: '销卡原因', hide: true}
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
                url: path +'/queryCardCancelToAudit',
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
        if (obj.event === 'detail') {
                var index = layer.open({
                    type: 1,
                    content: $('#popupface'),
                    area: ['500px'],
                    title: '销卡信息详情'
                });

                $('#cancelWname').text(tableData.cancelWname);
                $('#cancelDate').text(tableData.cancelDate);
                $('#popupCardNo').text(tableData.cardNo);
                $('#cancelCause').text(tableData.cancelCause);

        } else if (obj.event === 'pass') {

            layer.confirm("是否通过申请?", {btn: ['确定', '取消'], title: "提示"}, function () {

                $.post(path +"/manageCardCancelPass", {
                    'ccancelId': tableData.ccancelId,
                    'cardNo': tableData.cardNo
                }, function (msg) {
                    if (msg === "success") {
                        layer.alert("审核成功");
                        tableins.reload({
                            url: path +'/queryCardCancelToAudit',
                            where: {"id":tableData.ccancelId},
                            page: {curr: 1},
                            done: function (res, curr, count) {
                                this.where = {};
                            }
                        });
                    } else {
                        layer.alert("审核失败");
                    }
                });


            });
        } else if (obj.event === 'back') {
            layer.confirm("是否退回申请?", {btn: ['确定', '取消'], title: "提示"}, function () {

                $.post(path +"/manageCardCancelBack", {
                    'ccancelId': tableData.ccancelId,
                    'cardNo': tableData.cardNo
                }, function (msg) {
                    if (msg === "success") {
                        layer.alert("退回成功");
                        tableins.reload({
                            url:path + '/queryCardCancelToAudit',
                            where: {"id": tableData.ccancelId},
                            page: {curr: 1},
                            done: function (res, curr, count) {
                                this.where = {};
                            }
                        });
                    } else {
                        layer.alert("退回失败");
                    }
                });


            });
        }
    });


});
