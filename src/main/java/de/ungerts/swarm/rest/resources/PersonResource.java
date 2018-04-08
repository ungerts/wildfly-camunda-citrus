package de.ungerts.swarm.rest.resources;

import de.ungerts.swarm.data.model.Person;
import de.ungerts.swarm.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("person")
public class PersonResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

    @Inject
    private PersonService personService;

    @GET
    @Path("{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readPerson(@PathParam("personId") Long personId) {
        try {
            Optional<Person> personOptional = personService.readPerson(personId);
            if (personOptional.isPresent()) {
                return Response.ok().entity(personOptional.get()).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }

        } catch (RuntimeException e) {
            LOGGER.error("Could not read person!", e);
            return Response.serverError().build();
        }
    }


    @POST
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
    @Path("{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("personId") Long personId) {
        try {
            personService.deletePerson(personId);
            return Response.ok().build();
        } catch (RuntimeException e) {
            LOGGER.error("Could not delete person!", e);
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("{personId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(@PathParam("personId") Long personId, Person person) {
        person.setId(personId);
        try {
            Optional<Person> personOptional = personService.updatePerson(person);
            if (personOptional.isPresent()) {
                return Response.ok().entity(personOptional.get()).build();
            } else {
                return Response.status(Response.Status.NO_CONTENT).build();
            }

        } catch (RuntimeException e) {
            LOGGER.error("Could not update person!", e);
            return Response.serverError().build();
        }
    }

    @GET
    @Path("test")
    @Produces(MediaType.APPLICATION_JSON)
    public Person readTestPerson() {
        Person person = new Person();
        person.setId(999L);
        person.setPersonnelnumber("1-232-3");
        person.setLastname("Musermann");
        person.setFirstname("Max");
        return person;
    }

}
