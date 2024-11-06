package hu.besp.onetimetokenlogin.configuration;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.ott.OneTimeToken;
import org.springframework.security.web.authentication.ott.OneTimeTokenGenerationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailingOneTimeTokenHandler implements OneTimeTokenGenerationSuccessHandler {

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            OneTimeToken oneTimeToken
    ) throws IOException,
             ServletException {
        final var token = oneTimeToken.getTokenValue();

        final var message = "please go to http://localhost:8080/login/ott?token=" + token;
        System.out.println(message);

        final var emailAddress = String.format("%s@localhost", oneTimeToken.getUsername());
        sendEmail(emailAddress, "One Time Token", message);

        response.setContentType(
                MediaType.TEXT_HTML_VALUE);
        response.getWriter()
                .write("check your mail: <a href=\"http://localhost:8025\">http://localhost:8025</a> or check the console");
    }

    private void sendEmail(String to, String subject, String body) {
        final var message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }

}