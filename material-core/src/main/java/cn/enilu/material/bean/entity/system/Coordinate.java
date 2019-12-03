package cn.enilu.material.bean.entity.system;


import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "t_sys_coordinate")
@Table(appliesTo = "t_sys_coordinate", comment = "位置坐标表")
@Data
public class Coordinate {
    @Id
    @GeneratedValue
    private int coordinateId;//坐标ID
    @Column(name = "longitude")
    private double longitude;//经度
    @Column(name = "latitude")
    private double latitude;//纬度
    @Column(name = "elevation")
    private double elevation;//点高程
}
