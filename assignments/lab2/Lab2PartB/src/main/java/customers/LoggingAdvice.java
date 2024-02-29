package customers;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class LoggingAdvice {
	@After("execution(* *.EmailSender.sendEmail(..))")
	public void log(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		String email = (String)args[0];
		String message = (String)args[1];
		EmailSender emailSender = (EmailSender) joinPoint.getTarget();
		String logMessage = LocalDateTime.now() + " method=sentEmail" + " address=" + email + " message= " + message +
							" outoing mail server= " + emailSender.getOutgoingMailServer();
		System.out.println(logMessage);
	}
	
	@Around("execution(* *.CustomerRepository.*(..))")
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
}
