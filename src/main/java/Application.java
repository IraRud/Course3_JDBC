import java.sql.*;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        task_1();
        System.out.println("----------------------------");
        task_2();

    }

    private static void task_1() {

        // Задаем параметры для подключения к базе данных:
        // имя пользователя, пароль и URL
        final String user = "postgres";
        final String password = "7899";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        // Создаем соединение с базой данных внутри try-with-resources,
        // чтобы автоматически закрыть его после использования
        try (final Connection connection =
                     DriverManager.getConnection(url, user, password);
             // Создаем объект PreparedStatement с SQL-запросом
             // для выборки данных об одном из работников(имя, фамилия, пол, город) по id
             PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM employee WHERE id < 4")) {

            // Выполняем SQL-запрос
            // и получаем результаты в виде объекта ResultSet
            ResultSet resultSet = statement.executeQuery();

            // Обрабатываем результаты запроса, перебирая все записи в таблице
            while (resultSet.next()) {
                // Получаем значения полей записи и выводим их на экран
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
            // В случае возникновения ошибки при работе с базой данных
            // выводим сообщение об ошибке и информацию об исключении
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
    }

    private static void task_2() {

        EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();

        // получение списка всех объектов Employee из базы
        System.out.println("Список всех сотрудников");
        List<Employee> employees = employeeDAOImpl.getAllEmployee();
        for (Employee employee : employees) {
            printEmployee(employee);
            System.out.println();
        }

        // создание (добавление) сущности Employee в таблицу
        employeeDAOImpl.addEmployee(new Employee(10, "Бред", "Питт", "муж.", 51, 2));

        // получение конкретного объекта Employee по id
        Employee employee = employeeDAOImpl.findEmployeeById(10);
        System.out.println();
        System.out.println("Информация о сотруднике с id = " + employee.getId());
        printEmployee(employee);

        // изменение конкретного объекта Employee в базе по id
        employeeDAOImpl.setEmployeeById(10);

        // удаление конкретного объекта Employee из базы по id
        employeeDAOImpl.deleteEmployeeById(10);

    }

    private static void printEmployee(Employee employee) {
        System.out.println("ID сотрудника: " + employee.getId());
        System.out.println("Имя сотрудника: " + employee.getFirstName());
        System.out.println("Фамилия сотрудника: " + employee.getLastName());
        System.out.println("Пол: " + employee.getGender());
        System.out.println("Возраст: " + employee.getAge());
        System.out.println("id_города: " + employee.getCity());
    }

}