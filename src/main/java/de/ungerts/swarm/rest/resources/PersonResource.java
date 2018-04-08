package de.ungerts.swarm.rest.resources;

import de.ungerts.swarm.data.model.Person;
import de.ungerts.swarm.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wildfly.swarm.spi.runtime.annotations.Post;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("person")
public class PersonResource {

    private static Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

    @Inject
    private PersonService personService;

    @Path("{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPerson(@PathParam("personId") String personId) {
        try {
            Optional<Person> personOptional = personService.readPerson(personId);
            if (personOptional.isPresent()) {
                return Response.ok().entity(personOptional.get()).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

        } catch (RuntimeException e) {
            LOGGER.error("Could not read person!", e);
            return Response.serverError().build();
        }
    }


    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPerson(Person person) {
        if (person.getId() != null) {
            person.setId(null);
        }
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(personService.createPerson(person))
                    .build();
        } catch (RuntimeException e) {
            LOGGER.error("Could not create person!", e);
            return Response.serverError().build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePerson(String personId) {
        try {
            personService.deletePerson(personId);
            return Response.ok().build();
        } catch (RuntimeException e) {
            LOGGER.error("Could not delete person!", e);
            return Response.serverError().build();
        }
    }



}
