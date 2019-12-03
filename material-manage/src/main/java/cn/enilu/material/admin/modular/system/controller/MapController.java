package cn.enilu.material.admin.modular.system.controller;

import cn.enilu.material.bean.constant.Const;
import cn.enilu.material.bean.core.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/map")
public class MapController {

    private static String PREFIX = "/system/map/";

    @RequestMapping("/show")
    public ModelAndView viewMap(@RequestParam(value = "road", required = false) String road,@RequestParam(value = "zoom_level", required = false) String zoomLevel){
        ModelAndView mv=new ModelAndView();
        if (road!=null){
            mv.addObject("road",road);
        }else { mv.addObject("road","");}

        if (zoomLevel!=null){
            mv.addObject("zoom_level",zoomLevel);
        }else { mv.addObject("zoom_level","17");}
        mv.setViewName(PREFIX+"geographical_map.html");
        return mv;
    }

    @RequestMapping("/show/phone")
    public ModelAndView viewPhoneMap(@RequestParam(value = "road", required = false) String road,@RequestParam(value = "zoom_level", required = false) String zoomLevel){
        ModelAndView mv=new ModelAndView();
        if (road!=null){
            mv.addObject("road",road);
        }else { mv.addObject("road","");}

        if (zoomLevel!=null){
            mv.addObject("zoom_level",zoomLevel);
        }else { mv.addObject("zoom_level","17");}
        mv.setViewName(PREFIX+"geographical_map_phone.html");
        return mv;
    }
}
