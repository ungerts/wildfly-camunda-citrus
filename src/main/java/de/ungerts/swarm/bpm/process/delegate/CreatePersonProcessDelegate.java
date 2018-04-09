package de.ungerts.swarm.bpm.process.delegate;

import de.ungerts.swarm.bpm.service.CreatePersonProcessService;
import de.ungerts.swarm.data.vo.MailVO;
import de.ungerts.swarm.data.vo.PersonVO;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;

import static de.ungerts.swarm.bpm.process.CreatePersonProcess.*;

@Named
public class CreatePersonProcessDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePersonProcessDelegate.class);

    @Inject
    private CreatePersonProcessService createPersonProcessService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String currentActivityId = delegateExecution.getCurrentActivityId();
        LOGGER.info("Execute task {}.", currentActivityId);
        if (TASK_CREATE_DEFAULT_PERMISSIONS.equals(currentActivityId)) {
            LOGGER.info("Task {} is currently not implemented.", TASK_CREATE_DEFAULT_PERMISSIONS);
        } else if (TASK_CREATE_EMAIL_ACCOUNT.equals(currentActivityId)) {
            PersonVO person = (PersonVO) delegateExecution.getVariable(VAR_PERSON);
            MailVO mail = createPersonProcessService.createMail(person);
            delegateExecution.setVariable(VAR_MAIL, mail);
        } else if (TASK_INFORM_ADMINISTRATOR.equals(currentActivityId)) {
            PersonVO person = (PersonVO) delegateExecution.getVariable(VAR_PERSON);
            MailVO mail = (MailVO) delegateExecution.getVariable(VAR_MAIL);
            createPersonProcessService.informAdministrator(mail, person);
        } else {
            throw new IllegalArgumentException("Task unknown.");
        }

    }

}
