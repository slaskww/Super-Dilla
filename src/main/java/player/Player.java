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
    private Integer mentalHealthLevel;

    public Player(String name, PersonType personType, Integer offensiveLevel, Integer defensiveLevel, Integer mentalHealthLevel ,Weapon weapon, Map<Drug, Integer> backpackWithDope, BankAccount account) {
        super(name, personType, offensiveLevel, defensiveLevel, weapon);
        this.backpackWithDope = backpackWithDope;
        this.account = account;
        this.mentalHealthLevel = mentalHealthLevel;
    }

    public Map<Drug, Integer> getBackpackWithDope() {
        return backpackWithDope;
    }

    public BankAccount getAccount() {
        return account;
    }

    public Integer getMentalHealthLevel() {
        return mentalHealthLevel;
    }

    public void increaseMentalHealthLevel(Integer mentalHealth){
        this.mentalHealthLevel += mentalHealth;
    }
    public void decreaseMentalHealthLevel(Integer mentalHealth){
        this.mentalHealthLevel -= mentalHealth;
    }
}

