package fr.istic.taa.jaxrs.dao.generic.training;

import fr.istic.taa.jaxrs.dao.generic.configs.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.training.Employee;

public class EmployeeDao extends AbstractJpaDao<Long, Employee> {

    public EmployeeDao() {
        this.setClazz(Employee.class);
    }


}
