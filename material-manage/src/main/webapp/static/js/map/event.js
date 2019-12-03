/**
 * 给线路绑定窗口事件
 */
allLineGroup.eachOverlay(function (overlay, index, collections) {
    var id=overlay.getExtData();
    overlay.on('click',function (e) {
        var currentOnClick = e.lnglat;//点击位置经纬度坐标
        var detailLog = null;
        //TODO 根据管道id选择对应的详情信息
        if (id == '001') {
            detailLog = infoWindow(content1);
            //初始化每个线路的图表信息 参数id是变长的
           //initCharts("content1");
        } else if (id == '002'){
            detailLog = infoWindow(content2);
        } else if (id == '003'){
            detailLog = infoWindow(content3);
        }
        detailLog.open(map,currentOnClick);
    });
});
/**
 * 地图左边列表div显示事件 [初始化隐藏]
 */
$(".map-left-info-list").mouseover(function () {
    $(".info-detail-log").css("display","block");
});

/**
 * 地图左边列表div隐藏事件
 */
$(".map-left-info-list").mouseleave(function () {
    var display=$(".map-left-info-list").css("display");
    if (display=="block") {
        var flag=$(".map-left-info-list").attr("flag");
        console.log(flag);
        if (flag==2){
            $(".info-detail-log").hide();
        }else {
            $(".info-detail-log").show();
        }
    }
});
$(".info-detail-log").mouseover(function () {
    $(".map-left-info-list").attr("flag","2");

});
$(".info-detail-log").mouseleave(function () {
    $(".map-left-info-list").attr("flag","1");
    $(".info-detail-log").hide();
});



