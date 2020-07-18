package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewContoller {

    @RequestMapping(value = "/")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/detail")
    public String detail(){ return "detail"; }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
}
