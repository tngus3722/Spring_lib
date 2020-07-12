package Repository;

import DTO.ReviewDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewMapper {
    public List<ReviewDTO> display(int id);
    public void insert(ReviewDTO reviewDTO);
    public void delete(int id);
    public String getPasswordById(int id);
    public void update(@Param("title") String title, @Param("content") String content, @Param("id") int id);
}
