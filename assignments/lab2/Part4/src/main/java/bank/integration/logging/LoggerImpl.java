package bank.integration.logging;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class LoggerImpl implements Logger {

	public void log(String logString) {
		java.util.logging.Logger.getLogger("BankLogger").info(logString);
	}

}
