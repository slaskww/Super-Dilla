package enemy;

import generalplayer.Person;
import generalplayer.PersonType;
import weapon.Weapon;

import java.math.BigDecimal;

public class Enemy extends Person {

    private boolean isCorruptible; //czy wróg jest skłonny wziąć łapówkę
    public final BigDecimal BRIBE = BigDecimal.valueOf(500);

    public Enemy(String name, PersonType personType, Integer offensiveLevel, Integer defensiveLevel, Integer mentalLevel , Weapon weapon, boolean isCorruptible) {
        super(name, personType, offensiveLevel, defensiveLevel, mentalLevel ,weapon);
        this.isCorruptible = isCorruptible;
    }

    public boolean isCorruptible() {
        return isCorruptible;
    }
}
