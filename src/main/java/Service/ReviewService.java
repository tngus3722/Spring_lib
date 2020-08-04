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

    public boolean isNull(Review review){
        if ( !"".equals(review.getContent()) && !"".equals(review.getTitle()) )
            return true;
        else
            return false;
    }

    public List<Review> display(Integer fish_id){
        return reviewMapper.display(fish_id);
    } // review list 조회

    public Review getReviewById(Long id){ return reviewMapper.getReviewById(id); } // review id를 통해 Review 객체를 가져옴

    public Claims getPayloadsToJwt(HttpServletRequest request){ // 클라이언트의 JWT로 부터 payload를  반환함
        Cookie[] cookies = request.getCookies();
        for (int i=0; i<cookies.length; i++){
            if ( cookies[i].getName().equals("token")){ //token이 있다면
                String jwt = cookies[i].getValue();
                Claims claims = Jwts.parser().setSigningKey("Mysecretkey".getBytes()).parseClaimsJws(jwt).getBody();
                return claims; // payload 반환
            }
        }
        return null;
    }
    public boolean insert(Review review, HttpServletRequest request){ // 리뷰삽입
        if ( this.isNull(review)) { // null 이 아니라면
            Long id = this.getPayloadsToJwt(request).get("id", Long.class); // payload에서 user id 추출
            String writer = this.getPayloadsToJwt(request).get("name", String.class); // payload에서 user name 추철
            review.setWritter(writer); // set
            review.setUser_id(id); // set
            reviewMapper.insert(review); // 삽입
            return true;
        }
        else
            return false;
    }

    public boolean delete(Review review,  HttpServletRequest request){ // 삭제
        Long user_id = this.getPayloadsToJwt(request).get("id",Long.class); // payload 에서 가져온 user id와
        Long reviewedId = this.getReviewById(review.getId()).getUser_id(); // review를 작성한 user의 id가
        if ( user_id == reviewedId) { //같다면
            reviewMapper.delete(review.getId()); //삭제
            return true; //삭제 성공
        }
        else // 다르다면
            return false; // 삭제 실패
    }

    public int update(Review review,  HttpServletRequest request){ // 수정
        if ( this.isNull(review)  ) { // 값들이 null이 아니며
            Long user_id = this.getPayloadsToJwt(request).get("id", Long.class); // payload 에서 가져온 user 정보와
            Long reviewedId = this.getReviewById(review.getId()).getUser_id();
            ;// review를 작성한 user의 id가
            if (user_id == reviewedId) { //같다면
                reviewMapper.update(review.getTitle(), review.getContent(), review.getId()); // 수정
                return 1; //수정 성공
            }
            else
                return 2; // 권한없음
        }
        else
            return 3; // null
    }
}
