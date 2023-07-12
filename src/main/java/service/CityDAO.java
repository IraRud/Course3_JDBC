package service;

import model.City;

import java.util.List;

public interface CityDAO {

    void addCity(City city);

    City findCityById(int id);

    List<City> getAllCities();

    City setCity(City city);

    void deleteCity(City city);
}
