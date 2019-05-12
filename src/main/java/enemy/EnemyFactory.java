package enemy;

import weapon.Weapon;

public class EnemyFactory {

public static Enemy boss(){
    Enemy enemy = new Enemy("Officer Barbrady", 40, 40,  new Weapon());
    return enemy;
}


}
