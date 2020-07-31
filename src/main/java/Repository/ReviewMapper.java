package Repository;

import Domain.Review;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewMapper {
    public List<Review> display(Integer fish_id);
    public void insert(Review review);
    public void delete(Long id);

    //Param은 다수의 parameter를 전송하기 위해 사용 가능하다.
    public void update(@Param("title") String title, @Param("content") String content, @Param("id") Long id);
    public Review getReviewById(Long id);
}
