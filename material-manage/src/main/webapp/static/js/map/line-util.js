//红色线路
var redLine={
    path: null,
    isOutline: true,
    outlineColor: '#ffeeff',
    borderWeight: 3,
    strokeColor: "red",
    strokeOpacity: 1,
    strokeWeight: 20,
    // 折线样式还支持 'dashed'
    strokeStyle: "solid",
}
var blueLine={
    path: null,
    isOutline: true,
    outlineColor: '#ffeeff',
    borderWeight: 3,
    strokeColor: "#3366FF",
    strokeOpacity: 1,
    strokeWeight: 20,
    // 折线样式还支持 'dashed'
    strokeStyle: "solid",
}
function redLinePath(path) {
    redLine.path=path;
    return redLine;
}
function blueLinePath(path) {
    blueLine.path=path;
    return blueLine;
}

function polyLineLineArrayBuilder(...lineData) {
    var polylineArr=[];
    for (let item of lineData){
        polylineArr.push(item);
    }
    var overlayGroup = new AMap.OverlayGroup(polylineArr);
    return overlayGroup;
}

function markerCenterIcon() {
    //标记位置
    var marker = new AMap.Marker({
        position:[121.51584972750854,38.862162416370765]//位置
    });
    map.add(marker);
}

function hiddenAmpFooter() {
    $(".amap-logo").hide();
    $(".amap-copyright").hide();
    $(".amap-copyright").css("visibility","hidden");
}