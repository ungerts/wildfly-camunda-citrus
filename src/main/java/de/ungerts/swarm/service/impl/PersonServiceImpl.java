package de.ungerts.swarm.service.impl;

import de.ungerts.swarm.data.model.Person;
import de.ungerts.swarm.event.PersonEvent;
import de.ungerts.swarm.event.PersonVO;
import de.ungerts.swarm.service.PersonService;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.Optional;

import static javax.transaction.Transactional.TxType.*;

@Named
@Transactional(value = REQUIRED, rollbackOn = RuntimeException.class)
public class PersonServiceImpl implements PersonService {

    private final MapperFacade mapper;

    @PersistenceContext
    private
    EntityManager entityManager;

    @Inject
    private Event<PersonEvent> event;

    public PersonServiceImpl() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(Person.class, PersonVO.class).byDefault();
        mapperFactory.classMap(Person.class, Person.class).byDefault();
        mapper = mapperFactory.getMapperFacade();
    }

    @Override
    public Person createPerson(Person person) {
        entityManager.persist(person);
        entityManager.flush();
        emitPersonEvent(null, person, PersonEvent.EventType.CREATED);
        return person;
    }

    @Override
    public Optional<Person> readPerson(Long personId) {
        return Optional.ofNullable(entityManager.find(Person.class, personId));
    }

    @Override
    public void deletePerson(Long personId) {
        Person person = entityManager.find(Person.class, personId);
        if (person == null) {
            throw new IllegalArgumentException("Person with Id '" + personId + "' does not exist!");
        }
        emitPersonEvent(person, null, PersonEvent.EventType.DELETED);
        entityManager.remove(person);
    }

    public Optional<Person> updatePerson(Person person) {
        Person storedPerson = entityManager.find(Person.class, person.getId());
        if (storedPerson == null) {
           return Optional.ofNullable(null);
        }
        Person oldPerson = mapper.map(storedPerson, Person.class);
        storedPerson.setFirstname(person.getFirstname());
        storedPerson.setLastname(person.getLastname());
        storedPerson.setPersonnelnumber(person.getPersonnelnumber());
        emitPersonEvent(oldPerson, storedPerson, PersonEvent.EventType.UPDATED);
        return Optional.of(storedPerson);
    }

    private void emitPersonEvent(Person oldPerson, Person newPerson, PersonEvent.EventType eventType) {
        PersonVO oldPersonVO = mapper.map(oldPerson, PersonVO.class);
        PersonVO newPersonVO = mapper.map(newPerson, PersonVO.class);
        PersonEvent personEvent = new PersonEvent();
        personEvent.setTimestamp(System.currentTimeMillis());
        personEvent.setEventType(eventType);
        personEvent.setNewPerson(newPersonVO);
        personEvent.setOldPerson(oldPersonVO);
        event.fire(personEvent);
    }
}
