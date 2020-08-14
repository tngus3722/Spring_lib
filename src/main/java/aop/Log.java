package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Log {

    @Around("execution( * service.UserService.logIn(..))") // login 전, 후 실행 around
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

    @After("execution( * service.UserService.signUp(..))") // sign up 후 실행
    public void SignUpAfterLog(JoinPoint joinPoint) {
        System.out.println("after do " + joinPoint.getSignature().getName() + " - after");
    }
    @Before("execution( * service.UserService.signUp(..))") // sign up 전 실행
    public void SignUpBeforeLog(JoinPoint joinPoint) {
        System.out.println("before do " + joinPoint.getSignature().getName() + " - before");
    }
    @AfterReturning("execution( * service.UserService.logOut(..))") // create jwt 정상수행 후 실행
    public void SignUpAfterReturningLog(JoinPoint joinPoint) {
        System.out.println(" do success " + joinPoint.getSignature().getName() + " - after-returning");
    }
    @AfterThrowing("execution(* service.ReviewService.update(..))") // update 에러발생 후 실행
    public void SignUpAfterThrowingLog(JoinPoint joinPoint) {
        System.out.println("do fail " + joinPoint.getSignature().getName() + " - after-throwing");
    }

}
