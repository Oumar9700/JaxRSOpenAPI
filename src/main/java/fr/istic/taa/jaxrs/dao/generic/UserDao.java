package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.dao.generic.configs.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Client;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.persistence.NoResultException;

public class UserDao extends AbstractJpaDao<Long, User> {

    public UserDao() {
        this.setClazz(User.class);
    }

    public User findByEmail(String email) {
        try {
            return entityManager.createQuery(
                            "SELECT c FROM User c WHERE c.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

}
