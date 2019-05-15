package enemy;

import generalplayer.PersonType;
import weapon.WeaponFactory;

public class EnemyFactory {

    public static Enemy policeOfficer(){
    Enemy enemy = new Enemy("Officer Barbrady", PersonType.POLICE_OFFICER, 60, 60, 15 , WeaponFactory.pistol(), true);
    return enemy;
}

    public static Enemy youngDealer() {
        Enemy enemy = new Enemy("Young Dealer", PersonType.DEALER, 15, 15, 20 , WeaponFactory.kitchenKnife(), true);
        return enemy;
    }

    public static Enemy oldDealer() {
        Enemy enemy = new Enemy("Old Dealer", PersonType.DEALER, 45, 45, 30 , WeaponFactory.dagger(), true);
        return enemy;
    }


    public static Enemy elChipotle() {
        Enemy enemy = new Enemy("El Chipotle", PersonType.BOSS, 100, 100, 50 , WeaponFactory.machineGun(), true);
        return enemy;
    }

    public static Enemy pabloHabanero() {
        Enemy enemy = new Enemy("Pablo Habanero", PersonType.BOSS, 150, 150, 60 , WeaponFactory.machineGun(), true);
        return enemy;
    }

    public static Enemy vitoCapsicio() {
        Enemy enemy = new Enemy("Vito Capsicio", PersonType.BOSS, 200, 200, 70 , WeaponFactory.machineGun(), true);
        return enemy;
    }

    public static Enemy kingpin() {
        Enemy enemy = new Enemy("Wilson Fisk", PersonType.BOSS, 250, 250, 80 , WeaponFactory.walkingStick(), true);
        return enemy;
    }

}
