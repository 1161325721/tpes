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
        , url: path +'/queryCardStorage'
        , cellMinWidth: 90
        , limit: 5
        , cols: [[
            {type: 'numbers', title: '序号'}
            , {field: 'cardPrefix', title: '卡前缀',  sort: true}
            , {field: 'cardSfxstart', title: '开始编号',  sort: true}
            , {field: 'cardSfxend', title: '截止编号' }
            , {field: 'cardQuat', title: '数量',  sort: true}
            , {field: 'storageDate', title: '入库时间' }
            , {field: 'storageWname', title: '入库人' }
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
        ]]
        , page: true
    });

    form.on('submit(query)', function (data) {
        tableins.reload({
                url:path + '/queryCardStorage',
                where: data.field,
                page: {curr: 1}
            }
        )
        return false;
    })


    $('#popup').click(function () {
        var index = layer.open({
            type: 1,
            content: $('#popupface'),
            area: ['500px'],
            title: '卡入库页面'
        });
        form.on('submit(commit)', function (data) {
            var cardPrefix = data.field.cardPrefix;
            var cardSfxstart = data.field.cardSfxstart;
            var cardSfxend = data.field.cardSfxend;

            $.ajax({
                url: path +'/cardStorageInster',
                type: 'post',
                data: {"cardPrefix": cardPrefix, "cardSfxstart": cardSfxstart, "cardSfxend": cardSfxend},
                dataType: "text",
                success: function (msg) {
                    if (msg === "success") {
                        layer.alert("入库成功");
                        layer.close(index);
                        tableins.reload({
                                url:path + '/queryCardStorage',
                                where: data.field,
                                page: {curr: 1}
                            }
                        )
                    } else {
                        layer.alert("入库失败")
                    }
                }
            });
            return false;
        })
    });
    table.on('tool(dataTable)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            var index1 = layer.open({
                type: 1,
                content: $('#popupface2'),
                area: ['400px'],
                title: '入库详情页面'
            });
            $('#cardprefix').text(data.cardPrefix);
            $('#cardsfxstart').text(data.cardSfxstart);
            $('#cardsfxend').text(data.cardSfxend);
            $('#cardquat').text(data.cardQuat);
            $('#storagewname').text(data.storageWname);
            $('#storagedate').text(data.storageDate);
        }

    });

});
