package cn.enilu.material.service.well;

import cn.enilu.material.bean.entity.system.Coordinate;
import cn.enilu.material.bean.entity.system.Device;
import cn.enilu.material.bean.entity.system.Pipeline;
import cn.enilu.material.bean.entity.system.Road;
import cn.enilu.material.bean.entity.system.Well;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InitDataService {
    public static Map<Integer, Well> wellMap=new HashMap<>();// wellId ->  Well //井盖信息
    public static Map<Integer, Pipeline> pipelineMap=new HashMap<>();//pipelineId -> Pipeline//管道信息
    public static Map<Integer, Device> deviceMap=new HashMap<>(); // 设备id -> Device //设备信息
    public static Map<Integer, Coordinate> coordinateMap=new HashMap<>();//坐标信息
    public static Map<Integer, Road> RoadMap=new HashMap<>();//街道信息

    @Autowired
    private WellService wellService;
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private CoordinateService coordinateService;
    @Autowired
    private PipelineService pipelineService;
    @Autowired
    private RoadService roadService;

    public void init(){
        initWell();//初始化井盖信息
        initDevice();//初始化设备信息
        initCoordinate();//初始化位置点信息
        initPipeline();//初始化管道信息
        initRoad();
    }

    public void initWell(){
        List<Well> wellList=wellService.findAllWellEntity();
        if (wellList!=null&&wellList.size()>0){
            for (Well well:wellList){
                wellMap.put(well.getWellId(),well);
            }
        }
    }


    public void initDevice(){
        List<Device> deviceList=deviceService.findAllDeviceEntity();
        if (deviceList!=null&&deviceList.size()>0){
            for (Device device:deviceList){
                deviceMap.put(device.getDeviceId(),device);
            }
        }
    }

    public void initCoordinate(){
        List<Coordinate> coordinateList=coordinateService.findAllCoordinateEntity();
        if (coordinateList!=null&&coordinateList.size()>0){
            for (Coordinate coordinate:coordinateList){
                coordinateMap.put(coordinate.getCoordinateId(),coordinate);
            }
        }
    }

    public void initPipeline(){
        List<Pipeline> pipelineList=pipelineService.findAllPipelineEntity();
        if (pipelineList!=null&&pipelineList.size()>0){
            for (Pipeline pipeline:pipelineList){
                pipelineMap.put(pipeline.getPipelineId(),pipeline);
            }
        }
    }

    public void initRoad(){
        List<Road> roadList=roadService.findAllRoadEntity();
        if (roadList!=null&&roadList.size()>0){
            for (Road road:roadList){
                RoadMap.put(road.getId(),road);
            }
        }
    }
}
