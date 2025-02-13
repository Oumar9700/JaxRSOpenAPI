package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;

public class ClientDao extends AbstractJpaDao<Long, Concert> {

    public ClientDao() {
        this.setClazz(Concert.class);
    }


}
