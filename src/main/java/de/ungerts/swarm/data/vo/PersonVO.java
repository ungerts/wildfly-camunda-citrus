package de.ungerts.swarm.data.vo;

import java.io.Serializable;

public class PersonVO implements Serializable {

    static final long serialVersionUID = 42L;

    private Long id;

    private String firstname;

    private String lastname;

    private String personnelnumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPersonnelnumber() {
        return personnelnumber;
    }

    public void setPersonnelnumber(String personnelnumber) {
        this.personnelnumber = personnelnumber;
    }

}
