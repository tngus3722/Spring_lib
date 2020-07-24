package Controller;

import Domain.Review;
import Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getBoard(@RequestParam("id") Integer id){
        return new ResponseEntity(reviewService.display(id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value ="/board" , method= RequestMethod.POST) // 해당 낚시터 리뷰 발행
    public ResponseEntity postBoard(@RequestBody Review review){
        reviewService.insert(review);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.DELETE) // 해당 낚시터 리뷰 삭제
    public ResponseEntity deleteBoard(@RequestBody Review review){
        reviewService.delete(review.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.PUT) // 해당 낚시터 리뷰 수정
    public ResponseEntity updateBoard(@RequestBody Review review){
        reviewService.update(review.getId(),review.getContent(),review.getTitle());
        return new ResponseEntity(HttpStatus.OK);
    }

}
