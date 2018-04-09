package de.ungerts.swarm.bpm.process.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

@Named
public class CreatePersonProcessDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePersonProcessDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("Execute task {}.", delegateExecution.getCurrentActivityId());
    }

}
