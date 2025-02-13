package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Price;

public class PriceDao extends AbstractJpaDao<Long, Price> {

    public PriceDao() {
        this.setClazz(Price.class);
    }


}
