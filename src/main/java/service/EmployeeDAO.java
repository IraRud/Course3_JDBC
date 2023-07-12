package service;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {

    void addEmployee(Employee employee);

    Employee findEmployeeById(int id);

    List<Employee> getAllEmployees();

    void setEmployee(Employee employee);

    void deleteEmployee(Employee employee);
}
