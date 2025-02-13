package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Organizer;

public class OrganizerDao extends AbstractJpaDao<Long, Organizer> {

    public OrganizerDao() {
        this.setClazz(Organizer.class);
    }


}
