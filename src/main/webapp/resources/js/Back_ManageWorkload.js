layui.use(['form', 'laydate', 'table'], function () {

    var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
    var laydate = layui.laydate;
    var table = layui.table;
    var $ = layui.jquery;
    var nameArr = [];
    var valueArr = [];
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
                $('#issueWid').append(new Option('全部', 0));
                $.each(msg, function (index, item) {
                    $('#issueWid').append(new Option(item.wname, item.wid))
                });
                form.render('select');
            });


    });
    var tableins = table.render({
        elem: '#dataTable'
        , url: path +'/queryWorkload'
        , cellMinWidth: 90
        , limit: 5
        , cols: [[
            {type: 'numbers', title: '序号'}
            , {field: 'wname', title: '工作人员', sort: true}
            , {field: 'issueNos', title: '发卡数', sort: true}
            , {field: 'changeNos', title: '换卡数'}
            , {field: 'totalCard', title: '总操作数'}
        ]]
        , page: true
    });

    form.on('submit(query)', function (data) {
        tableins.reload({
                url: path +'/queryWorkload',
                where: data.field,
                page: {curr: 1},
                done: function (res, curr, count) {
                    this.where = {};
                }

            }
        );
        return false;
    });


});

