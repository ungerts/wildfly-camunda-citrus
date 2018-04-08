package de.ungerts.swarm.event;

import java.util.Optional;

public class PersonEvent {

    private Long timestamp;

    private Optional<PersonVO> oldPersonOptional;

    private Optional<PersonVO> newPersonOptional;

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

    public Optional<PersonVO> getOldPersonOptional() {
        return oldPersonOptional;
    }

    public void setOldPersonOptional(Optional<PersonVO> oldPersonOptional) {
        this.oldPersonOptional = oldPersonOptional;
    }

    public Optional<PersonVO> getNewPersonOptional() {
        return newPersonOptional;
    }

    public void setNewPersonOptional(Optional<PersonVO> newPersonOptional) {
        this.newPersonOptional = newPersonOptional;
    }

    public enum EventType {
        CREATED,
        DELETED,
        UPDATED

    }

}
