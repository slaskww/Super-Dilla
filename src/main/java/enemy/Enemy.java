package enemy;

import generalplayer.Person;
import weapon.Weapon;

public class Enemy extends Person {

    private boolean isCorruptible; //czy wróg jest skłonny wziąć łapówkę

    public Enemy(String name, Integer offensiveLevel, Integer defensiveLevel, Weapon weapon, boolean isCorruptible) {
        super(name, offensiveLevel, defensiveLevel, weapon);
        this.isCorruptible = isCorruptible;
    }
}
