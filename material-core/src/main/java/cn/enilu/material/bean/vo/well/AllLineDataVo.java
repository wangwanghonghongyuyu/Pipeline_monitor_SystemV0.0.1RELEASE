package cn.enilu.material.bean.vo.well;

import cn.enilu.material.bean.entity.system.Road;
import lombok.Data;

import java.util.List;
@Data
public class AllLineDataVo<T,V> {
    List<T> wells;
    List<V> pipelines;
    List<RoadVo> roads;
}
