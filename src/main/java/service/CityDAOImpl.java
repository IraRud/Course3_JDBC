package service;

import model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CityDAOImpl implements CityDAO {

    // создание (добавление) сущности City в таблицу
    @Override
    public void addCity(City city) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(city);
            transaction.commit();
        }
    }

    // получение конкретного объекта City по id
    @Override
    public City findCityById(int id) {
        try (Session session = service.HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(City.class, id);
        }
    }

    // получение списка всех объектов City из базы
    @Override
    public List<City> getAllCities() {
        try (Session session = service.HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM City").list();
        }
    }

    // изменение конкретного объекта City в базе по id
    @Override
    public City setCity(City city) {
        City updatedCity;
        try (Session session = service.HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            updatedCity = (City) session.merge(city);
            transaction.commit();
        }
        return updatedCity;
    }

    // удаление конкретного объекта City из базы по id
    @Override
    public void deleteCity(City city) {
        try (Session session = service.HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }

}
