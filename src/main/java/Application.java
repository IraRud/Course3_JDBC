import java.sql.*;

public class Application {
    public static void main(String[] args) {

//        // Задаем параметры для подключения к базе данных:
//        // имя пользователя, пароль и URL
//        final String user = "postgres";
//        final String password = "7899";
//        final String url = "jdbc:postgresql://localhost:5432/skypro";
//
//        // Создаем соединение с базой данных внутри try-with-resources,
//        // чтобы автоматически закрыть его после использования
//        try (final Connection connection =
//                     DriverManager.getConnection(url, user, password);
//             // Создаем объект PreparedStatement с SQL-запросом
//             // для выборки данных об одном из работников(имя, фамилия, пол, город) по id
//             PreparedStatement statement =
//                     connection.prepareStatement("SELECT * FROM city RIGHT JOIN employee ON city.city_id = employee.city_id WHERE id = 4")) {
//
//            // Выполняем SQL-запрос
//            // и получаем результаты в виде объекта ResultSet
//            ResultSet resultSet = statement.executeQuery();
//
//            // Обрабатываем результаты запроса, перебирая все записи в таблице
//            while (resultSet.next()) {
//                // Получаем значения полей записи и выводим их на экран
//                int idOfEmployee = resultSet.getInt("id");
//                System.out.println("ID сотрудника: " + idOfEmployee);
//
//                String firstName = resultSet.getString("first_name");
//                System.out.println("Имя сотрудника: " + firstName);
//
//                String lastName = resultSet.getString("last_name");
//                System.out.println("Фамилия сотрудника: " + lastName);
//
//                String gender = resultSet.getString("gender");
//                System.out.println("Пол: " + gender);
//
//                int age = resultSet.getInt("age");
//                System.out.println("Возраст: " + age);
//
//                String cityName = resultSet.getString("city_name");
//                System.out.println("Город: " + cityName);
//            }
//
//        } catch (SQLException e) {
//            // В случае возникновения ошибки при работе с базой данных
//            // выводим сообщение об ошибке и информацию об исключении
//            System.out.println("Ошибка при подключении к базе данных!");
//            e.printStackTrace();
//        }
    }
}