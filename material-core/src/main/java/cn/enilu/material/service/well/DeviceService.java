package cn.enilu.material.service.well;

import cn.enilu.material.bean.entity.system.Device;
import cn.enilu.material.dao.well.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> findAllDeviceEntity(){
        return deviceRepository.findAll();
    }
}
