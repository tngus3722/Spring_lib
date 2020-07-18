package Service;

import Domain.User;
import Repository.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public String CreateJwt(String name, String password){
        Map<String, Object> headers = new HashMap<String, Object>();
        headers.put("typ", "JWT");
        headers.put("alg","HS256");
        Map<String, Object> payloads = new HashMap<String, Object>();
        payloads.put("user",name);
        payloads.put("password",password);

        String key = "a"; // 암호화?
        String jwt = Jwts.builder().setHeader(headers).setClaims(payloads).signWith(SignatureAlgorithm.HS256, key.getBytes()).compact() ;

        return jwt;
    }

    public String signUp(User user){
        String jwt = CreateJwt(user.getName(),user.getPassword());
        user.setToken(jwt);
        userMapper.signUp(user);
        return jwt;
    }
}
