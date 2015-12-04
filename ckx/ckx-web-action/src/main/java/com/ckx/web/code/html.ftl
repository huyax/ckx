${r'<@compress single_line=isCompress>'}
<!DOCTYPE html>
<html>
<head>
    <title>${name}管理</title>
${r'<#include "/pager/common/quote.html" />'}
</head>
<body>
<div class="body-center">
    <div class="html-panel query-panel" id="query_con">
        <div class="panel-header">
            <div class="panel-title">查询条件</div>
        </div>
        <div class="panel-content" id="menuMgrPanel">
            <form onsubmit="return false;" id="queryForm" class="form_style">
                <span class="spacing"><label>ID：</label><input type="text" name="id"/></span>

                <span class="spacing"><input type="submit" id="queryButton" value="查询"/></span>
            </form>
        </div>
    </div>
    <table id="grid" data-options="url:'list'" title="数据列表">
        <thead>
        <#list columns as item>
        <th data-options="field:'${item}',width:80,resizable:true,align:'left'">${item}</th>
        </#list>
        <th data-options="field:'opt',width:80,resizable:true,align:'center',formatter:formatter.opt">操作</th>
        </thead>
    </table>
</div>
<div class="hide-panel">
    <div id="editDialog" style="width:500px;height:300px;padding-left:50px;"
         data-options="resizable:false,modal:true,closed: true">
        <form id="editForm" action="${r'${base}'}/${nameLow}" class="form_style" method="post" onsubmit="return false;">
            <input　type="hidden"　name="_method" id="_method"　value="put"/>
            <input type="hidden" name="id" id="edit_id"/>

        <#list columns as item>
            <div class="form-field">
                <label for="edit_key">${item}：</label>
                <input class="easyui-validatebox" type="text" name="${item}" id="edit_${item}" maxlength="30"/>
            </div>
        </#list>

        </form>
    </div>
</div>
</body>
</html>
${r'</@compress>'}
