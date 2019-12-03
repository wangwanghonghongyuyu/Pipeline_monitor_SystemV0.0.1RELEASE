package cn.enilu.material.bean.entity.system;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "t_sys_device")
@Table(appliesTo = "t_sys_device", comment = "设备表")
@Data
public class Device {
    @Id
    @GeneratedValue
    private int deviceId;//设备ID
    @Column(name = "code")
    private String code;//设备Code
    @Column(name = "physics_code")
    private String physicsCode;//物理设备
    @Column(name = "height")
    private Double height;
}
