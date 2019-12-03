package cn.enilu.material.bean.entity.system;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "t_sys_road")
@Table(appliesTo = "t_sys_road")
@Data
public class Road {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private Integer coordinateId;
    private Integer type;
}
