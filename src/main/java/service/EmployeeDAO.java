package service;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {
    // создание (добавление) сущности Employee в таблицу
    void addEmployee(Employee employee);

    // получение конкретного объекта Employee по id
    Employee findEmployeeById(int id);

    // получение списка всех объектов Employee из базы
    List<Employee> getAllEmployees();

    // изменение конкретного объекта Employee в базе
    void setEmployee(Employee employee);

    // удаление конкретного объекта Employee из базы
    void deleteEmployee(Employee employee);
}
