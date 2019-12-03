

//实例化信息窗体
var title = '<span class="iconfont icon-liebiao" style="font-size:11px;color:#000000;"></span><span>    管道详细信息</span>';
var content = [];
content.push("<img src='http://tpc.googlesyndication.com/simgad/5843493769827749134'>地址：北京市朝阳区阜通东大街6号院3号楼东北8.3公里");
content.push("电话：010-64733333");
content.push("<a href='https://ditu.amap.com/detail/B000A8URXB?citycode=110105'>详细信息</a>");


//初始化窗口元素
function infoWindow(content) {
    return new AMap.InfoWindow({
        isCustom: true,  //使用自定义窗体
        content: createInfoWindow(title, content.join("<br/>")),
        offset: new AMap.Pixel(0,0)
    });

}

//自定义信息窗体顶部
function createInfoWindowTop(info,title) {
    // 定义顶部标题
    var top = document.createElement("div");
    var titleD = document.createElement("div");
    var closeX = document.createElement("img");
    top.className = "info-top";
    titleD.innerHTML = title;
    closeX.src = "https://webapi.amap.com/images/close2.gif";
    closeX.onclick = closeInfoWindow;
    top.appendChild(titleD);
    top.appendChild(closeX);
    info.appendChild(top);
}

//自定义信息窗体中部信息
function createInfoWindowMiddle(info,content) {
    // 定义中部内容
    var middle = document.createElement("div");
    middle.className = "info-middle";
    middle.style.backgroundColor = 'white';
    middle.innerHTML = content;
    middle.style.width="800px";
    middle.style.height="400px";
    initcharts("main",111,middle);//测试随便写的参数
    info.appendChild(middle);
}
//自定义信息窗体底部信息
function createInfoWindowBottom(info) {
    // 定义底部内容
    var bottom = document.createElement("div");
    bottom.className = "info-bottom";
    bottom.style.position = 'relative';
    bottom.style.top = '0px';
    bottom.style.margin = '0 auto';
    var sharp = document.createElement("img");
    sharp.src = "https://webapi.amap.com/images/sharp.png";
    bottom.appendChild(sharp);
    info.appendChild(bottom);
}

//构建自定义信息窗体
function createInfoWindow(title, content) {
    var info = document.createElement("div");
    info.className = "custom-info input-card content-window-card";//规定通用样式
   /* info.style.width = "400px";
    info.style.height = "400px"*/;
    createInfoWindowTop(info,title);
    createInfoWindowMiddle(info,content);
    createInfoWindowBottom(info);
    return info;
}

//关闭信息窗体
function closeInfoWindow() {
    map.clearInfoWindow();
}

/**
 *  初始化管道水位图表
 * @param domFlag  图标元素id
 * @param option    图表配置和数据JSON
 * @param parentDom  父元素
 */
function initcharts(domFlag,option,parentDom) {
    var chartDom=document.createElement("div");
    chartDom.style.width="700px";
    chartDom.style.height="300px";
    var myChart = echarts.init(chartDom);
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '管道水位变化'
        },
        tooltip: {},
        legend: {
            data:['水位值/cm']
        },
        xAxis: {
            data: ["2019/9/19","2019/9/20","2019/9/21","2019/9/22","2019/9/23","2019/9/24"]
        },
        yAxis: {},
        series: [{
            name: '水位值/cm',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    //把创建好的图标放到父元素中
    parentDom.appendChild(chartDom);
}


