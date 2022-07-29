package whitesoftapp.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Aspect
@Component
public class LoggingRequest {

    @Pointcut("execution(public * whitesoftapp.controller.*.*.*(..))")
    private void callAtController() { }

    @Before("callAtController()")
    private void logParams(JoinPoint jp) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        List<String> args = Arrays.stream(jp.getArgs())
                                  .map(a -> a.toString())
                                  .collect(Collectors.toList());

        String info ="Class request: "+ jp.getSignature().getDeclaringTypeName() +
                     " Request " + jp.getSignature().getName()+
                     " with params: " + "Id: " +(args.size() != 0 ? args.get(0) : null) +
                     " Request IP: " + request.getRemoteAddr()+
                     " Args: " + args;

        log.info(info);
    }

    @AfterThrowing(pointcut = "callAtController()", throwing = "e")
    private void logException(JoinPoint jp, Throwable e) {
        log.error("Exception in {}.{}() with message = {}", jp.getSignature().getDeclaringTypeName(),
                  jp.getSignature().getName(), e.getMessage() != null ? e.getMessage() : "NULL");
    }

}
