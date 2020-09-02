package controller;

import domain.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import service.UserService;
import service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController {
    @Autowired
    UserService userService;
    @ResponseBody
    @RequestMapping (value = "/sign-up" ,method = RequestMethod.POST)
    public ResponseEntity signUp(@RequestBody User user) { // 회원가입
        if (  userService.signUp(user) == 3)
            return new ResponseEntity(HttpStatus.OK);
        else if ( userService.signUp(user) == 2 )
            return new ResponseEntity("already used user name",HttpStatus.FORBIDDEN);
        else
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    @ApiOperation( value = "value test", notes = "notes test", authorizations = @Authorization(value = "Authorization"))
    @ResponseBody
    @RequestMapping(value = "/log-in" , method = RequestMethod.POST)
    public ResponseEntity logIn(@RequestBody User user, HttpServletResponse response){ // 로그인
        if ( userService.logIn(user, response) ) // 인증성공
            return new ResponseEntity("login success",HttpStatus.OK);
        else // 인증실패
            return new ResponseEntity(user.getName(),HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @RequestMapping(value =  "/log-out")
    public ResponseEntity logOut(HttpServletResponse response){ // 로그아웃
        userService.logOut(response);
        return new ResponseEntity(HttpStatus.OK);
    }
}
