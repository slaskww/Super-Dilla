package weapon;

import java.math.BigDecimal;

public class WeaponFactory {


    public static Weapon heavyBar(){
        return new Weapon("Heavy bar", WeaponType.BAR, 1, 2, BigDecimal.valueOf(0),13.2);
    }

    public static Weapon kitchenKnife(){
        return new Weapon("Kitchen Knife", WeaponType.KNIFE, 7, 7, BigDecimal.valueOf(10),4.2);
    }

    public static Weapon dagger(){
        return new Weapon("Pork Dagger", WeaponType.KNIFE, 11, 9, BigDecimal.valueOf(50),9.5);
    }

    public static Weapon ancientSword(){
        return new Weapon("Sword of Isildur", WeaponType.SWORD, 100, 100, BigDecimal.valueOf(20000),17.5);
    }

    public static Weapon machineGun(){
        return new Weapon("Machine gun", WeaponType.GUN, 100, 100, BigDecimal.valueOf(5000),17.5);
    }

    public static Weapon pistol(){
        return new Weapon("Automatic pistol", WeaponType.GUN, 75, 75, BigDecimal.valueOf(100),17.5);
    }

    public static Weapon walkingStick(){
        return new Weapon("Laser walking stick", WeaponType.STICK, 120, 120, BigDecimal.valueOf(50000),12.5);
    }

    public static Weapon flower(){
        return new Weapon("Bloomy flower", WeaponType.FLOWER, 0, 0, BigDecimal.valueOf(0),1.5);
    }
}
