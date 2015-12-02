display("role,post"); // 需要翻译的类型
$(function() {
    $('#roleId')._pullDownTree({url:'role/get_roles.html', width:150, height:200, treeName: 'roleName', treeId:'roleId', treePId : 'parentRole',chkboxType:{ "Y" : "", "N" : ""},onCheck:function(zTree,treeNode,targ, hide){
        zTree.checkAllNodes(false);
        zTree.checkNode(treeNode, true, true);
        var nodes = zTree.getCheckedNodes(true);
        for(var i in nodes){
            hide.val(nodes[i].roleId);
            targ.val(nodes[i].roleName);
        }
    },successCallback : function(zTree) {
        node = zTree.getNodeByParam("roleId", window.user.roleId, null);
        node.nocheck = true;
        zTree.updateNode(node);
    }});

});





function add(){
	$('#createNewUserForm').attr("action","user/add.html");
    if(!$('#createNewUserForm').form('validate')){return;}
    $('#createNewUserForm')._ajaxForm(function(r){
        if(r.r){
        	$(parent.document).find("[href='menus/user_mgr.html']").click();
        }else{$.messager.alert('操作提示', r.m,'error');}
    });

}