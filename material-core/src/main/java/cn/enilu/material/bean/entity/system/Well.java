package cn.enilu.material.bean.entity.system;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.naming.Name;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "t_sys_well")
@Table(appliesTo = "t_sys_well", comment = "井")
@Data
public class Well {
    @Id
    @GeneratedValue
    private int wellId;
    @Column(name = "pointNo")
    private String pointNo;
    @Column(name = "coordinateId")
    private Integer coordinateId;
    @Column(name = "deviceId")
    private Integer deviceId;
    @Column(name = "elevation")
    private Double elevation;
    @Column(name = "diameter" )
    private Double diameter;
    @Column(name = "attr_material")
    private String attr_material;
    @Column(name = "valveStatus")
    private Integer valveStatus;
    @Column(name = "isBranch")
    private Integer isBranch;
    @Column(name = "remark")
    private String remark;
    private String road;
    private Integer type;//
    private String typeStr;//雨水管 污水管*/
    @Transient
    private Double waterLevel;//水位
    @Transient
    private String deviceCode;//探测仪设备Code
}
