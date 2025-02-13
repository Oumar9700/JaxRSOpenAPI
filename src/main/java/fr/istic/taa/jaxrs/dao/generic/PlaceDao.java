package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;

public class PlaceDao extends AbstractJpaDao<Long, Concert> {

    public PlaceDao() {
        this.setClazz(Concert.class);
    }


}
