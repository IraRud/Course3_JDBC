import config.ConnectionConfig;
import model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {

        service.EmployeeDAOImpl employeeDAOImpl = new service.EmployeeDAOImpl();

        // получение списка всех объектов Employee из базы
        System.out.println("Список всех сотрудников:");
        employeeDAOImpl.getAllEmployees().forEach(System.out::println);

        // создание (добавление) сущности Employee в таблицу
        Employee employee = new Employee(7, "Бред", "Питт", "муж.", 51, 2);
        employeeDAOImpl.addEmployee(employee);

        // получение конкретного объекта Employee по id
        Employee foundEmployee = employeeDAOImpl.findEmployeeById(employee.getId());
        System.out.println("Информация о сотруднике с id = " + foundEmployee.getId());
        System.out.println(foundEmployee);

        // изменение конкретного объекта Employee в базе
        employee.setFirstName("Вэш");
        employee.setLastName("Ураган");
        employeeDAOImpl.setEmployee(employee);
        System.out.println("Информация о сотруднике с id = " + employee.getId());
        System.out.println(employee);

        // удаление конкретного объекта Employee из базы
        employeeDAOImpl.deleteEmployee(employee);

    }


    private static void task_1() {

        try (final PreparedStatement statement =
                     ConnectionConfig.getConnection().prepareStatement("SELECT * FROM employee WHERE id < 4")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int idOfEmployee = resultSet.getInt("id");
                System.out.println("ID сотрудника: " + idOfEmployee);

                String firstName = resultSet.getString("first_name");
                System.out.println("Имя сотрудника: " + firstName);

                String lastName = resultSet.getString("last_name");
                System.out.println("Фамилия сотрудника: " + lastName);

                String gender = resultSet.getString("gender");
                System.out.println("Пол: " + gender);

                int age = resultSet.getInt("age");
                System.out.println("Возраст: " + age);

                int cityName = resultSet.getInt("city_id");
                System.out.println("Город: " + cityName);
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
    }

}