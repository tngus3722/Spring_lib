package controller;

import domain.Review;
import service.ReviewService;
import service.ReviewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.GET) // 해당 낚시터 리뷰 조회
    public ResponseEntity getBoard(@RequestParam("fish_id") Integer fish_id){
        return new ResponseEntity(reviewService.display(fish_id), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value ="/board" , method= RequestMethod.POST) // 해당 낚시터 리뷰 발행
    public ResponseEntity postBoard(@RequestBody Review review, HttpServletRequest request){
        if ( reviewService.insert(review, request) )
            return new ResponseEntity("post success",HttpStatus.CREATED); // 201 http status
        else
            return new ResponseEntity("input is null",HttpStatus.BAD_REQUEST); // 400 ERROR
    }

    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.DELETE) // 해당 낚시터 리뷰 삭제
    public ResponseEntity deleteBoard(@RequestBody Review review,  HttpServletRequest request){
        if ( reviewService.delete(review, request) ) // 성공했다면
            return new ResponseEntity("delete success" , HttpStatus.OK);
        else //실패했다면
            return new ResponseEntity("delete fail",HttpStatus.FORBIDDEN); // 권한없음을 표시
    }

    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.PUT) // 해당 낚시터 리뷰 수정
    public ResponseEntity updateBoard(@RequestBody Review review,  HttpServletRequest request){
        if ( reviewService.update(review, request) == 1 ) // 성공
            return new ResponseEntity("update success" , HttpStatus.OK);
        else if ( reviewService.update(review,request) == 2) // 권한없음
            return new ResponseEntity("update fail",HttpStatus.FORBIDDEN);
        else // NULL 입력
            return new ResponseEntity("input is null", HttpStatus.BAD_REQUEST);
    }
}
