package cn.enilu.material.service.well;

import cn.enilu.material.bean.entity.system.Coordinate;
import cn.enilu.material.bean.vo.well.CoordinateVo;
import cn.enilu.material.dao.well.CoordinateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinateService {
    @Autowired
    private CoordinateRepository coordinateRepository;

    public List<Coordinate> findAllCoordinateEntity(){
        return coordinateRepository.findAll();
    }

    public CoordinateVo findCoordinateById(int id){
        CoordinateVo coordinateVo=new CoordinateVo();
        Coordinate coordinate=InitDataService.coordinateMap.get(id);
        coordinateVo.setElevation(coordinate.getElevation());
        coordinateVo.setLatitude(coordinate.getLatitude());
        coordinateVo.setLongitude(coordinate.getLongitude());
        return coordinateVo;
    }
}
