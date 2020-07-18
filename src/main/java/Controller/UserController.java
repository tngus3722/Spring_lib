package Controller;

import Domain.User;
import Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping (value = "/signUp" ,method = RequestMethod.POST)
    public void signUp(@RequestBody User user) {
        userService.signUp(user);
    }
}
