package player;

import bank.BankAccount;
import drug.Drug;
import generalplayer.Person;
import generalplayer.PersonType;
import weapon.Weapon;

import java.util.Map;

public class Player extends Person {

    private Map<Drug, Integer> drugs;
    private BankAccount account;

    public Player(String name, PersonType personType, Integer offensiveLevel, Integer defensiveLevel, Weapon weapon, Map<Drug, Integer> drugs, BankAccount account) {
        super(name, personType, offensiveLevel, defensiveLevel, weapon);
        this.drugs = drugs;
        this.account = account;
    }
}

