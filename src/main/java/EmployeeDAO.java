import java.util.List;

public interface EmployeeDAO {
    // создание (добавление) сущности Employee в таблицу
    void addEmployee(Employee employee);

    // получение конкретного объекта Employee по id
    Employee findEmployeeById(int id);

    // получение списка всех объектов Employee из базы
    List<Employee> getAllEmployee();

    // изменение конкретного объекта Employee в базе по id
    void setEmployeeById(int id);

    // удаление конкретного объекта Employee из базы по id
    void deleteEmployeeById(int id);
}
