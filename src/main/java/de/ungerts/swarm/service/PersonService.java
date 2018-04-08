package de.ungerts.swarm.service;

import de.ungerts.swarm.data.model.Person;

import java.util.Optional;

public interface PersonService {

    Person createPerson(Person person);

    Optional<Person> readPerson(Long personId);

    void deletePerson(Long personId);

    Optional<Person> updatePerson(Person person);
}
