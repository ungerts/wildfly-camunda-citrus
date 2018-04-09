package de.ungerts.swarm.data.vo;

import java.io.Serializable;

public class MailVO implements Serializable {

    static final long serialVersionUID = 42L;

    private Long personId;

    private String emailAddress;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
