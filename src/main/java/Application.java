import java.sql.*;

public class Application {
    public static void main(String[] args) {

        // Задаем параметры для подключения к базе данных:
        // имя пользователя, пароль и URL
        final String user = "postgres";
        final String password = "7899";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        // Создаем соединение с базой данных внутри try-with-resources,
        // чтобы автоматически закрыть его после использования
        try (final Connection connection =
                     DriverManager.getConnection(url, user, password)) {

            System.out.println("Соединение установлено!");

        } catch (SQLException e) {
            // В случае возникновения ошибки при работе с базой данных
            // выводим сообщение об ошибке и информацию об исключении
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
    }
}