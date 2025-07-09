package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* org.example.service.UserService.*(..))")
    public void userServiceMethods() {}

    @Before("userServiceMethods()")
    public void logBefore() {
        System.out.println("Before method execution: Logging Before...");
    }

    @After("userServiceMethods()")
    public void logAfter() {
        System.out.println("After method execution: Logging After...");
    }

    @AfterReturning("userServiceMethods()")
    public void logAfterReturning() {
        System.out.println("Method executed successfully: Logging AfterReturning...");
    }

    @AfterThrowing("userServiceMethods()")
    public void logAfterThrowing() {
        System.out.println("Exception occurred: Logging...");
    }

    @Around("userServiceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around - Before method execution: Logging...");
        Object result = joinPoint.proceed(); // 执行目标方法
        System.out.println("Around - After method execution: Logging...");
        return result;
    }

}
