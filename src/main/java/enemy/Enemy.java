package enemy;

import generalplayer.Person;
import generalplayer.PersonType;
import weapon.Weapon;

public class Enemy extends Person {

    private boolean isCorruptible; //czy wróg jest skłonny wziąć łapówkę

    public Enemy(String name, PersonType personType, Integer offensiveLevel, Integer defensiveLevel, Weapon weapon, boolean isAlive, boolean isCorruptible) {
        super(name, personType, offensiveLevel, defensiveLevel, weapon, isAlive);
        this.isCorruptible = isCorruptible;
    }
}
