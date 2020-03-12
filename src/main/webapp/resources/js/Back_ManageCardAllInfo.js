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
        , url: path +'/queryCardAllInfoToAudit'
        , cellMinWidth: 90
        , limit: 5
        , cols: [[
            {type: 'numbers', title: '序号'}
            , {field: 'cardId', title: '卡ID', hide: true}
            , {field: 'cardNo', title: '卡号', sort: true}
            , {
                field: 'cardState', title: '卡状态', sort: true, templet: function (d) {
                    if (d.cardState === 1000) {
                        return '销卡锁定'
                    } else if (d.cardState === 1001) {
                        return '已入库'
                    } else if (d.cardState === 1002) {
                        return '已领用'
                    } else if (d.cardState === 1003) {
                        return '已使用'
                    } else if (d.cardState === 1004) {
                        return '替换弃用'
                    } else if (d.cardState === 1005) {
                        return '销卡弃用'
                    } else {
                        return '未知'
                    }
                }
            }
            , {field: 'applyWname', title: '领卡人', sort: true}
            , {field: 'getDate', title: '领卡日期', sort: true}
            , {field: 'issueWname', title: '发卡人', sort: true}
            , {field: 'issueDate', title: '发卡日期', sort: true}
            , {field: 'patientName', title: '绑定病人', sort: true}
            , {field: 'companyName', title: '所属单位', sort: true}
            , {field: 'cancelCause', title: '销卡原因', sort: true}
            , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
        ]]
        , page: true
    });

    form.on('submit(query)', function (data) {
        tableins.reload({
                url:path + '/queryCardAllInfoToAudit',
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
                title: '卡片信息详情'
            });
            $('.show').text('');
            $('#showCardNo').text(tableData.cardNo == null?'暂无数据':tableData.cardNo);
            $('#showCompanyName').text(tableData.companyName == null?'暂无数据':tableData.companyName);
            $('#showPatientName').text(tableData.patientName == null?'暂无数据':tableData.patientName);
            $('#showApplyWname').text(tableData.applyWname == null?'暂无数据':tableData.applyWname);
            $('#showGetDate').text(tableData.getDate == null?'暂无数据':tableData.getDate);
            $('#showIssueWname').text(tableData.issueWname == null?'暂无数据':tableData.issueWname);
            $('#showIssueDate').text(tableData.issueDate == null?'暂无数据':tableData.issueDate);

        }
    });


});
