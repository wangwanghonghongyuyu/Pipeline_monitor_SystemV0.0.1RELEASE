package cn.enilu.material.service.well;

import cn.enilu.material.bean.entity.system.Device;
import cn.enilu.material.bean.entity.system.Pipeline;
import cn.enilu.material.bean.entity.system.Well;
import cn.enilu.material.bean.vo.well.PipelineWaterLevelVo;
import cn.enilu.material.bean.vo.well.WellWaterLevelVo;
import cn.enilu.material.utils.Log;
import cn.enilu.material.utils.StringUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.iot.model.v20180120.InvokeDataAPIServiceRequest;
import com.aliyuncs.iot.model.v20180120.InvokeDataAPIServiceResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WellWaterLevelService {
    Logger log= Log.get(WellWaterLevelService.class);
    @Value("${aliyun.device.data.name}")
    private String devDataName;
    @Value("${aliyun.device.length}")
    private String length;
    @Value("${aliyun.device.paramName}")
    private String paramKey;
    public static final String DEIVCENAME = "deviceName";
    public static final String UPDATEDATE = "updateTime";
    public static final String WATERDATA = "waterData";

    public List<WellWaterLevelVo> findWellWaterLevel() {
        List<WellWaterLevelVo> levelList = new ArrayList<>();
        Map<Integer, Well> wellMap = InitDataService.wellMap;
        if (wellMap != null && wellMap.size() > 0) {
            for (Well entity : wellMap.values()) {
                if (entity.getType() != 2) {
                    if (entity.getDeviceId()!= null) {
                        levelList.add(convertVo(entity));//转换成Vo
                    }
                }
            }
        }
        return levelList;
    }


    public List<PipelineWaterLevelVo> findPipelineWaterLevel() {
        List<PipelineWaterLevelVo> levelList = new ArrayList<>();
        Map<Integer, Pipeline> pipelineMap = InitDataService.pipelineMap;
        if (pipelineMap != null && pipelineMap.size() > 0) {
            for (Pipeline entity : pipelineMap.values()) {
                PipelineWaterLevelVo pipelineWaterLevelVo = new PipelineWaterLevelVo();
                Well endWell = InitDataService.wellMap.get(entity.getEndWellId());
                Well startWell = InitDataService.wellMap.get(entity.getStartWellId());
                boolean endWellDataFlag = setEndWellData(endWell, entity, pipelineWaterLevelVo);//终点井数据填写
                boolean startWellDataFlag = setStartWellData(startWell, entity, pipelineWaterLevelVo);//起点井数据填写
                levelList.add(pipelineWaterLevelVo);
            }
        }
        return levelList;
    }

    public void setPipelineWaterLevel() {
        Map<Integer, Pipeline> pipelineMap = InitDataService.pipelineMap;
        if (pipelineMap != null && pipelineMap.size() > 0) {
            for (Pipeline entity : pipelineMap.values()) {
                Well endWell = InitDataService.wellMap.get(entity.getEndWellId());
                Well startWell = InitDataService.wellMap.get(entity.getStartWellId());
                setEndWellData(endWell, entity);//终点井数据填写
                setStartWellData(startWell, entity);//起点井数据填写
            }
        }
    }

    //终点井数据填写
    private void setEndWellData(Well endWell, Pipeline entity) {
        double diameter = entity.getDiameter();//管道直径
        double waterLevel = endWell.getWaterLevel();//水位信息16进制  -10进制
        double waterLevelPercent = (waterLevel / diameter) * 100;//水位百分比
        double aboverWaterLevel = waterLevel - diameter;//超出管内水位高度
        entity.setEndAboverWaterLevel(aboverWaterLevel);
        entity.setEndWaterLevel(waterLevelPercent);
    }

    //起点井数据填写
    private void setStartWellData(Well startWell, Pipeline entity) {
        double diameter = entity.getDiameter();//管道直径
        double waterLevel = startWell.getWaterLevel();//水位信息16进制  -10进制
        double waterLevelPercent = (waterLevel / diameter) * 100;//水位百分比
        double aboverWaterLevel = waterLevel - diameter;//超出管内水位高度
        entity.setStartAboverWaterLevel(aboverWaterLevel);
        entity.setStartWaterLevel(waterLevelPercent);
    }


    /**
     * 管道终点井盖数据
     *
     * @param endWell
     * @param entity
     * @param pipelineWaterLevelVo
     */
    private boolean setEndWellData(Well endWell, Pipeline entity, PipelineWaterLevelVo pipelineWaterLevelVo) {
        Double diameter = entity.getDiameter();//管道直径
        pipelineWaterLevelVo.setPipelineId(entity.getPipelineId());
        Double endWellWaterLevel = endWell.getWaterLevel();
        if (diameter != null && endWellWaterLevel != null) {
            double waterLevel = endWell.getWaterLevel();//水位信息16进制  -10进制
            double waterLevelPercent = (waterLevel / diameter) * 100;//水位百分比
            double aboverWaterLevel = waterLevel - diameter;//超出管内水位高度
            pipelineWaterLevelVo.setEndAboverWaterLevel(aboverWaterLevel);
            pipelineWaterLevelVo.setEndWaterLevel(waterLevelPercent);
            return true;
        }
        return false;
    }

    /**
     * 管道起点数据
     *
     * @param endWell
     * @param entity
     * @param pipelineWaterLevelVo
     */
    private boolean setStartWellData(Well endWell, Pipeline entity, PipelineWaterLevelVo pipelineWaterLevelVo) {
        Double diameter = entity.getDiameter();//管道直径
        pipelineWaterLevelVo.setPipelineId(entity.getPipelineId());
        Double endWellWaterLevel = endWell.getWaterLevel();
        if (diameter != null && endWellWaterLevel != null) {
            double waterLevel = endWellWaterLevel;//水位信息16进制  -10进制
            double waterLevelPercent = (waterLevel / diameter) * 100;//水位百分比
            double aboverWaterLevel = waterLevel - diameter;//超出管内水位高度
            pipelineWaterLevelVo.setStartAboverWaterLevel(aboverWaterLevel);
            pipelineWaterLevelVo.setStartWaterLevel(waterLevelPercent);
            return true;
        }
        return false;
    }

    /**
     *
     * @param paramName
     * @param deviceList
     * @return
     */
    public Map<Integer,String> getDataObjectByDevice(String paramName, List<Device> deviceList) {
        Map<Integer,String>  deviceMap = new HashMap<>();
        String accessKey = "LTAI4Fhwfi6QKHRaJxbAeeU9";
        String accessSecret = "Joehcqx1HkAjU4op34Oji85KjZOD2F";
        try {
            DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Iot", "iot.cn-shanghai.aliyuncs.com");
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, accessSecret);
        DefaultAcsClient client = new DefaultAcsClient(profile); //初始化SDK客户端
        String apiSrn = "acs:iot:*:1398267758426727:serveapi/waterdata";
        InvokeDataAPIServiceRequest.Param param = new InvokeDataAPIServiceRequest.Param();
        InvokeDataAPIServiceRequest request = new InvokeDataAPIServiceRequest();
        for (Device device : deviceList) {
            // 请求参数名称
            param.setParamName(paramName);
            // 设备名
            param.setParamValue(device.getPhysicsCode());//设备名称 例如：text0001
            request.setApiSrn(apiSrn);
            request.setParams(Arrays.asList(param));
            // 当param为空时用请求方式用GET，如果不为空是用POST
            request.setParams(Arrays.asList(param));
            request.setSysMethod(MethodType.POST);

            try {
                InvokeDataAPIServiceResponse response = client.getAcsResponse(request);

                System.out.println("API Success : " + response.getSuccess());
                System.out.println("ErrorMessage : " + response.getErrorMessage());

                // 服务API指定的SQL查询结果
                List<Map<Object, Object>> resList = response.getData().getResultList();
                if (null != resList) {
                    for (Map<Object, Object> rMap : resList) {
                        System.out.println("API Result : 更新时间=" + rMap.get(UPDATEDATE) + "  设备名=" + String.valueOf(rMap.get(DEIVCENAME)) + "  RTU采样值=" + String.valueOf(rMap.get(WATERDATA)));
                        deviceMap.put(device.getDeviceId(),String.valueOf(rMap.get(WATERDATA)));
                    }
                }
            } catch (ClientException ce) {
                ce.printStackTrace();
            }
        }
        return deviceMap;
    }

    /**
     * 转换成Vo
     *
     * @return
     */
    public WellWaterLevelVo convertVo(Well well) {
        WellWaterLevelVo vo = new WellWaterLevelVo();
        vo.setId(well.getWellId());
        vo.setPointNo(well.getPointNo());//井盖号
        vo.setWaterLevel(getPercentWaterLevel(well));//水位信息 百分比
        vo.setHeightToGround(getHeightToGround(well));//水位
        vo.setOverFlow(isOverFlow(vo));
        String deviceCode = well.getDeviceCode();
        if (deviceCode!= null) {
            vo.setDeviceCode(deviceCode);//设备code
        }
        return vo;
    }


    //把水位信息放入内存对象中
    public void setWellWaterLevelInfo(Map<Integer,String> aLiYunData) {
        for (Well well:InitDataService.wellMap.values()){
            String waterLevelData=aLiYunData.get(well.getDeviceId());
            if(waterLevelData != null){
                if (waterLevelData.length() == Integer.parseInt(length)) {
                    double waterLevel = getWaterLevel(StringUtil.subCmd(waterLevelData, 8, 12));//水位信息16进制  -10进制
                    well.setWaterLevel(waterLevel);//水位
                }
            }
        }
    }

    /**
     * 获得水位高度
     * @param well
     * @return
     */
    private Double getHeightToGround(Well well) {
        Device device = InitDataService.deviceMap.get(well.getDeviceId());
        Double actualLevel=well.getWaterLevel();
        if (actualLevel!=null) {
            return device.getHeight() + actualLevel;
        }
        return null;
    }

    /**
     * 获得水位百分比
     * @param well
     * @return
     */
    private Double getPercentWaterLevel(Well well) {
        Double actualLevel=well.getWaterLevel();//实时水位信息
        Double elevation=well.getElevation();//井深度
        Device device=InitDataService.deviceMap.get(well.getDeviceId());//井绑定的设备
        Double diameter=well.getDiameter();//井盖下对应的管道直径
        double waterLevel=0;
        if (actualLevel!=null&&elevation!=null&&device!=null&&diameter!=null){
            waterLevel =(((elevation/1000)- (device.getHeight()/1000) - actualLevel)/(diameter/1000)) * 100;
            if (waterLevel > 100) {
                waterLevel = 100;
            }
        }
        return waterLevel;
    }

    /**
     * 计算水位转换
     *
     * @param data
     * @return
     */
    public double getWaterLevel(String data) {
        double level = Integer.parseInt(data, 16);//原始数据
        return 5 * level / 2000;//换算成m
    }

    /**
     * 是否溢流
     *
     * @param vo
     * @return
     */
    public boolean isOverFlow(WellWaterLevelVo vo) {
        Double heightToGround = vo.getHeightToGround();//水位到井口距离=井深-水位
        if (heightToGround!=null){
            if (heightToGround <= 1.5) {//水位到井口距离<=1.5米时判定为可能溢流，isOverFlow=true，否则为false
                return true;
            }
        }
        return false;
    }


    public List<Device> getValidDevices() {
        Map<Integer, Well> wellMap = InitDataService.wellMap;
        List<Device> deviceList =new ArrayList<>();
        if (wellMap != null && wellMap.size() > 0) {
            for (Well entity : wellMap.values()) {
                Device device = InitDataService.deviceMap.get(entity.getDeviceId());
                if (device!=null){
                    deviceList.add(device);
                }
            }
        }
        return deviceList;
    }
}
