package de.ungerts.swarm.bpm.service;


import de.ungerts.swarm.data.vo.MailVO;
import de.ungerts.swarm.data.vo.PersonVO;

public interface CreatePersonProcessService {

    MailVO createMail(PersonVO person);

    void informAdministrator(MailVO mail, PersonVO person);

}
