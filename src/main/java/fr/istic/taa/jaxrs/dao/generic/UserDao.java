package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;

public class UserDao extends AbstractJpaDao<Long, Concert> {

    public UserDao() {
        this.setClazz(Concert.class);
    }


}
