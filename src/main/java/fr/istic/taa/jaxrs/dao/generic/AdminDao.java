package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;

public class AdminDao extends AbstractJpaDao<Long, Concert> {

    public AdminDao() {
        this.setClazz(Concert.class);
    }


}
