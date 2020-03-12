layui.use(['form', 'laydate', 'table'], function () {
    //只有执行了这一步，部分表单元素才会自动修饰成功
    var table = layui.table;
    var form = layui.form;
    var $ = layui.jquery;
    var path = $('#path').val();
    var tableins1 = table.render({
        elem: '#dataTable1'
        , url: path +'/myAudit'
        , cellMinWidth: 90
        , cols: [[
            {type: 'numbers', title: '序号'}
            , {field: 'id', title: '任务ID', hide: true}
            , {field: 'name', title: '任务名称', sort: true}
            , {
                field: 'createTime',
                title: '任务时间',
                sort: true,
                templet: "<div>{{layui.util.toDateString(d.applyTime, 'yyyy-MM-dd HH:mm:ss')}}</div>"
            }
            , {field: 'applyUser', title: '申请人', sort: true, templet: '<div>{{d.vac.applyUser}}</div>'}
            , {
                field: 'applyTime',
                title: '申请时间',
                sort: true,
                templet: '<div>{{d.vac.applyTime}}</div>',
                templet: "<div>{{layui.util.toDateString(d.applyTime, 'yyyy-MM-dd HH:mm:ss')}}</div>"
            }
            , {field: 'days', title: '请购数量', sort: true, templet: '<div>{{d.vac.days}}</div>'}
            , {field: '.reason', title: '请购原因', templet: '<div>{{d.vac.reason}}</div>'}
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
        ]]
    });
    var tableins2 = table.render({
        elem: '#dataTable2'
        , url: path +'/myAuditRecord'
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


    table.on('tool(dataTable)', function (obj) {
        var tableData = obj.data;
        var result = "";
        if (obj.event === 'pass') {

            layer.confirm("是否执行操作?", {btn: ['确定', '取消'], title: "提示"}, function () {

                result = "审核通过";
                var vactask = {
                    'id': tableData.id,
                    'vac': {result: result}
                };
                $.ajax({
                    url: path +'/passAudit',
                    type: 'post',
                    data: JSON.stringify(vactask),
                    dataType: "text",
                    contentType: "application/json",
                    success: function (msg) {
                        if (msg === "success") {
                            layer.alert("操作成功");
                            tableins1.reload({
                                url:path + '/myAudit',
                                page: {curr: 1},
                            });
                            tableins2.reload({
                                url: path +'/myAuditRecord',
                                page: {curr: 1},
                            });
                        } else {
                            layer.alert("操作失败");
                        }
                    }
                });
            });
        } else if (obj.event === 'back') {
            layer.confirm("是否执行操作?", {btn: ['确定', '取消'], title: "提示"}, function () {

                result = "拒绝申请";
                var vactask = {
                    'id': tableData.id,
                    'vac': {result: result}
                };
                $.ajax({
                    url: path +'/passAudit',
                    type: 'post',
                    data: JSON.stringify(vactask),
                    dataType: "text",
                    contentType: "application/json",
                    success: function (msg) {
                        if (msg === "success") {
                            layer.alert("操作成功");
                            tableins1.reload({
                                url: path +'/myAudit',
                                page: {curr: 1},
                            });
                            tableins2.reload({
                                url: path +'/myAuditRecord',
                                page: {curr: 1},
                            });
                        } else {
                            layer.alert("操作失败");
                        }
                    }
                });
            });
        }

    });


});

