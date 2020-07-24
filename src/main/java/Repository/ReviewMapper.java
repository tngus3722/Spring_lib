package Repository;

import Domain.Review;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewMapper {
    public List<Review> display(Integer fish_id);
    public void insert(Review reviewDTO);
    public void delete(Long id);
    public void update(@Param("title") String title, @Param("content") String content, @Param("id") Long id);
    public String getNameById(Long id);
}
