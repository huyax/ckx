//创建地图函数：  
function createMap(longtitude, latitude, title, content, type) {
    var longt;
    var lat;
    var map = new BMap.Map("container");//在百度地图容器中创建一个地图  
    var point = new BMap.Point(longtitude, latitude);//定义一个中心点坐标，这里是初始化要显示的经度和纬度，可到http://openapi.baidu.com/map/createMap.html查看自己要显示的值。
    var pointMarker = new BMap.Marker(point);
    // 当点击“编辑”时，定位到具体的坐标
    if (type == 2) {
        map.centerAndZoom(point, 20);//设定地图的中心点和坐标并将地图显示在地图容器中
        map.addOverlay(pointMarker);
        //pointMarker.setAnimation(BMAP_ANIMATION_BOUNCE);// 标注跳动
        //marker.disableDragging();// 标注不可拖拽
        pointMarker.enableDragging();// 标注可拖拽
    }
    // 当点击“新增”时，通过ip定位到具体的城市
    else {
        map.centerAndZoom(point, 12);//设定地图的中心点和坐标并将地图显示在地图容器中
        function myFun(result) {
            var cityName = result.name;
            map.setCenter(cityName);
        }

        var myCity = new BMap.LocalCity();
        myCity.get(myFun);
    }
    // 信息框设置
    var searchInfoWindow = null;
    searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
        title: title,      //标题
        width: 290,             //宽度
        height: 105,              //高度
        panel: "panel",         //检索结果面板
        enableAutoPan: true,     //自动平移
        searchTypes: [
            BMAPLIB_TAB_SEARCH,   //周边检索
            //BMAPLIB_TAB_TO_HERE,  //到这里去
            //BMAPLIB_TAB_FROM_HERE //从这里出发
        ]
    });

    // 自定义信息框设置
    /*var opts = {
     width : 200,     // 信息窗口宽度
     height: 100,     // 信息窗口高度
     title : title , // 信息窗口标题
     enableMessage:true//设置允许信息窗发送短息
     }
     var infoWindow = new BMap.InfoWindow(addr, opts);  // 创建信息窗口对象
     */  // 点击标注开启信息窗口
    pointMarker.addEventListener("click", function (e) {
        //map.openInfoWindow(infoWindow,point);
        searchInfoWindow.open(pointMarker);
    });
    // 拖拽标注获取经纬度
    pointMarker.addEventListener("dragend", function (e) {
        longt = e.point.lng;
        lat = e.point.lat;
        $("#long_la_titude").val(longt + "," + lat);
        $("#latitude").val(lat);
        $("#longtitude").val(longt);
    });
    // 点击地图上某一点获取此经纬度
    map.addEventListener("click", function (e) {
        longt = e.point.lng;
        lat = e.point.lat;
        $("#long_la_titude").val(longt + "," + lat);
        $("#latitude").val(lat);
        $("#longtitude").val(longt);

    });

    //地图事件设置
    map.enableDragging();//启用地图拖拽事件，默认启用(可不写)  
    map.enableScrollWheelZoom();//启用地图滚轮放大缩小  
    map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)  
    map.enableKeyboard();//启用键盘上下左右键移动地图  

    //地图控件添加
    //向地图中添加缩放控件  
    var ctrl_nav = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_LEFT, type: BMAP_NAVIGATION_CONTROL_LARGE});
    map.addControl(ctrl_nav);
    //向地图中添加缩略图控件
    var ctrl_ove = new BMap.OverviewMapControl({anchor: BMAP_ANCHOR_BOTTOM_RIGHT, isOpen: 1});
    map.addControl(ctrl_ove);
    //向地图中添加比例尺控件
    var ctrl_sca = new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT});
    map.addControl(ctrl_sca);
} 