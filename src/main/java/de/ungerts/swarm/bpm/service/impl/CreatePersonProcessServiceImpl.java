package de.ungerts.swarm.bpm.service.impl;


import de.ungerts.swarm.bpm.service.CreatePersonProcessService;
import de.ungerts.swarm.data.vo.MailVO;
import de.ungerts.swarm.data.vo.PersonVO;
import org.eclipse.microprofile.config.Config;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Named
@Transactional(value = REQUIRED, rollbackOn = RuntimeException.class)
public class CreatePersonProcessServiceImpl implements CreatePersonProcessService {

    @Resource(mappedName = "java:jboss/mail/mailserver")
    private Session session;


    @Inject
    Config config;

    @Override
    public MailVO createMail(PersonVO person) {
        String serverUrl = config.getValue("email.creation.service.url", String.class);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(serverUrl);
        return target.request(MediaType.APPLICATION_JSON).post(Entity.json(person), MailVO.class);
    }

    @Override
    public void informAdministrator(MailVO mail, PersonVO person) {
        MimeMessage msg = new MimeMessage(session);
        String text = String.format("Person %s %s with id %d and email %s created!", person.getFirstname(), person.getLastname(), person.getId(), mail.getEmailAddress());
        try {
            msg.setText(text);
            msg.setSubject("Person created");
            msg.setRecipients(Message.RecipientType.TO, "admins@example.org");
            Transport.send(msg);
        } catch (MessagingException e) {
            throw new RuntimeException("Could not send mail", e);
        }
    }


}
