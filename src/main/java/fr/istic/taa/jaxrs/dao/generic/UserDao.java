package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.User;

public class UserDao extends AbstractJpaDao<Long, User> {

    public UserDao() {
        this.setClazz(User.class);
    }


}
