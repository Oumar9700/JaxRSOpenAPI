package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;

public class PriceDao extends AbstractJpaDao<Long, Concert> {

    public PriceDao() {
        this.setClazz(Concert.class);
    }


}
