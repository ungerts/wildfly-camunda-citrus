package de.ungerts.swarm.bpm.engine;

import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.ProcessApplicationInterface;
import org.camunda.bpm.application.impl.EjbProcessApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.*;


@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@ProcessApplication
@Local(ProcessApplicationInterface.class)
public class Bootstrap extends EjbProcessApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

    @PostConstruct
    public void start() {
        deploy();
    }

    @PreDestroy
    public void stop() {
        undeploy();
    }

}
