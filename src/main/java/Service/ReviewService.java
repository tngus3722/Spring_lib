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
    public Boolean delete(Long id, String inputPassword){ // 삭제
        String password = reviewMapper.getPasswordById(id);
        if ( password.equals(inputPassword)){ // 비밀번호 확인
            reviewMapper.delete(id);
            return true;
        }
        else
            return false;
    }

    public Boolean update(Long id, String content, String title, String inputPassword){ // 수정
        String password = reviewMapper.getPasswordById(id);
        if ( password.equals(inputPassword)){ // 비밀번호 확인
            reviewMapper.update(title,content,id);
            return true;
        }
        else
            return false;
    }
}
