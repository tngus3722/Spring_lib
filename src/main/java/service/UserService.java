package service;

import domain.User;
import javax.servlet.http.HttpServletResponse;


public interface UserService {
    public boolean isValidToken(String jwt);

    public String createJwt(User user);
    public int signUp(User user);
    public boolean logIn(User user, HttpServletResponse response);

    public void logOut(HttpServletResponse response);
}
