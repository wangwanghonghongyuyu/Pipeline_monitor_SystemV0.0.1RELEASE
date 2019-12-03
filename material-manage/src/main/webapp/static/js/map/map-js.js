AMapUI.load(['lib/$'], function ($) {
    // $ 即为UI组件库最终使用的DomLibrary
});
//地图初始化配置JSON对象
var mapConfigJson = {
    resizeEnable: true, //是否监控地图容器尺寸变化
    zoom: 18, //初始化地图层级
    center: [121.51584972750854, 38.862162416370765], //初始化地图中心点  三丰大厦
    pitch: 60,// 地图俯仰角度，有效范围 0 度- 83 度
    viewMode: '3D',//使用3D视图
    lang: 'zh_cn',  //设置地图语言类型
    zoomEnable: false,//缩放禁用
    pitchEnable: false,//是否允许设置俯仰角度，3D视图下为true，2D视图下无效。（自V1.4.0开始支持）
};
var map = new AMap.Map('container', mapConfigJson);//初始化地图

markerCenterIcon();//标记中心点

//画四条线段
var polyline1 = new AMap.Polyline(redLinePath(line1));
polyline1.setExtData("001");
var polyline2 = new AMap.Polyline(blueLinePath(line2));
polyline2.setExtData("002");
var polyline3 = new AMap.Polyline(redLinePath(line3));
polyline3.setExtData("003");
var polyline4 = new AMap.Polyline(blueLinePath(line4));
polyline4.setExtData("004");
var allLineGroup = polyLineLineArrayBuilder(polyline1, polyline2, polyline3, polyline4)
map.add(polyLineLineArrayBuilder(polyline1, polyline2, polyline3, polyline4));


//定位 AMap.Geolocation
AMap.plugin('AMap.Geolocation',function(){//异步加载插件
    var geolocation = new AMap.Geolocation();
    map.addControl(geolocation);
});
// map.add(polyline2);
/*
3D图层用来承载Object3D对象
var object3Dlayer = new AMap.Object3DLayer();
map.add(object3Dlayer);*/

/*//创建矩形
var rectangle = new AMap.Object3D.Mesh();
var geometry = rectangle.geometry;//创建之后获取geometry
var lnglat1 = new AMap.LngLat(121.51709427249145,38.86169458575808);
var lnglat2 = new AMap.LngLat(121.51814569842529,38.86283074047742);
var v0xy = map.lngLatToGeodeticCoord(lnglat1);
var v1xy = map.lngLatToGeodeticCoord(lnglat2);
var z = -1000;//3D地图Z方向朝下，所以负值
geometry.vertices.push(v0xy.x, v0xy.y, 0);//V0
geometry.vertices.push(v1xy.x, v1xy.y, 0);//V1
geometry.vertices.push(v0xy.x, v0xy.y, z);//V3
geometry.vertices.push(v1xy.x, v1xy.y, z);//V2
geometry.faces.push(0, 1, 3);
geometry.faces.push(1, 2, 3);
geometry.vertexColors.push(1, 0, 0, 1); //V0
geometry.vertexColors.push(0, 1, 0, 1); //V1
geometry.vertexColors.push(0, 0, 1, 1); //V2
geometry.vertexColors.push(0, 1, 1, 1); //V3
rectangle.transparent = false;
rectangle.backOrFront = 'both';//'back'、'front'、'both'
object3Dlayer.add(rectangle)*/
/*
三丰大厦下的棱柱
var bounds = [
    new AMap.LngLat(121.51709427249145,38.86169458575808),
    new AMap.LngLat(121.51741613757324,38.860859166295086),
    new AMap.LngLat(121.5179311217041,38.86136041915103),
    //[121.62, 38.9223],[121.62,38.9226],[121.62,38.9278],[121.62,38.9287],
];
var height = 500;
var color = '#0088ff';//rgba
var prism = new AMap.Object3D.Prism({
    path:bounds,
    height:height,
    color:color
});
object3Dlayer.add(prism);//添加*/

