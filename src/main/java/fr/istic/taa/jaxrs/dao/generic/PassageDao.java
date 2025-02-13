package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Passage;

public class PassageDao extends AbstractJpaDao<Long, Passage> {

    public PassageDao() {
        this.setClazz(Passage.class);
    }


}
