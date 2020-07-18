package Service;

import Domain.Review;
import Repository.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    public List<Review> display(Integer fish_id){
        return reviewMapper.display(fish_id);
    }

    public void insert(Review reviewDTO){
        reviewMapper.insert(reviewDTO);
    }

    public void delete(Long id){ // 삭제
        reviewMapper.delete(id);
    }

    public void update(Long id, String content, String title){ // 수정
        reviewMapper.update(title,content,id);
    }
}
