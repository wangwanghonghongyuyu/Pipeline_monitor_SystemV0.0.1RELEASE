package cn.enilu.material.service.well;

import cn.enilu.material.bean.entity.system.Pipeline;
import cn.enilu.material.bean.entity.system.Well;
import cn.enilu.material.bean.vo.well.CoordinateVo;
import cn.enilu.material.bean.vo.well.PipelinesVo;
import cn.enilu.material.dao.well.PipeLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PipelineService {
    @Autowired
    private PipeLineRepository pipeLineRepository;
    @Autowired
    private CoordinateService coordinateService;

    public List<Pipeline> findAllPipelineEntity() {
        return pipeLineRepository.findAll();
    }

    public PipelinesVo findPipelinesById(int id) {
        PipelinesVo domain = new PipelinesVo();
        Pipeline entity = InitDataService.pipelineMap.get(id);
        domain.setId(entity.getPipelineId());//管道ID
        if (entity.getDiameter() != null) {
            domain.setDiameter(entity.getDiameter());//管线直径
        }
        if (entity.getDistance() != null) {
            domain.setDistance(entity.getDistance());//管线长度
        }
        domain.setRoad(entity.getAttr_road());//所属道路
        if (entity.getAttr_material() != null) {
            domain.setMaterial(entity.getAttr_material());//管线材质
        }
        domain.setCreateDate(entity.getCreateDate());//建设年代
        domain.setRemark(entity.getRemark());//备注

        getPath(entity.getStartWellId(), entity.getEndWellId(), domain);
        domain.setType(entity.getType());//雨水管或者是污水管线
        domain.setMainOrBranch(entity.getMainOrBranCh());
        domain.setTotalDistance(entity.getTotalDistance());
        domain.setTypeStr(entity.getTypeStr());

        return domain;
    }

    public List<PipelinesVo> findAllPipelineVo(String road) {
        Map<Integer, Pipeline> pipelineMap = InitDataService.pipelineMap;
        List<PipelinesVo> pipelinesVoList = new ArrayList<>();
        if (pipelineMap != null && pipelineMap.size() > 0) {
            for (Pipeline pipeline : pipelineMap.values()) {
                if (road != null) {
                    if (road.equals(pipeline.getAttr_road())) {
                        pipelinesVoList.add(findPipelinesById(pipeline.getPipelineId()));
                    }
                } else {
                    pipelinesVoList.add(findPipelinesById(pipeline.getPipelineId()));
                }
            }
        }
        return pipelinesVoList;
    }

    //起点井坐标
    public void getPath(int startWellId, int endWellId, PipelinesVo domain) {
        Well startWell = InitDataService.wellMap.get(startWellId);
        Well endWell = InitDataService.wellMap.get(endWellId);
        CoordinateVo startWellCoorinate = coordinateService.findCoordinateById(startWell.getCoordinateId());
        CoordinateVo endWellCoorinate = coordinateService.findCoordinateById(endWell.getCoordinateId());
        double[][] path = {{startWellCoorinate.getLongitude(), startWellCoorinate.getLatitude()}, {endWellCoorinate.getLongitude(), endWellCoorinate.getLatitude()}};
        domain.setPath(path);
    }

}
