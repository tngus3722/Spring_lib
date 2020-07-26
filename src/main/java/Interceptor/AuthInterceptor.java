package Interceptor;

import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean check = false;
        String url = request.getRequestURL().toString();

        Cookie[] cookies = request.getCookies();
        for (int i=0; i<cookies.length; i++){
            if ( cookies[i].getName().equals("token")){ //token이 있다면
                if ( userService.isValidToken(cookies[i].getValue())){ // cookie의 jwt token이 valid하다면
                    check = true; // true
                }
            }
        }
        if ( !check && !url.contains("login")) //token 없고, login창을 요구한것이 아니라면
            response.sendRedirect("/fish/login"); // 로그인 화면으로

        if ( check && url.contains("login")){ // token이 있고, login창을 요청한다면
            response.sendRedirect("/fish"); // 처음화면으로
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
