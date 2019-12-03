package cn.enilu.material.bean.vo.well;

import lombok.Data;

/**
 * 管道的前端封装Bean
 */
@Data
public class PipelinesVo {
    private int id;//管道ID
    private double diameter;//管线直径
    private String road;//所在道路
    private String material;//管线材质
    private double distance;//主线长度
    private String createDate;//建设年代
    private String  remark;//备注
    private Integer type;//雨水管或者是污水管线  1 雨水管线  2是污水管线
    private Integer mainOrBranch;//0 主线 1支线  默认主线 0
    private Double totalDistance;//主管线路长度
    private String typeStr;//雨水管 污水管
    private double[][] path;
}
