$(function () {
    $('#editDialog').dialog({
        buttons: [{
            text: '保存', handler: function () {
                if (!$('#editForm').form('validate')) {
                    return;
                }
                $('#editForm')._ajaxForm(function (r) {
                    if (r.r) {
                        $('#editDialog').dialog('close');
                        $('#grid').datagrid('reload');
                    } else {
                        $.messager.alert('操作提示', r.m, 'error');
                    }
                });
            }
        }, {
            text: '关闭', handler: function () {
                $('#editDialog').dialog('close');
            }
        }]
    });
    var grid = $('#grid')._datagrid({
        method: 'get',
        checkOnSelect: false,
        selectOnCheck: false,
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '添加数据',
            iconCls: 'icon-add',
            handler: handlerAdd
        }, '-', {
            text: '删除所选',
            iconCls: 'icon-remove',
            handler: batchDel
        }, '-']
    });

    $('#queryButton').click(function () {
        var params = $('#queryForm')._formToJson();
        $(grid).datagrid('load', params);
    });

    /*新增*/
    function handlerAdd() {
        $('#editForm').resetForm();
        $('#_method').val('post');
        $('#edit_id').val('');
        $('#editDialog').dialog('open').dialog("setTitle", "添加数据");
    }

    /*批量删除*/
    function batchDel() {
        var check = $('#grid').datagrid('getChecked');
        if (check.length > 0) {
            $.messager.confirm('操作提示', '确定要删除所选数据？', function (r) {
                if (r) {
                    var datas = new Array();
                    for (var i in check) {
                        datas[i] = check[i].id;
                    }

                    var paramIds = datas.join('|');
                    $.ajax({
                        url: 'batch',
                        type: 'POST',
                        data: {_method: 'DELETE', ids: paramIds},
                        success: function (r) {
                            if (r.r) {
                                $('#grid').datagrid('reload');
                            } else {
                                $.messager.alert('操作提示', r.m, 'error');
                            }
                        }
                    });

                }
            });
        }
    }
});
var formatter = {
    opt: function (value, rowData, rowIndex) {
        var html = '<a class="spacing a-blue" onclick="upd(' + rowIndex + ');" href="javascript:void(0);">修改</a>';
        html += '<a class="spacing a-red" onclick="del(' + rowIndex + ');" href="javascript:void(0);">删除</a>';
        return html;
    }
};

/*修改*/
function update(rowIndex) {
    $('#editForm').resetForm();
    $('#_method').val('put');
    var data = $('#grid').datagrid('getRows')[rowIndex];
    $('#editForm')._jsonToForm(data);
    $('#editDialog').dialog('open').dialog('setTitle', '修改');
}
/*删除*/
function del(rowIndex) {
    $.messager.confirm('操作提示', '确定要删除该记录？', function (r) {
        if (r) {
            var data = $('#grid').datagrid('getRows')[rowIndex];
            $.ajax({
                url: data.id,
                type: 'DELETE',
                success: function (r) {
                    if (r.r) {
                        $('#grid').datagrid('reload');
                    } else {
                        $.messager.alert('操作提示', r.m, 'error');
                    }
                }
            });
        }
    });
}