/*//画一条线段
var path = [
   [121.62, 38.9223],[121.62,38.9226],[121.62,38.9278],[121.62,38.9287],
];
var polyline = new AMap.Polyline({
    path: path,
    borderWeight: 2, // 线条宽度，默认为 1
    strokeColor: 'red', // 线条颜色
    lineJoin: 'round' // 折线拐点连接处样式
});
map.add(polyline);*/

/*
获取当前地图中心点数据
var currentCenter =map.getCenter();//获取地图中心点 是个json数组
console.log("获取地图中心点"+JSON.stringify(currentCenter));*/


/*
地图销毁
map.destroy( );*/

/*
加载完成函数调用
map.on('complete', function(){
    // 地图图块加载完成后触发
    var mapSize = map.getSize();//获取地图大小，返回的是地图容器的像素大小
    var width = mapSize.width;
    var height = mapSize.height;
    console.log("地图加载完成！！！ 地图宽:"+width+"地图高:"+height);
});*/

/*
像素尺寸
var mapSize = map.getSize();//获取地图大小，返回的是地图容器的像素大小
var width = mapSize.width;
var height = mapSize.height;
console.log("地图宽:"+width+"地图高:"+height);
var marker = new AMap.Marker({
    position: [121.62, 38.92],
    icon: new AMap.Icon({
        size: new AMap.Size(40, 50),  //图标的大小
        image: "https://webapi.amap.com/theme/v1.3/images/newpc/way_btn2.png",
        imageOffset: new AMap.Pixel(0, -60)
    })
});
map.add(marker)*/

/*
像素点
var offset  = new AMap.Pixel(0,0);
var marker = new AMap.Marker({
    offset:offset,
    icon:'../img/map.png',
});
map.add(marker);*/


/*
多个插架添加
                字符串数组--插件名称           实例化插架的匿名方法
AMap.plugin(['AMap.ToolBar','AMap.Driving'],function(){//异步同时加载多个插件
    var toolbar = new AMap.ToolBar();
    map.addControl(toolbar);
    var driving = new AMap.Driving();//驾车路线规划
    driving.search(/!*参数*!/)
});*/

/*
单个插件
AMap.plugin('AMap.PolyEditor',function(){//异步加载插件
    var polyEditor = new AMap.PolyEditor();//插件组件对象创建
    map.addControl(polyEditor);//添加插件到Map对象中
});*/
/*
事件功能与信息窗体
var infoWindow = new AMap.InfoWindow({ //创建信息窗体
    isCustom: true,  //使用自定义窗体
    content:'<div>信息窗体</div>', //信息窗体的内容可以是任意html片段
    offset: new AMap.Pixel(16, -45)
});
var marker = new AMap.Marker({
    position:[116.39, 39.9]//位置
});
var onMarkerClick  =  function(e) {
    infoWindow.open(map, e.target.getPosition());//打开信息窗体
    //e.target就是被点击的Marker
}
map.add(marker);
marker.on('click',onMarkerClick);//绑定click事件*/

/*
画几何图形  线段 矩形 圆形等
可以考虑用这个画线路  线路配置数据提出来尽量进行便捷阅读性高的编写
var lineArr = [
    [116.368904, 39.913423],
    [116.382122, 39.901176],
    [116.387271, 39.912501],
    [116.398258, 39.904600]
];

var polyline = new AMap.Polyline({
    path: lineArr,          //设置线覆盖物路径
    strokeColor: "#3366FF", //线颜色
    strokeWeight: 5,        //线宽
    strokeStyle: "solid",   //线样式
});
map.add(polyline);*/

/*
标记点
var marker = new AMap.Marker({
    position:[116.39, 39.9]//位置
});
map.add(marker);*/

/*
实时路况图层初始化
var trafficLayer =new AMap.TileLayer.Traffic({
    zIndex:10
});

map.add(trafficLayer);//添加图层到地图*/


//隐藏高德地图的footer 注意放到最后执行
hiddenAmpFooter();

