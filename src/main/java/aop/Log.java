package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import domain.SlackAttachment;
import domain.SlackParameter;
import slack.SlackSender;

import java.nio.charset.Charset;
import java.util.ArrayList;

@Component
@Aspect
public class Log {

    @Autowired
    SlackSender slackSender;

    @Around("execution( * service.UserServiceImpl.logIn(..))") // login 전, 후 실행 around
    public Object LogInLog(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        try {
            System.out.println("pre do login - around"); // 전 실행
            result = proceedingJoinPoint.proceed(); // do target
            System.out.println("after do login - around"); // 후 실행
        } catch (Throwable throwable) { // 에러 발생 실행
            System.out.println("exception! ");
        }
        return result;
    }

    @After("execution( * service.UserServiceImpl.signUp(..))") // sign up 후 실행
    public void SignUpAfterLog(JoinPoint joinPoint) {
        System.out.println("after do " + joinPoint.getSignature().getName() + " - after");
    }
    @Before("execution( * service.UserServiceImpl.signUp(..))") // sign up 전 실행
    public void SignUpBeforeLog(JoinPoint joinPoint) {
        System.out.println("before do " + joinPoint.getSignature().getName() + " - before");
    }
    @AfterReturning("execution( * service.UserServiceImpl.logOut(..))") // log out 정상수행 후 실행
    public void LogOutAfterReturningLog(JoinPoint joinPoint) {
        System.out.println("success do " + joinPoint.getSignature().getName() + " - AfterReturning");
    }

    @AfterThrowing(pointcut = "execution(* service.ReviewServiceImpl.update(..))" ,  throwing = "exception") // update 에러발생 후 실행
    public void SignUpAfterThrowingLog(JoinPoint joinPoint , Throwable exception) {
        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setAuthor_name("정수현");
        slackAttachment.setColor("danger");
        slackAttachment.setTitle("Error method : " + joinPoint.getSignature().getName() );
        slackAttachment.setText("Error name : " + exception);
        slackSender.noticeError(slackAttachment);
    }

}
