package bank.logging;

import org.springframework.stereotype.Service;

@Service
public class LoggerImpl implements Logger {
	public void log(String logstring) {
		java.util.logging.Logger.getLogger("BankLogger").info(logstring);		
	}
}
