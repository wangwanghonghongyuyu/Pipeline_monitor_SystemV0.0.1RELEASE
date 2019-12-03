package cn.enilu.material.admin.modular.system.controller;

import cn.enilu.material.admin.core.base.controller.BaseController;
import cn.enilu.material.admin.modular.system.transfer.ResponseBean;
import cn.enilu.material.bean.constant.Const;
import cn.enilu.material.bean.entity.system.Well;
import cn.enilu.material.bean.vo.well.AllLineDataVo;
import cn.enilu.material.bean.vo.well.PipelineWaterLevelVo;
import cn.enilu.material.bean.vo.well.PipelinesVo;
import cn.enilu.material.bean.vo.well.RoadVo;
import cn.enilu.material.bean.vo.well.WellVo;
import cn.enilu.material.bean.vo.well.WellWaterLevelVo;
import cn.enilu.material.service.well.PipelineService;
import cn.enilu.material.service.well.RoadService;
import cn.enilu.material.service.well.WellService;
import cn.enilu.material.service.well.WellWaterLevelService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class WellController extends BaseController {
    @Autowired
    private WellService wellService;
    @Autowired
    private PipelineService pipelineService;
    @Autowired
    private WellWaterLevelService wellWaterLevelService;
    @Autowired
    private RoadService roadService;

    @RequestMapping("/getAllInfo")
    @ResponseBody
    public  ResponseBean getAllWell(@RequestParam(value = "road",required = false) String road){
        List<WellVo> wellList=wellService.findAllWellVo(road);
        List<PipelinesVo> pipelineList=pipelineService.findAllPipelineVo(road);
        List<RoadVo> roadVoList=roadService.findAllRoadVo();
        AllLineDataVo<WellVo,PipelinesVo> allLineDataVo=new AllLineDataVo();
        ResponseBean<AllLineDataVo> responseBean=new ResponseBean(Const.RESPONSE_RCODE.SUCCESS,allLineDataVo);
        if (wellList!=null&&wellList.size()>0){
            allLineDataVo.setWells(wellList);
        }
        if (pipelineList!=null&&pipelineList.size()>0){
            allLineDataVo.setPipelines(pipelineList);
            allLineDataVo.setRoads(roadVoList);
            return responseBean;
        }
        return new ResponseBean(Const.RESPONSE_RCODE.ERROR);
    }

    @RequestMapping("/getWaterRegiment")
    @ResponseBody
    public ResponseBean getWaterRegiment(){
        List<WellWaterLevelVo> wellList=wellWaterLevelService.findWellWaterLevel();//井盖水位信息
        AllLineDataVo<WellWaterLevelVo,PipelineWaterLevelVo> allLineDataVo=new AllLineDataVo();//水位信息封装对象
        ResponseBean<AllLineDataVo> responseBean=new ResponseBean(Const.RESPONSE_RCODE.SUCCESS,allLineDataVo);
        if (wellList!=null&&wellList.size()>0){
            allLineDataVo.setWells(wellList);
        }
        return responseBean;
    }
}
