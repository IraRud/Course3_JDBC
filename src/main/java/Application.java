import model.City;
import model.Employee;
import service.CityDAOImpl;
import service.EmployeeDAOImpl;

import java.util.List;

public class Application {

    public static EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
    public static CityDAOImpl cityDAOImpl = new CityDAOImpl();

    public static void main(String[] args) {

        City city = City.builder()
                .name("Осака")
                .build();

        Employee employee1 = Employee.builder()
                .firstName("Юджи")
                .lastName("Нишида")
                .gender("муж.")
                .age(23)
                .city(city)
                .build();

        Employee employee2 = Employee.builder()
                .firstName("Ран")
                .lastName("Такахаши")
                .gender("муж.")
                .age(21)
                .city(city)
                .build();

        cityDAOImpl.addCity(city); // добавление города в таблицу

        city.setEmployees(List.of(employee1, employee2)); // добавление сотрудников в город
        City updatedCity = cityDAOImpl.setCity(city); // обновление города

        checkEmployees(updatedCity); // проверка

        System.out.println("Список всех городов: ");
        cityDAOImpl.getAllCities().forEach(System.out::println);

        System.out.println("Список всех сотрудников: ");
        employeeDAOImpl.getAllEmployees().forEach(System.out::println);

        Employee employee3 = Employee.builder()
                .firstName("Вэш")
                .lastName("Ураган")
                .gender("муж.")
                .age(131)
                .city(city)
                .build();

        // замена сотрудника - employee2 удалится, т.к. связь разрывается и orphanRemoval = true (удаление сироты)
        updatedCity.setEmployees(List.of(employee1, employee3));
        City updatedCity2 = cityDAOImpl.setCity(updatedCity); // обновление города
        checkEmployees(updatedCity2); // проверка

        // список сотрудников города, который будет удален
        List<Employee> futureDeletedEmployees = updatedCity2.getEmployees();
        // поиск удаленного города по id (должен вернуть null)
        System.out.println("id: " + cityDAOImpl.findCityById(cityDAOImpl.deleteCity(updatedCity2)));

        System.out.println("Содержатся сотрудники? - "
                + employeeDAOImpl.getAllEmployees().containsAll(futureDeletedEmployees)); // false

    }

    public static void checkEmployees(City city) { // проверка добавления сотрудников
        System.out.println(city.getName() + ": "
                + employeeDAOImpl.getAllEmployees().containsAll(city.getEmployees()));
    }

}