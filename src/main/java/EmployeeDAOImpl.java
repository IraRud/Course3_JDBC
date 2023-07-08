import validate_utils.ValidateUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    final String user = "postgres";
    final String password = "7899";
    final String url = "jdbc:postgresql://localhost:5432/skypro";

    // создание (добавление) сущности Employee в таблицу
    @Override
    public void addEmployee(Employee employee) {
        try (final Connection connection = DriverManager.getConnection(url, user, password)
        ) {
            // как вариант
            String sql = "INSERT INTO employee(id, first_name, last_name, gender, age, city_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getGender());
            statement.setInt(5, employee.getAge());
            statement.setInt(6, employee.getCity());

            statement.executeUpdate();
            System.out.println("Сотрудник id = " + employee.getId() + " успешно добавлен!");

        } catch (
                SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
    }

    // получение конкретного объекта Employee по id
    @Override
    public Employee findEmployeeById(int id) {
        Employee employee = null;
        try (final Connection connection = DriverManager.getConnection(url, user, password)) {

            String sql = "SELECT * FROM employee WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int amountOfCity = resultSet.getInt("city_id");
                employee = new Employee(firstName, lastName, gender, age, amountOfCity);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
        return employee;
    }

    // получение списка всех объектов Employee из базы
    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM employee")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int city = resultSet.getInt("city_id");

                employees.add(new Employee(firstName, lastName, gender, age, city));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
        return employees;
    }

    // изменение конкретного объекта Employee в базе по id
    @Override
    public void setEmployeeById(int id, String firstName, String lastName, String gender, int age, int city) {
        try (final Connection connection = DriverManager.getConnection(url, user, password)
        ) {
            String sql = "UPDATE employee SET first_name = ?, last_name  = ?, gender = ?, age = ?, city_id  = ? " +
                    "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            Employee employee = findEmployeeById(id);

            firstName = ValidateUtils.checkString(employee.getFirstName(), firstName);
            statement.setString(1, firstName);
            lastName = ValidateUtils.checkString(employee.getLastName(), lastName);
            statement.setString(2, lastName);
            gender = ValidateUtils.checkString(employee.getGender(), gender);
            statement.setString(3, gender);
            age = ValidateUtils.checkInt(employee.getAge(), age);
            statement.setInt(4, age);
            city = ValidateUtils.checkInt(employee.getCity(), city);
            statement.setInt(5, city);
            statement.setInt(6, id);

            statement.executeUpdate();
            System.out.println("Данные сотрудника id = " + employee.getId() + " успешно изменены!");

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
    }

    // удаление конкретного объекта Employee из базы по id
    @Override
    public void deleteEmployeeById(int id) {
        try (final Connection connection = DriverManager.getConnection(url, user, password)
        ) {
            String sql = "DELETE FROM employee WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Сотрудник id = " + id + " успешно удален!");

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
    }

}
