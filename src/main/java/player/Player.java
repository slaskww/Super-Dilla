package player;

import bank.BankAccount;
import drug.Drug;
import generalplayer.Person;
import weapon.Weapon;

import java.util.Map;

public class Player extends Person {

    private Map<Drug, Integer> drugs;
    private BankAccount account;


    public Player(String name, Integer offensiveLevel, Integer defensiveLevel, Weapon weapon) {
        super(name, offensiveLevel, defensiveLevel, weapon);

    }
}
