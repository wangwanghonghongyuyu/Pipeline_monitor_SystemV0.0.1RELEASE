package cn.enilu.material.admin.runner;

import cn.enilu.material.service.well.InitDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
@Component
public class InitData implements ApplicationRunner {


    @Autowired
    private InitDataService initDataService;

    public void run(ApplicationArguments args) throws Exception {
        initDataService.init();
    }
}
