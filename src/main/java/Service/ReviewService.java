package Service;

import Domain.Review;
import Repository.ReviewMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    public List<Review> display(Integer fish_id){
        return reviewMapper.display(fish_id);
    }

    public String getNameToJwt(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (int i=0; i<cookies.length; i++){
            if ( cookies[i].getName().equals("token")){ //token이 있다면
                String jwt = cookies[i].getValue();
                Claims claims = Jwts.parser().setSigningKey("a".getBytes()).parseClaimsJws(jwt).getBody();
                String name = claims.get("name", String.class); // jwt payload -> name
                return name;
            }
        }
        return null;
    }

    public String getNameById(Long id){
        return reviewMapper.getNameById(id);
    }
    public void insert(Review review, HttpServletRequest request){
        String name = this.getNameToJwt(request);
        review.setName(name);
        reviewMapper.insert(review);
    }

    public boolean delete(Review review,  HttpServletRequest request){ // 삭제
        String reviewName = this.getNameById(review.getId()); // 해당 review id의 유저이름과 jwt token의 유저이름 비교
        String name = this.getNameToJwt(request);
        if ( name.equals(reviewName)) { //같다면
            reviewMapper.delete(review.getId());
            return true; //삭제 성공
        }
        else // 다르다면
            return false; // 삭제 실패
    }

    public boolean update(Review review,  HttpServletRequest request){ // 수정
        String name = this.getNameToJwt(request);
        String reviewName = this.getNameById(review.getId()); // 해당 review id의 유저이름과 jwt token의 유저이름 비교
        if ( name.equals(reviewName)) { //같다면
            reviewMapper.update(review.getTitle(), review.getContent(), review.getId());
            return true; //삭제 성공
        }
        else // 다르다면
            return false; // 삭제 실패
    }
}
