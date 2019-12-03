package cn.enilu.material.bean.vo.well;

import lombok.Data;

/**
 * 位置点前端封装Bean
 */
@Data
public class CoordinateVo {
    private double latitude;//纬度
    private double longitude;//经度
    private double elevation;//点高程
}
