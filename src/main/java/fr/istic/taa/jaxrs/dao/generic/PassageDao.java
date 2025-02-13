package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;

public class PassageDao extends AbstractJpaDao<Long, Concert> {

    public PassageDao() {
        this.setClazz(Concert.class);
    }


}
