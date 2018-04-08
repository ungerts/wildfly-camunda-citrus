package de.ungerts.swarm.event;

import java.util.Optional;

public class PersonEvent {

    private Long timestamp;

    private PersonVO oldPerson;

    private PersonVO newPerson;

    private EventType eventType;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public PersonVO getOldPerson() {
        return oldPerson;
    }

    public void setOldPerson(PersonVO oldPerson) {
        this.oldPerson = oldPerson;
    }

    public PersonVO getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(PersonVO newPerson) {
        this.newPerson = newPerson;
    }

    public enum EventType {
        CREATED,
        DELETED,
        UPDATED

    }

}
