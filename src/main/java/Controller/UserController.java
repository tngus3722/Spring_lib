package Controller;

import Domain.User;
import Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping (value = "/signUp" ,method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody User user) { // 회원가입
        userService.signUp(user);
        return new ResponseEntity(HttpStatus.OK);
    }


    @ResponseBody
    @RequestMapping(value = "/logIn" , method = RequestMethod.POST)
    public ResponseEntity logIn(@RequestBody User user, HttpServletResponse response){ // 로그인
        if ( userService.logIn(user, response) ) // 인증성공
            return new ResponseEntity("loing success",HttpStatus.OK);
        else // 인증실패
            return new ResponseEntity("login fail",HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @RequestMapping(value =  "/logOut")
    public ResponseEntity logOut(HttpServletResponse response){ // 로그아웃
        userService.logOut(response);
        return new ResponseEntity(HttpStatus.OK);
    }
}
