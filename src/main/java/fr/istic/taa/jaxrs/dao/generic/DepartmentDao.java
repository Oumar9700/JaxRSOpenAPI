package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.Department;

public class DepartmentDao extends AbstractJpaDao<Long, Department> {

    public DepartmentDao() {
        this.setClazz(Department.class);
    }


}
