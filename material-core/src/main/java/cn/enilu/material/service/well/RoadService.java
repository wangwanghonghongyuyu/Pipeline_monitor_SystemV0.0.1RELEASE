package cn.enilu.material.service.well;

import cn.enilu.material.bean.entity.system.Coordinate;
import cn.enilu.material.bean.entity.system.Road;
import cn.enilu.material.bean.vo.well.RoadVo;
import cn.enilu.material.dao.well.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoadService {
    @Autowired
    private RoadRepository repository;

    public List<Road> findAllRoadEntity() {
        return repository.findAll();
    }

    public List<RoadVo> findAllRoadVo() {
        List<Road> roadList=findAllRoadEntity();
        List<RoadVo> roadVoList=new ArrayList<>();
        if (roadList!=null&&roadList.size()>0){
            for (Road roadEntity:roadList){
                RoadVo roadVo=new RoadVo();
                roadVo.setTitle(roadEntity.getTitle());
                roadVo.setType(roadEntity.getType());
                Coordinate coordinate=InitDataService.coordinateMap.get(roadEntity.getCoordinateId());
                if (coordinate!=null){
                    double[] position={ coordinate.getLongitude(),coordinate.getLatitude()};
                    roadVo.setPosition(position);
                }
                roadVoList.add(roadVo);
            }
        }
        return roadVoList;
    }
}
