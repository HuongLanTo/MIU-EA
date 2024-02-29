package bank.service.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import bank.integration.logging.Logger;

@Component
@Aspect
@Configuration
public class LoggingAdvice {
	@Autowired
	private Logger logger;
	
	@After("execution(* bank.repository.AccountRepository.*(..))")
    public void accountRepositoryLog(JoinPoint joinPoint){
		String logMessage = "Account Repository Log: " + " method=" + joinPoint.getSignature().getName();
        logger.log(logMessage);
        System.out.println(logMessage);
    }
	
	@Around("execution(* bank.service.*.*(..))")
	public Object invoke(ProceedingJoinPoint call ) throws Throwable {
		 StopWatch sw = new StopWatch();
		 sw.start(call.getSignature().getName());
		 Object retVal = call.proceed();
		 sw.stop();

		long totaltime = sw.getLastTaskTimeMillis();
		// print the time to the console
		System.out.println("Time to excute: " + totaltime);
		
		return retVal;
	}
	
	@After("execution(* bank.integration.jms.JMSSender.sendJMSMessage(..))")
    public void JMSLog(JoinPoint joinPoint){
        String logMessage = "Send Message Log: " + " message= " + (String)joinPoint.getArgs()[0];
        logger.log(logMessage);
        System.out.println(logMessage);
    }
}

