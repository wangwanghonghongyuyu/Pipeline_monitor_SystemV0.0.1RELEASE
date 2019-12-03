package cn.enilu.material.bean.entity.system;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity(name = "t_sys_pipeline")
@Table(appliesTo = "t_sys_pipeline", comment = "管道信息表")
@Data
public class Pipeline {
    @Id
    @GeneratedValue
    private Integer pipelineId;//管道ID
   @Column(name = "startWellId")
    private Integer startWellId;//起点井ID
   @Column(name = "endWellId")
    private Integer endWellId;//终点井ID
   @Column(name = "coordinateAId")
    private Integer coordinateAId;//分割点A坐标ID
   @Column(name = "coordinateBId")
    private Integer coordinateBId;//分割点B坐标ID
   @Column(name = "diameter")
    private Double diameter;//管线直径
   @Column(name = "attr_material")
    private String attr_material;//管线材质
   @Column(name = "attr_road")
    private String attr_road;//所在道路
   @Column(name = "distance")
    private Double distance;//主线长度
   @Column(name = "createDate")
    private String createDate;//建设年代
   @Column(name = "remark")
    private String remark;//备注
    private Integer type;//雨水管或者是污水管线  1 雨水管线  2是污水管线
    private Integer mainOrBranCh;//0 主线 1支线  默认主线 0
    private Double totalDistance;//主管线路长度
    private String typeStr;//雨水管 污水管
    @Transient
    private Double startWaterLevel;//起点井对应管内水位百分比
    @Transient
    private Double startAboverWaterLevel;//起点井超出管内水位高度
    @Transient
    private Double endWaterLevel;//终点井对应管内水位百分比
    @Transient
    private Double endAboverWaterLevel;//终点井超出管内水位高度
}
