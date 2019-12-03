package cn.enilu.material.bean.vo.well;

import lombok.Data;

@Data
public class PipelineWaterLevelVo {
    private int pipelineId;//管道Id
    private double startWaterLevel;//起点井对应管内水位百分比
    private double startAboverWaterLevel;//起点井超出管内水位高度
    private double endWaterLevel;//终点井对应管内水位百分比
    private double endAboverWaterLevel;//终点井超出管内水位高度
}
