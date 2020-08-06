package service;

import domain.User;
import repository.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired

    UserMapper userMapper;

    private String key = "Mysecretkey"; // secret key

    @Override
    public boolean isValidToken(String jwt){ // 토큰이 유효한지 검사하는 함수
        Claims claims = Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(jwt).getBody();
        Date now = new Date();
        Date exp = claims.get("exp", Date.class); // jwt payload -> exp
        if( exp.getTime() > now.getTime()  ){ // 만료시간 유효 & jwt의 내용이 database user 내용과 일치한다면
            return true;
        }
        else
            return false;
    }
    @Override
    public String createJwt(User user){ // 토큰 생성
        Map<String, Object> headers = new HashMap<String, Object>(); // header
        headers.put("typ", "JWT");
        headers.put("alg","HS256");
        Map<String, Object> payloads = new HashMap<String, Object>(); //payload
        payloads.put("name",user.getName()); // name
        payloads.put("id",userMapper.getUserByName(user.getName()).getId()); // id 정보를 payload에 넣기위해서는 name으로 부터 user의 id를 조회하여야 한다.
        Long tmp = 11l*1000l * 60l; // 10분
        Date exp = new Date();
        exp.setTime( (exp.getTime() + tmp) ); // 현재시간  + 11분
        String jwt = Jwts.builder().setHeader(headers).setClaims(payloads).setExpiration(exp).signWith(SignatureAlgorithm.HS256, key.getBytes()).compact();
        return jwt;
    }
    @Override
    public int signUp(User user){ // 회원가입
        if( "".equals(user.getName()) )  // null name
            return 1;

        else if ( "".equals(user.getPassword())) // null password
            return 1;

        else if ( userMapper.getUserByName(user.getName()).getName().equals( user.getName())){ // 이미 사용되고 있는 name
            return 2;
        }
        else{
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt())); //bcrypt 암호화
            userMapper.signUp(user);
            return 3;
        }
    }
    @Override
    public boolean logIn(User user, HttpServletResponse response){
        User tmp = userMapper.getUserByName(user.getName()); // 해당 id에 암호화 된 password 조회

        if ( tmp == null ) // 없는 id 라면 false
            return false;

        else if ( BCrypt.checkpw(user.getPassword(), tmp.getPassword() ) ) {// 입력 plain password, database에 암호화된 암호 비교
            user.setPassword(tmp.getPassword());
            String jwt = createJwt(user);
            Cookie cookie = new Cookie("token", jwt); // 쿠기가 없다면
            cookie.setMaxAge(10*60); // 5분
            cookie.setHttpOnly(true); // XSS 공격을 막음
            response.addCookie(cookie);
            return true;
        }
        else
            return false;
    }
    @Override
    public void logOut(HttpServletResponse response){
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

}
