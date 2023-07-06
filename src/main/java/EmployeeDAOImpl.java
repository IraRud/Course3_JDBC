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

            String sql = "SELECT * FROM employee WHERE id = " + id;
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idOfEmployee = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int amountOfCity = resultSet.getInt("city_id");
                employee = new Employee(idOfEmployee, firstName, lastName, gender, age, amountOfCity);
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
                int idOfEmployee = resultSet.getInt("id");
//                System.out.println("ID сотрудника: " + idOfEmployee);

                String firstName = resultSet.getString("first_name");
//                System.out.println("Имя сотрудника: " + firstName);

                String lastName = resultSet.getString("last_name");
//                System.out.println("Фамилия сотрудника: " + lastName);

                String gender = resultSet.getString("gender");
//                System.out.println("Пол: " + gender);

                int age = resultSet.getInt("age");
//                System.out.println("Возраст: " + age);

                int city = resultSet.getInt("city_id");
//                System.out.println("id_города: " + city);

                employees.add(new Employee(idOfEmployee, firstName, lastName, gender, age, city));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
        return employees;
    }

    // изменение конкретного объекта Employee в базе по id
    @Override
    public void setEmployeeById(int id) {
        try (final Connection connection = DriverManager.getConnection(url, user, password)
        ) {
            String sql = "UPDATE employee SET first_name = ?, last_name  = ?, gender = ?, age = ?, city_id  = ?) " +
                    "WHERE id = " + id;
            PreparedStatement statement = connection.prepareStatement(sql);

            Employee employee = findEmployeeById(id);

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, 0);
            statement.setInt(5, employee.getCity());

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
            String sql = "DELETE FROM employee WHERE id = " + id;
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.executeUpdate();
            System.out.println("Сотрудник id = " + id + " успешно удален!");

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к БД!");
            e.printStackTrace();
        }
    }

}
