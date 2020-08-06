package service;

import domain.Review;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ReviewService {
    public boolean isNull(Review review);
    public List<Review> display(Integer fish_id);
    public Review getReviewById(Long id);
    public Claims getPayloadsToJwt(HttpServletRequest request);
    public boolean insert(Review review, HttpServletRequest request);
    public boolean delete(Review review,  HttpServletRequest request);
    public int update(Review review,  HttpServletRequest request);

}
