package de.ungerts.swarm.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DOContract {

    @Id
    @GeneratedValue
    private Long id;

}
