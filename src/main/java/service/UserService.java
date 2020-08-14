package service;

import domain.User;
import javax.servlet.http.HttpServletResponse;


public interface UserService {
    boolean isValidToken(String jwt);
    String createJwt(User user);
    int signUp(User user);
    boolean logIn(User user, HttpServletResponse response);
    void logOut(HttpServletResponse response);
}
