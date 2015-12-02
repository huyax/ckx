$(function() {
    $('#editDialog').dialog({
        buttons:[]
    });
    $('#trackDialog').dialog({
        buttons:[]
    });
    var grid = $('#grid')._datagrid({
    	method:'get',
        checkOnSelect:false,
        selectOnCheck:false,/*
        onLoadSuccess:function(){
        	track();
        },*/
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar : [{
                    text : '查看轨迹',
                    iconCls : 'icon-add',
                    handler : handlerTrack
                }, '-', {
                    text : '删除所选',
                    iconCls : 'icon-remove',
                    handler : batchDel
                }, '-' ]
    });
    
    $('#queryButton').click(function(){
        var params = $('#queryForm')._formToJson();
        $(grid).datagrid('load',params);
    });

    /*新增*/
    function handlerTrack() {
   		track();
    }
    /*批量删除*/
    function batchDel() {
        var check = $('#grid').datagrid('getChecked');
        if(check.length > 0){
            $.messager.confirm('操作提示', '确定要删除所选数据？', function(r){
                if (r){
                    var datas = new Array();
                    for(var i in check){
                    	datas[i] = check[i].id;
                    }
                    
                    var paramIds = datas.join('|');
                    $.ajax({
        				url : 'batch',
        				type : 'POST',
        				data:{_method:'DELETE',ids:paramIds},
        				success : function(r) {
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
    opt : function(value, rowData, rowIndex) {
        var html = '<a class="spacing a-blue" onclick="update('+rowIndex+');" href="javascript:void(0);">定位</a>';
            html+= '<a class="spacing a-red" onclick="del('+rowIndex+');" href="javascript:void(0);">删除</a>';
        return html;
    }
};

function map(longtitude,latitude,title,content,type)
{
	createMap(longtitude,latitude,title,content,type);//创建地图  
}

/*修改*/
function update(rowIndex) {
    $('#editForm').resetForm();
    $('#_method').val('put');
    var data = $('#grid').datagrid('getRows')[rowIndex];
    $('#editForm')._jsonToForm(data);
    $('#editDialog').dialog('open').dialog('setTitle','地图定位');
    var map = new BMap.Map("container");
    theLocation(map);
}
/*删除*/
function del(rowIndex) {
    $.messager.confirm('操作提示', '确定要删除该记录？', function(r){
        if (r){
            var data = $('#grid').datagrid('getRows')[rowIndex];
            $.ajax({
				url : data.id,
				type : 'DELETE',
				success : function(r) {
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

function theLocation(map){
	if(document.getElementById("longitude").value != "" && document.getElementById("latitude").value != ""){
		map.clearOverlays();
		map.enableScrollWheelZoom(true);
		
		var point = new BMap.Point(document.getElementById("longitude").value,document.getElementById("latitude").value);
		map.centerAndZoom(point,20);//设定地图的中心点和坐标并将地图显示在地图容器中 
		var marker = new BMap.Marker(point);  // 创建标注
		map.addOverlay(marker);              // 将标注添加到地图中
		map.panTo(point);      
	}
}

function track(){
	
	$('#trackDialog').dialog('open').dialog('setTitle','轨迹图');
	
	var rows = $('#grid').datagrid('getRows');
	if(rows == null || rows.length <= 0){
		return ;
	}
	var map = new BMap.Map("track");
	map.enableScrollWheelZoom();
	var points = new Array();
	for(var i=0;i<rows.length;i++){
		points[i] = new BMap.Point(rows[i].longitude,rows[i].latitude);
		var marker = new BMap.Marker(points[i]);  // 创建标注
		if(i == 0){
			map.centerAndZoom(points[i], 17);//设置启始中心
			marker.setAnimation(BMAP_ANIMATION_BOUNCE); //设置启始弹跳
		}
		map.addOverlay(marker);              // 将标注添加到地图中
		map.panTo(points[i]); 
	}
	var polyline = new BMap.Polyline(points, {strokeColor:"blue", strokeWeight:3, strokeOpacity:0.5}); //创建折线对象
	map.addOverlay(polyline); //添加到地图中
	//curve.enableEditing(); //开启编辑功能
}
