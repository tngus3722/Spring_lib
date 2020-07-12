package Controller;

import DTO.FishingHoleDTO;
import DTO.ReviewDTO;
import Service.FishService;
import Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class myController{
    @Autowired
    FishService fishService;

    @Autowired
    ReviewService reviewService;

/* do once for data2db
    @ResponseBody
    @RequestMapping(value = "/data2db")
    public void data2db(){
        fishService.initial()
    }
*/
    @RequestMapping(value = "/")
    public ModelAndView display(){
        List<FishingHoleDTO> list = fishService.display();
        ModelAndView mav = new ModelAndView();
        mav.addObject("list",list);
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value = "/search")
    public ModelAndView search(@RequestParam("search") String search){
        ModelAndView mav = new ModelAndView();
        List<FishingHoleDTO> list = fishService.search(search);
        mav.addObject("list",list);
        mav.setViewName("index");
        return mav;
    }

    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ModelAndView getBoard(@RequestParam("id") int id , @RequestParam(value = "str", required = false) String str){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("detail");
        mav.addObject("data",fishService.viewOne(id));
        mav.addObject("list", reviewService.display(id));
        mav.addObject("str",str);
        return mav;
    }

    @RequestMapping(value ="/board" , method= RequestMethod.POST)
    public String postBoard(@RequestParam("fish_id") int fish_id, @RequestParam("name") String name,
                                  @RequestParam("title") String title, @RequestParam("content") String content,
                                  @RequestParam("password") String password){
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setFish_id(fish_id);
        reviewDTO.setName(name);
        reviewDTO.setTitle(title);
        reviewDTO.setContent(content);
        reviewDTO.setPassword(password);
        reviewService.insert(reviewDTO);
        return "redirect:board/?id="+ fish_id;
    }

    @RequestMapping(value = "/board", method = RequestMethod.DELETE)
    public String deleteBoard(@RequestParam("id") int id, @RequestParam("password") String inputPassword,
                              @RequestParam("fish_id") int fish_id){
        ModelAndView mav = new ModelAndView();
        if ( !reviewService.delete(id, inputPassword) )
            return "redirect:board/?id="+ fish_id +"&str=passwordFail";
        else
            return "redirect:board/?id="+ fish_id;
    }

    @RequestMapping(value = "/board", method = RequestMethod.PUT)
    public String updateBoard(@RequestParam("id") int id, @RequestParam("password") String inputPassword,
                              @RequestParam("fish_id") int fish_id, @RequestParam("title") String title,
                              @RequestParam("content") String content){
        if ( !reviewService.update(id, content, title, fish_id,inputPassword) )
            return "redirect:board/?id="+ fish_id +"&str=passwordFail";
        else
            return "redirect:board/?id="+ fish_id;
    }
}
