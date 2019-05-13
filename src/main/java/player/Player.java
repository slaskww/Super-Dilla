package player;

import bank.BankAccount;
import drug.Drug;
import generalplayer.Person;
import generalplayer.PersonType;
import weapon.Weapon;

import java.util.Map;

public class Player extends Person {

    private Map<Drug, Integer> backpackWithDope;
    private BankAccount account;

    public Player(String name, PersonType personType, Integer offensiveLevel, Integer defensiveLevel, Weapon weapon, Map<Drug, Integer> backpackWithDope, BankAccount account) {
        super(name, personType, offensiveLevel, defensiveLevel, weapon);
        this.backpackWithDope = backpackWithDope;
        this.account = account;
    }

    public Map<Drug, Integer> getBackpackWithDope() {
        return backpackWithDope;
    }

    public BankAccount getAccount() {
        return account;
    }


}

