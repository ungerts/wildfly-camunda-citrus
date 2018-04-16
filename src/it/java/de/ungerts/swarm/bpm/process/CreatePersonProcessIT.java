package de.ungerts.swarm.bpm.process;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.server.HttpServer;
import com.consol.citrus.mail.message.CitrusMailMessageHeaders;
import com.consol.citrus.mail.message.MailMessage;
import com.consol.citrus.mail.server.MailServer;
import com.consol.citrus.message.MessageType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class CreatePersonProcessIT extends JUnit4CitrusTestDesigner {

    @Autowired
    private HttpClient restClient;

    @Autowired
    private MailServer mailServer;

    @Autowired
    private HttpServer restServer;

    @Test
    @CitrusTest
    public void testCreatePerson(@CitrusResource TestContext context) {
        String firstname = "Max";
        String lastname = "Mustermann";
        String emailAddress = "max.mustermann@example.org";
        variable("firstname", firstname);
        variable("lastname", lastname);
        variable("personnelnumber", "123456789");
        variable("personId", 1L);
        variable("emailAddress", emailAddress);
        http().client(restClient)
                .send()
                .post("/person")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .payload(new ClassPathResource("messages/createPerson.json"));
        http().client(restClient)
                .receive()
                .response(HttpStatus.CREATED)
                .messageType(MessageType.JSON)
                .payload(new ClassPathResource("messages/createPersonResponse.json"));
        http().server(restServer)
                .receive()
                .post("/createemail")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                 .timeout(3000);
        http().server(restServer)
                .send()
                .response(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .payload(new ClassPathResource("messages/createEmailResponse.json"));
        //TODO: delete
        sleep(1000);
        Long personId = (Long) getVariables().get("personId");
        String emailText = String.format("Person %s %s with id %d and email %s created!", firstname, lastname, personId, emailAddress);
        receive(mailServer).header(CitrusMailMessageHeaders.MAIL_TO, "admins@example.org")
                .header(CitrusMailMessageHeaders.MAIL_SUBJECT, "Person created")
                .message(MailMessage.request()
                        .from("no-reply@example.org")
                        .to("admins@example.org")
                        .cc("")
                        .bcc("")
                        .subject("Person created")
                        .body(emailText, "text/plain; charset=us-ascii"))
                .timeout(5000);
        send(mailServer)
                .message(MailMessage.response(250, "OK"));

    }

}
