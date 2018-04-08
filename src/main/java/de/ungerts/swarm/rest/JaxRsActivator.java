package de.ungerts.swarm.rest;

import de.ungerts.swarm.rest.resources.PersonResource;
import de.ungerts.swarm.swarmtest.rest.HelloWorldEndpoint;
import org.camunda.bpm.engine.rest.impl.CamundaRestResources;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("")
public class JaxRsActivator extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        // add camunda engine rest resources
        classes.addAll(CamundaRestResources.getResourceClasses());
        classes.addAll(CamundaRestResources.getConfigurationClasses());
        classes.add(HelloWorldEndpoint.class);
        classes.add(PersonResource.class);
        return classes;
    }


}
