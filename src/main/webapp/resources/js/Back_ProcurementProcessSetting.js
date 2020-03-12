layui.use(['form', 'laydate', 'table'], function () {

    var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
    var table = layui.table;
    var $ = layui.jquery;
    var path = $('#path').val();
    var tableins = table.render({
        elem: '#dataTable'
        , url: path +'/queryWorkerGroup'
        , cellMinWidth: 90
        , limit: 5
        , cols: [[
            {type: 'numbers', title: '序号'}
            , {field: 'userName', title: '人员名称', sort: true}
            , {field: 'groupId', title: '所属节点', sort: true,
                templet: function (d) {
                    if (d.groupId === 'empGroup') {
                        return '仓管组'
                    } else if (d.groupId === 'manageGroup') {
                        return '经理组'
                    } else if (d.groupId === 'dirGroup') {
                        return '总监组'
                    } else {
                        return ''
                    }
                }
            }
            , {field: 'password', title: '员工ID', hide:true}
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
        ]]
        , page: true
    });

    form.on('submit(query)', function (data) {
        tableins.reload({
                url: path +'/queryWorkerGroup',
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
        if (obj.event === 'setting') {
            var index1 = layer.open({
                type: 1,
                content: $('#popupface'),
                area: ['400px'],
                title: '人员配置页面'
            });
            $('#showUserName').text(tableData.userName);
            // $('#password').text(tableData.password);

            form.on('submit(commit)', function (formdata) {
                $.post(path +"/addUser", {
                    userName:tableData.userName,
                    password:tableData.password,
                    groupId:formdata.field.groupId
                }, function (msg) {
                    if (msg === "success") {
                        layer.alert("设置成功");
                        tableins.reload({
                            url: path +'/queryWorkerGroup',
                            where: {'id': tableData.password},
                            page: {curr: 1},
                            done: function (res, curr, count) {
                                this.where = {};
                            }
                        });
                    }else {
                        layer.alert("审核失败");
                    }
                    layer.close(index1);
                });
                return false;
            });
        }

    });



});

