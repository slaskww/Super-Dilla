package world;

import city.City;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class World implements Serializable {

    private List<City> cities;

    public World() {
        this.cities = new ArrayList<>();
    }

    public List<City> getCities() {
        return cities;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }
}
