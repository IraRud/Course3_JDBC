package service;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    // создание (добавление) сущности Employee в таблицу
    @Override
    public void addEmployee(Employee employee) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            System.out.println("!!! ID = " + employee);
            transaction.commit();
        }
    }

    // получение конкретного объекта Employee по id
    @Override
    public Employee findEmployeeById(int id) {
        try (Session session = service.HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }

    // получение списка всех объектов Employee из базы
    @Override
    public List<Employee> getAllEmployees() {
        try (Session session = service.HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee").list();
        }
    }

    // изменение конкретного объекта Employee в базе по id
    @Override
    public void setEmployee(Employee employee) {
        try (Session session = service.HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    // удаление конкретного объекта Employee из базы по id
    @Override
    public void deleteEmployee(Employee employee) {
        try (Session session = service.HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }

}
