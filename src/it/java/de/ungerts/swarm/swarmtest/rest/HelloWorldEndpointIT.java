package de.ungerts.swarm.swarmtest.rest;


import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static org.fest.assertions.Assertions.assertThat;

public class HelloWorldEndpointIT {

    @Test
    public void testHelloWorldEndpoint() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090");
        Response response = target.path("hello").request().get();
        assertThat(response.getStatus()).isEqualTo(200);
    }


}
