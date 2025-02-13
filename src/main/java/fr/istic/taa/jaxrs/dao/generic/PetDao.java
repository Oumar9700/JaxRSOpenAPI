package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;

public class PetDao extends AbstractJpaDao<Long, Concert> {

    public PetDao() {
        this.setClazz(Concert.class);
    }


}
