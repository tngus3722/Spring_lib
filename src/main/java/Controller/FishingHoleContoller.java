package Controller;

import Domain.FishingHole;
import Service.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FishingHoleContoller {
    @Autowired
    FishService fishService;

    /* do once for data2db.
        @ResponseBody
        @RequestMapping(value = "/data2db")
        public void data2db(){
            fishService.initial()
        }
    */
    @ResponseBody
    @RequestMapping(value = "/display",  method = RequestMethod.GET) // 낚시터 목록 조회
    public List<FishingHole> display(){ // 기본화면
        return fishService.display();
    }

    @ResponseBody
    @RequestMapping(value = "/search" , method = RequestMethod.GET) // 낚시터 검색 조회
    public List<FishingHole> search(@RequestParam("search") String search){ // 검색
        return fishService.search(search);
    }

    @ResponseBody
    @RequestMapping (value = "/fish_detail" , method = RequestMethod.GET) // 낚시터 상세정보 조회
    public FishingHole fish_detail(@RequestParam Integer id){
        return fishService.viewOne(id);
    }
}
