package controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import service.FishService;
import service.FishServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FishingHoleContoller {
    @Autowired
    FishService fishService;

    @ResponseBody
    @RequestMapping(value = "/data2db")
    public ResponseEntity data2db(){ // data2db
        fishService.initial();
        return new ResponseEntity(HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping(value = "/display",  method = RequestMethod.GET) // 낚시터 목록 조회
    public ResponseEntity display(){ // 기본화면
        return new ResponseEntity( fishService.display(), HttpStatus.OK);

    }

    @ApiOperation( value = "value test", notes = "notes test", authorizations = @Authorization(value = "Authorization"))
    @ResponseBody
    @RequestMapping(value = "/search" , method = RequestMethod.GET) // 낚시터 검색 조회
    public ResponseEntity search(@ApiParam(name ="serach" , value = "desc test") @RequestParam("search") String search)
    {
        return new ResponseEntity(fishService.search(search), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping (value = "/fish-detail" , method = RequestMethod.GET) // 낚시터 상세정보 조회
    public ResponseEntity fish_detail(@RequestParam Long id){
        return new ResponseEntity(fishService.viewOne(id), HttpStatus.OK);
    }
}
