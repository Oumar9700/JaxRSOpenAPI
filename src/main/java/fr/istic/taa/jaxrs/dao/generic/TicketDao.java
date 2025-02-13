package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;

public class TicketDao extends AbstractJpaDao<Long, Concert> {

    public TicketDao() {
        this.setClazz(Concert.class);
    }


}
