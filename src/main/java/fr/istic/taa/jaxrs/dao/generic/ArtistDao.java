package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;

public class ArtistDao extends AbstractJpaDao<Long, Concert> {

    public ArtistDao() {
        this.setClazz(Concert.class);
    }


}
