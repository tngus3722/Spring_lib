package Service;

import DTO.ReviewDTO;
import Repository.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    public List<ReviewDTO> display(int id){
        return reviewMapper.display(id);
    }
    public void insert(ReviewDTO reviewDTO){
        reviewMapper.insert(reviewDTO);
    }
    public Boolean delete(int id, String inputPassword){ // 삭제
        String password = reviewMapper.getPasswordById(id);
        if ( password.equals(inputPassword)){ // 비밀번호 확인
            reviewMapper.delete(id);
            return true;
        }
        else
            return false;
    }

    public Boolean update(int id, String content, String title, int fish_id, String inputPassword){ // 수정
        String password = reviewMapper.getPasswordById(id);
        if ( password.equals(inputPassword)){ // 비밀번호 확인
            reviewMapper.update(title,content,id);
            return true;
        }
        else
            return false;
    }
}
