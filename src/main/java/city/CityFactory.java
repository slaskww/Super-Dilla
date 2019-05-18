package city;

import enemy.EnemyFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class CityFactory {

    private static List<City> mapOfCities = new ArrayList<>();

    public static City grassbay(){
        City city = new City("Grassbay", EnemyFactory.elChipotle(), BigDecimal.valueOf(100));
        mapOfCities.add(city);
        return city;
    }

    public static City whiteMountain(){
        City city =  new City("White Mountain", EnemyFactory.pabloHabanero(), BigDecimal.valueOf(200));
        mapOfCities.add(city);
        return city;
    }

    public static City dopeTown(){
        City city =   new City("Dope Town", EnemyFactory.vitoCapsicio(), BigDecimal.valueOf(400));
        mapOfCities.add(city);
        return city;
    }

    public static City crystalRiver(){
        City city =  new City("Crystal River", EnemyFactory.kingpin(), BigDecimal.valueOf(700));
        mapOfCities.add(city);
        return city;
    }

    public static List<City> mapOfCities(){
        return mapOfCities;
    }
}
