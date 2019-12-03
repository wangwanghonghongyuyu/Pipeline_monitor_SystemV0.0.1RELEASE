package cn.enilu.material.bean.vo.well;

import lombok.Data;

@Data
public class WellWaterLevelVo {
    private int id;//井盖id
    private String pointNo;//井点号
    private double waterLevel;//水位百分比
    private String deviceCode;//探测仪设备Code
    private boolean isOverFlow;//是否溢流
    private Double heightToGround;//水位
}
