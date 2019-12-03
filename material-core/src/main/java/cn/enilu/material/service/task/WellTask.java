package cn.enilu.material.service.task;

import cn.enilu.material.bean.entity.system.Device;
import cn.enilu.material.service.well.WellWaterLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class WellTask {

    @Value("${aliyun.device.data.name}")
    private String devDataName;
    @Value("${aliyun.device.length}")
    private String length;
    @Value("${aliyun.device.paramName}")
    private String paramKey;
    @Autowired
    private WellWaterLevelService wellWaterLevelService;

    @Scheduled(fixedDelayString = "${task.execute.milisecond}")
    public void wellTask() {
            List<Device> deviceList =wellWaterLevelService.getValidDevices();
            Map<Integer,String> aLiYunData = wellWaterLevelService.getDataObjectByDevice(paramKey, deviceList);//获得阿里水位数据
            wellWaterLevelService.setWellWaterLevelInfo(aLiYunData);//井盖实时把阿里数据放到内存中的对象上
        }
    }
