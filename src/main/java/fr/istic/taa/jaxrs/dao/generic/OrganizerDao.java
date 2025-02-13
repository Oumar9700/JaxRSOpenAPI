package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;

public class OrganizerDao extends AbstractJpaDao<Long, Concert> {

    public OrganizerDao() {
        this.setClazz(Concert.class);
    }


}
