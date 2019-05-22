package weapon;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WeaponFactory {

    private static List<Weapon> weapons = new LinkedList<>();

    public WeaponFactory() {

    }

    public static Weapon heavyBar(){
        return new Weapon("Heavy bar", WeaponType.BAR, 10, 10, BigDecimal.valueOf(0),13.2, "dobry do odganiania psow");
    }

    public static Weapon kitchenKnife(){
        return new Weapon("Kitchen Knife", WeaponType.KNIFE, 15, 15, BigDecimal.valueOf(10),4.2, "przebije wszystko, od chleba po kurtki Adidasa");
    }

    public static Weapon dagger(){
        return new Weapon("Pork Dagger", WeaponType.KNIFE, 25, 15, BigDecimal.valueOf(50),9.5, "maly, ale wariat");
    }

    public static Weapon ancientSword(){
        return new Weapon("Narsil", WeaponType.SWORD, 300, 300, BigDecimal.valueOf(20000),17.5, "oto miecz Isildura");
    }

    public static Weapon machineGun(){
        return new Weapon("Machine gun", WeaponType.GUN, 100, 100, BigDecimal.valueOf(5000),17.5,  "strzela, jak na zawolanie");
    }

    public static Weapon pistol(){
        return new Weapon("Automatic pistol", WeaponType.GUN, 75, 75, BigDecimal.valueOf(1000),17.5, "uniwersalna spluwa, na kazda kieszen");
    }

    public static Weapon rifle(){
        return new Weapon("Assault rifle", WeaponType.GUN, 110, 85, BigDecimal.valueOf(1000),17.5, "podobno sam Kingpin uzywal tej laski");
    }

    public static Weapon walkingStick(){
        return new Weapon("Laser walking stick", WeaponType.STICK, 170, 160, BigDecimal.valueOf(50000),12.5, "idealnie lezy w dloni");
    }

    public static Weapon flower(){
        return new Weapon("Bloomy flower", WeaponType.FLOWER, 1, 1, BigDecimal.valueOf(0),1.5, "dobry na alergikow");
    }

    public static List<Weapon> getWeapons(){

        if (weapons.size() == 0)
        { weapons = new LinkedList<>();
            weapons.add(heavyBar());
            weapons.add(kitchenKnife());
            weapons.add(dagger());
            weapons.add(ancientSword());
            weapons.add(machineGun());
            weapons.add(pistol());
            weapons.add(walkingStick());
            weapons.add(flower());
            weapons.add(rifle());
        }

        return weapons;
    }
}
