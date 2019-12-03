package cn.enilu.material.bean.vo.well;

import lombok.Data;

/**
 * 井盖前端封装Bean
 */
@Data
public class WellVo {
    private int id;
    private String pointNo;//井盖Code
    private CoordinateVo coordinateBean;//位置点
    private String deviceCode;//设备Code
    private double elevation;//井深
    private Double diameter;//井直径
    private String material;//井盖材质
    private boolean  valveStatus;//阀门开启状态 0 是关闭 1 是开放
    private int type;//井盖类型 1 雨水 2污水
    private boolean isBranch;//是否为支线
    private String remark;//备注
    private String road;
    private String typeStr;
    private double []coordinate;
}
