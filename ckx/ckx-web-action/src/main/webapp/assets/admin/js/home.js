display("field"); // 需要翻译的类型
var editor = {
    val: {
        type: 'validatebox',
        options: {
            required: true,
            validType: 'integer'
        }
    }
};
$(function () {

    //定义全局变量，当前编辑的行
    var editRow = undefined;

    $('#thresholdEditDialog').dialog({
        buttons: [
            {
                text: '保存',
                handler: function () {
                    //在使用getChanges之前必须为编辑关闭状态
                    $('#thresholdGrid').datagrid('endEdit', editRow);
                    editRow = undefined;
                    var changeData = $('#thresholdGrid').datagrid('getRows');
                    var sc = JSON.stringify(changeData);
                    if (changeData.length > 0) {
                        $.post('threshold/updateThreshold.html', {changeData: sc}, function (r) {
                            if (r.r) {
                                $('#thresholdGrid').datagrid('acceptChanges');
                                $('#thresholdGrid').datagrid('unselectAll');
                                $('#grid1').datagrid('reload');
                                $('#thresholdEditDialog').dialog('close');
                            } else {
                                editRow = undefined;
                                $('#grid').datagrid('rejectChanges');
                                $('#grid').datagrid('unselectAll');
                            }
                            $.messager.alert('提示', r.m);
                        }, 'json');
                    } else {
                        $.messager.alert('提示', '您还没有修改数据!');
                    }
                }
            },
            {
                text: '关闭',
                handler: function () {
                    $('#thresholdEditDialog').dialog('close');
                }
            }
        ]
    });

    $('#queryButton2').click(function () {
        var params = $('#thresholdEditForm')._formToJson();
        $.post('threshold/loadReport.html', params, function (r) {
            var data = $.parseJSON(r);
            $(thresholdGrid).datagrid('loadData', data);
        })

    });

    var thresholdGrid = $('#thresholdGrid')._datagrid({
        checkOnSelect: false,
        selectOnCheck: false,
        pagination: false,
        onDblClickCell: function (rowIndex, rowData) {
            $('#thresholdGrid').datagrid('endEdit', editRow);
            $('#thresholdGrid').datagrid('beginEdit', rowIndex);
            $('#thresholdGrid').datagrid('selectRow', rowIndex);
            editRow = rowIndex;
        },
        toolbar: [{
            text: '取消选择编辑',
            iconCls: 'icon-redo',
            handler: function () {
                //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
                editRow = undefined;
                $('#thresholdGrid').datagrid('rejectChanges');
                $('#thresholdGrid').datagrid('unselectAll');
            }
        }, '-']
    });


    var grid0 = $('#grid0')._datagrid({
        checkOnSelect: false,
        selectOnCheck: false,
        pageSize: 5,
        pageList: [5, 10, 20, 30, 50, 100],
        onBeforeLoad: function (param) {
            if (!$('#queryWarnForm').form('validate')) {
                return false;
            }
            var queryParams = $('#queryWarnForm')._formToJson();
            for (i in queryParams) {
                param[i] = queryParams[i];
            }
        }
    });
    var grid1 = $('#grid1')._datagrid({
        checkOnSelect: false,
        selectOnCheck: false,
        pageSize: 5,
        pageList: [5, 10, 20, 30, 50, 100]
    });
    /** 列表查询* */
    $('#queryWarnButton').click(function () {
        var params = $('#queryWarnForm')._formToJson();
        $('#grid0').datagrid('load', params);
    });
    $('#queryThresholdButton').click(function () {
        var params = $('#queryThresholdForm')._formToJson();
        $('#grid1').datagrid('load', params);
    });
});
var formatter = {
    opt: function (value, rowData, rowIndex) {
        var html = "";
        html += '<a class="spacing a-blue" onclick="toSet(' + rowIndex
            + ');" href="javascript:void(0);">去设置</a>';
        return html;
    },
    field: function (value, rowData, rowIndex) {
        return $.fn.display.field[value];
    },
};
// 首页设置阈值
function toSet(rowIndex) {
    var data = $('#grid1').datagrid('getRows')[rowIndex];
    $("#thresholdEditForm").find("input[name$='channelKey']").val(data.cKey);
    $("#thresholdEditForm").find("input[name$='productKey']").val(data.pKey);
    $("#thresholdEditForm").find("input[name$='field']").val(data.field);
    var params = {"channelKey": data.cKey, "productKey": data.pKey, "field": data.field};
    $('#thresholdGrid').datagrid('load', params);
    isLoadData = false;
    $('#thresholdEditDialog').dialog('open').dialog("setTitle", "设置预警字段阈值");

}