package cn.enilu.material.service.well;

import cn.enilu.material.bean.entity.system.Coordinate;
import cn.enilu.material.bean.entity.system.Device;
import cn.enilu.material.bean.entity.system.Pipeline;
import cn.enilu.material.bean.entity.system.Well;
import cn.enilu.material.bean.vo.well.CoordinateVo;
import cn.enilu.material.bean.vo.well.WellVo;
import cn.enilu.material.dao.well.WellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WellService {
    @Autowired
    private WellRepository wellRepository;
    @Autowired
    private CoordinateService coordinateService;

    /**
     * 查询所有井盖DB信息
     *
     * @return
     */
    public List<Well> findAllWellEntity() {
        return wellRepository.findAll();
    }


    public WellVo findWellByWellId(int wellId) {
        WellVo well = new WellVo();
        Well entity = InitDataService.wellMap.get(wellId);
        if (entity != null) {
            well.setId(entity.getWellId());
            well.setPointNo(entity.getPointNo());//井盖Code
            well.setTypeStr(entity.getTypeStr());
            Device device = InitDataService.deviceMap.get(entity.getDeviceId());
            if (device != null) {
                well.setDeviceCode(device.getCode());//设备Code
            }
            if (entity.getElevation() != null) {
                well.setElevation(entity.getElevation());//井深
            }
            if (entity.getDiameter() != null) {
                well.setDiameter(entity.getDiameter());//井的直径
            }
            if (entity.getAttr_material() != null) {
                well.setMaterial(entity.getAttr_material());//井盖材质
            }
            well.setRoad(entity.getRoad());//所属道路
            well.setValveStatus(entity.getValveStatus() == 0 ? false : true);//阀门开启状态 false 是关闭 true是开放
            well.setType(entity.getType());// 井盖类型 1雨水 2污水
            well.setBranch(entity.getIsBranch() == 0 ? false : true);//false 不是支线  true 支线
            if (entity.getRemark() != null) {
                well.setRemark(entity.getRemark());//备注
            }
            CoordinateVo coordinateVo=coordinateService.findCoordinateById(entity.getCoordinateId());
            double [] coordinateArr={coordinateVo.getLongitude(),coordinateVo.getLatitude()};
            well.setCoordinate(coordinateArr);//位置点
            calculationOfTheArrow(entity,well);//计算箭头方向
        }
        return well;
    }

    /**
     * 计算箭头方向
     * @return
     */
    public void calculationOfTheArrow(Well well,WellVo vo){
        double[] coordinate2=new double[2];
        for (Pipeline pipeline:InitDataService.pipelineMap.values()){
            Integer startWellId=pipeline.getStartWellId();
            if (startWellId!=null){
                if (startWellId==well.getWellId()){
                    Coordinate startCoordinate =InitDataService.coordinateMap.get(well.getCoordinateId());
                    Well endWell=InitDataService.wellMap.get(pipeline.getEndWellId());
                    Coordinate endCoordinate=InitDataService.coordinateMap.get(endWell.getCoordinateId());
                    double x1=startCoordinate.getLongitude();
                    double x2=endCoordinate.getLongitude();
                    double y1=startCoordinate.getLatitude();
                    double y2=startCoordinate.getLatitude();
                    double x=x1+(x2-x1)/1000000;
                    double y=y1+(((x-x1)*(y2-y1))/(x2-x1));
                    coordinate2[0]=x;
                    coordinate2[1]=y;
                    vo.setCoordinate2(coordinate2);
                    return ;
                }
            }
        }
    }

    /**
     * 所有井盖信息
     *
     * @param road
     * @return
     */
    public List<WellVo> findAllWellVo(String road) {
        List<WellVo> wellVoList = new ArrayList<>();
        for (Well well : InitDataService.wellMap.values()) {
            int wellId = well.getWellId();
            WellVo wellVo = findWellByWellId(wellId);
            if (road!=null){
                if (wellVo.getRoad().equals(road)){//根据街道显示
                    wellVoList.add(wellVo);
                }
            }else {
                wellVoList.add(wellVo);//显示所有数据
            }

        }

        return wellVoList;
    }

}
