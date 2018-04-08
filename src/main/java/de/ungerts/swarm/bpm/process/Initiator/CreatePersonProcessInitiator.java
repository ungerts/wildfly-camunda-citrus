package de.ungerts.swarm.bpm.process.Initiator;

import de.ungerts.swarm.event.PersonEvent;
import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

@Stateless
public class CreatePersonProcessInitiator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePersonProcessInitiator.class);

    @Inject
    RuntimeService runtimeService;

    @Asynchronous
    public void handlePersonEvent(@Observes(during = TransactionPhase.AFTER_SUCCESS)PersonEvent personEvent) {
        LOGGER.info("Received event of type '{}'.", personEvent.getEventType().name());

    }

}
