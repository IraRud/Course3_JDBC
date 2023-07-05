import lesson_4.Book;

import java.util.List;

public interface EmployeeDAO {
    // создание (добавление) сущности Employee в таблицу
    List<Employee> addEmployee(Employee employee);
    // получение конкретного объекта Employee по id
    List<Employee> findEmployeeById(int id);
    // получение списка всех объектов Employee из базы
    List<Employee> getAllEmployee();
    // изменение конкретного объекта Employee в базе по id
    List<Employee> setEmployeeById(int id);
    // удаление конкретного объекта Employee из базы по id
    List<Employee> deleteEmployeeById(int id);
}
