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
    public Review getReviewById(Long id){
        return reviewMapper.getReviewById(id);
    }
    public Claims getPayloadsToJwt(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (int i=0; i<cookies.length; i++){
            if ( cookies[i].getName().equals("token")){ //token이 있다면
                String jwt = cookies[i].getValue();
                Claims claims = Jwts.parser().setSigningKey("Mysecretkey".getBytes()).parseClaimsJws(jwt).getBody();
                return claims;
            }
        }
        return null;
    }
    public void insert(Review review, HttpServletRequest request){
        Long id = this.getPayloadsToJwt(request).get("id",Long.class);
        String writer = this.getPayloadsToJwt(request).get("name", String.class);
        review.setWritter(writer);
        review.setUser_id(id);
        reviewMapper.insert(review);
    }

    public boolean delete(Review review,  HttpServletRequest request){ // 삭제
        Long user_id = this.getPayloadsToJwt(request).get("id",Long.class);
        Long reviewedId = this.getReviewById(review.getId()).getUser_id();
        if ( user_id == reviewedId) { //같다면
            reviewMapper.delete(review.getId());
            return true; //삭제 성공
        }
        else // 다르다면
            return false; // 삭제 실패
    }

    public boolean update(Review review,  HttpServletRequest request){ // 수정
        Long user_id = this.getPayloadsToJwt(request).get("id",Long.class);
        Long reviewedId = this.getReviewById(review.getId()).getUser_id();; // 해당 review id의 user Id와 jwt token의 id비교
        if ( user_id == reviewedId) { //같다면
            reviewMapper.update(review.getTitle(), review.getContent(), review.getId());
            return true; //삭제 성공
        }
        else // 다르다면
            return false; // 삭제 실패
    }
}
