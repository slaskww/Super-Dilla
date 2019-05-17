package city;

import enemy.EnemyFactory;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


public class CityFactory {

    public static City grassbay(){
        return new City("Grassbay", EnemyFactory.elChipotle(), BigDecimal.valueOf(100));
    }

    public static City whiteMountain(){
        return new City("White Mountain", EnemyFactory.pabloHabanero(), BigDecimal.valueOf(200));
    }

    public static City dopeTown(){
        return new City("Dope Town", EnemyFactory.vitoCapsicio(), BigDecimal.valueOf(400));
    }

    public static City crystalRiver(){
        return new City("Crystal River", EnemyFactory.kingpin(), BigDecimal.valueOf(700));
    }

    public static List<City> mapOfCities(){
        return  Arrays.asList(grassbay(), whiteMountain(), dopeTown(), crystalRiver());
    }
}
