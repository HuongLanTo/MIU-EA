package bank.advice;

import bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class MyAdvice {
    @Autowired
    Logger logger;

    @Before("execution(* bank.repository.*.*(..))")
    public void logBeforeMethod(JoinPoint joinpoint) {
        logger.log("-----------------> Before execution " + joinpoint.getSignature());
    }

    @Around("execution(* bank.service.*.*(..))")
    public Object calculateServiceMethod(ProceedingJoinPoint call) throws Throwable {
        StopWatch clock = new StopWatch("");
        clock.start(call.toShortString());

        Object object = call.proceed();

        clock.stop();
        logger.log(clock.prettyPrint());
        return object;
    }

    @After("execution(* bank.integration.jms.JMSSenderImpl.*(..))")
    public void logAfterMethod(JoinPoint joinpoint) {
        logger.log("-----------------> After JMS message is sent");
    }
}
