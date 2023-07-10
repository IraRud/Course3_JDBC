package service;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {
    // создание (добавление) сущности model.Employee в таблицу
    void addEmployee(Employee employee);

    // получение конкретного объекта model.Employee по id
    Employee findEmployeeById(int id);

    // получение списка всех объектов model.Employee из базы
    List<Employee> getAllEmployee();

    // изменение конкретного объекта model.Employee в базе по id
    void setEmployeeById(Employee employee);

    // удаление конкретного объекта model.Employee из базы по id
    void deleteEmployeeById(Employee employee);
}
