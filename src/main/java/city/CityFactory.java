package city;

import enemy.EnemyFactory;

import java.math.BigDecimal;


public class CityFactory {


    public static City grassbay(){
        City city = new City("Grassbay", EnemyFactory.elChipotle(), BigDecimal.valueOf(100));
        return city;
    }

    public static City whiteMountain(){
        City city =  new City("White Mountain", EnemyFactory.pabloHabanero(), BigDecimal.valueOf(200));
        return city;
    }

    public static City dopeTown(){
        City city =   new City("Dope Town", EnemyFactory.vitoCapsicio(), BigDecimal.valueOf(400));
        return city;
    }

    public static City crystalRiver(){
        City city =  new City("Crystal River", EnemyFactory.kingpin(), BigDecimal.valueOf(700));
        return city;
    }


}
