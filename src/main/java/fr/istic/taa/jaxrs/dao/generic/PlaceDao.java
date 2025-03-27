package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.dao.generic.configs.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Place;

public class PlaceDao extends AbstractJpaDao<Long, Place> {

    public PlaceDao() {
        this.setClazz(Place.class);
    }


}
