layui.use(['form', 'laydate', 'table'], function () {

    var table = layui.table;
    var $ = layui.jquery;
    var path = $('#path').val();


    var tableins = table.render({
        elem: '#dataTable'
        , url: path + '/queryNewToShow'
        , cellMinWidth: 90
        , limit: 10
        , cols: [[
            {
                field: 'newTitel', title: '行业资讯', templet: function (d) {
                    return '<span style="font-size: larger">' + d.newTitel + '</span>'
                }
            }
            , {field: 'newUrl', title: '资讯地址', hide: true}

        ]]
        , page: true
    });

    table.on('row(dataTable)', function(obj) {
        var data = obj.data;
        layer.confirm("即将跳转至第三方页面?", {btn: ['继续访问', '取消访问'], title: "提示"}, function () {
            window.location.href = data.newUrl
        });
    });
});
