package co.com.disney.email;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import co.com.disney.model.gateways.EmailGateway;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class EmailUseCase implements EmailGateway {
	

	@Value("${disney.word.email.sender}")
	private String emailSender;
	
	@Autowired
	private Environment env;
	

	@Override
	public void sendWelcomeEmailTo(String to) {
	String apiKey = env.getProperty("EMAIL_API_KEY");
		
		Email fromEmail = new Email(emailSender);
		Email fromTo = new Email(to);
		Content content = new Content(
				
				"text/plain",
				"Brilliant! Now you can access all the resources available in the application");
		
		
		String subject = "Welcome to Application Disney-Word!";
		
		Mail mail = new Mail(fromEmail,subject,fromTo,content);
		SendGrid send = new SendGrid(apiKey);
		Request rq = new Request();
		
		try {
			
			rq.setMethod(Method.POST);
			rq.setEndpoint("mail/send");
			rq.setBody(mail.build());
			Response response = send.api(rq);
			
			log.info("", response.getStatusCode());
			log.info("", response.getHeaders());
			log.info("", response.getBody());

			
			
		}catch (IOException ex) {
			log.info("Error trying to send the email", ex);
		}
		
	}

}