package Controller;

import Domain.Review;
import Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.GET) // 해당 낚시터 리뷰 조회
    public List<Review> getBoard(@RequestParam("id") Integer id){
        return reviewService.display(id);
    }

    @ResponseBody
    @RequestMapping(value ="/board" , method= RequestMethod.POST) // 해당 낚시터 리뷰 발행
    public void postBoard(@RequestBody Review review){
        reviewService.insert(review);
    }

    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.DELETE) // 해당 낚시터 리뷰 삭제
    public void deleteBoard(@RequestBody Review review){
        reviewService.delete(review.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.PUT) // 해당 낚시터 리뷰 수정
    public void updateBoard(@RequestBody Review review){
        reviewService.update(review.getId(),review.getContent(),review.getTitle());
    }

}
