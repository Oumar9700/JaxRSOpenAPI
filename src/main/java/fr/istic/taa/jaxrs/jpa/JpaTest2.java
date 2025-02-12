package fr.istic.taa.jaxrs.jpa;

import java.util.List;

import fr.istic.taa.jaxrs.domain.Department;
import fr.istic.taa.jaxrs.domain.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaTest2 {

    private EntityManager manager;

    public JpaTest2(EntityManager manager) {
        this.manager = manager;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        JpaTest2 test = new JpaTest2(manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            test.createEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        test.listEmployees();
        test.listEmployeesOfDepartement(1L);

        manager.close();
        System.out.println(".. done");
    }

    private void createEmployees() {
        //Recuperer la taille du nombre d'employes
        int numOfEmployees = manager.createQuery("Select a From Employee a", fr.istic.taa.jaxrs.domain.Employee.class).getResultList().size();

        //si nombre d'employes == 0, inserer un departement puis inserer deux employes
        if (numOfEmployees == 0) {
            fr.istic.taa.jaxrs.domain.Department department = new Department("java");
            manager.persist(department);

            manager.persist(new Employee("Jakab Gipsz",department));
            manager.persist(new Employee("Captain Nemo",department));

        }
    }

    private void listEmployees() {
        List<Employee> resultList = manager.createQuery("Select a From Employee a", Employee.class).getResultList();
        System.out.println("num of employes:" + resultList.size());
        for (Employee next : resultList) {
            System.out.println("next employee: " + next);
        }
    }

    private void listEmployeesOfDepartement(Long id) {
        List<Employee> resultList = manager.createQuery("Select a From Employee a Where a.department.id = id"
                , Employee.class).getResultList();
        System.out.println("num of employes of departement of ID = 1:" + resultList.size());
        for (Employee next : resultList) {
            System.out.println("next employee: " + next);
        }
    }


}


