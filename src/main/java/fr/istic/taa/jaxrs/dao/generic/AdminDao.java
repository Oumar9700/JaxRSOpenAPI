package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.dao.generic.configs.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Admin;

public class AdminDao extends AbstractJpaDao<Long, Admin> {

    public AdminDao() {
        this.setClazz(Admin.class);
    }


}
