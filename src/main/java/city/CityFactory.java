package city;

import enemy.EnemyFactory;


public class CityFactory {

    public static City grassbay(){
        return new City("Grassbay", EnemyFactory.elChipotle());
    }

    public static City whiteMountain(){
        return new City("White Mountain", EnemyFactory.pabloHabanero());
    }

    public static City dopeTown(){
        return new City("Dope Town", EnemyFactory.vitoCapsicio());
    }

    public static City crystalRiver(){
        return new City("Crystal River", EnemyFactory.kingpin());
    }

}
