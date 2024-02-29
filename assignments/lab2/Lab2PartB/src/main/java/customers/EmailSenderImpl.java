package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {
	@Value("${outgoingMailServer}")
	String outgoingMailServer;

	private Logger logger ;
	
	@Autowired
	public EmailSenderImpl(Logger logger) {
		this.logger = logger;
	}
	

	public String getOutgoingMailServer() {
		return outgoingMailServer;
	}

	public void sendEmail (String email, String message){
		System.out.println("Outgoing Mail Server: " + outgoingMailServer);
		System.out.println("EmailSender: sending '"+message+"' to "+email );
		logger.log("Email is sent: message= "+message +" , emailaddress ="+ email  );
	}

}
