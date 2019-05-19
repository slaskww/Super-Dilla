package world;

import city.City;
import city.CityFactory;

import java.util.ArrayList;
import java.util.List;

public class WorldBuilder {

    private World world;
    private static List<City> mapOfCities = new ArrayList<>();


    public WorldBuilder() {
        this.world = new World();
    }

    public WorldBuilder addGrassbay(){
        this.world.addCity(CityFactory.grassbay());
        mapOfCities.add(CityFactory.grassbay());
        return this;
    }

    public WorldBuilder addDopeTown(){
        this.world.addCity(CityFactory.dopeTown());
        mapOfCities.add(CityFactory.dopeTown());
        return this;
    }

    public WorldBuilder addWhiteMountain(){
        this.world.addCity(CityFactory.whiteMountain());
        mapOfCities.add(CityFactory.whiteMountain());
        return this;
    }

    public WorldBuilder addCrystalRiver(){
        this.world.addCity(CityFactory.crystalRiver());
        mapOfCities.add(CityFactory.crystalRiver());
        return this;
    }

    public World build(){
        return this.world;
    }

    public static List<City> mapOfCities(){
        return mapOfCities;
    }

}
