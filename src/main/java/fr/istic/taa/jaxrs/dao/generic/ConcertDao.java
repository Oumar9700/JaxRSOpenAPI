package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.dao.generic.configs.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Concert;

public class ConcertDao extends AbstractJpaDao<Long, Concert> {

    public ConcertDao() {
        this.setClazz(Concert.class);
    }


}
