package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.dao.generic.configs.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Client;
import jakarta.persistence.NoResultException;

public class ClientDao extends AbstractJpaDao<Long, Client> {

    public ClientDao() {
        this.setClazz(Client.class);
    }

    public Client findByEmail(String email) {
        try {
            return entityManager.createQuery(
                            "SELECT c FROM Client c WHERE c.email = :email", Client.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }



}


