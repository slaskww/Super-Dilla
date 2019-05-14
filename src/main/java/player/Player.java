package player;

import backpack.SmartBackpack;
import city.City;
import city.CityFactory;
import generalplayer.Person;
import generalplayer.PersonType;
import weapon.Weapon;

import java.math.BigDecimal;

public class Player extends Person {

    private SmartBackpack smartBackpack;
    private Integer mentalHealthLevel;
    private City city;
    public static final Integer INITIAL_OFFENSIVE_LEVEL = 50;
    public static final Integer INITIAL_DEFENSIVE_LEVEL = 50;
    public final Integer INITIAL_MENTAL_LEVEL = 50;


    public Player(String name, PersonType personType, Weapon weapon) {
        super(name, personType, INITIAL_OFFENSIVE_LEVEL, INITIAL_DEFENSIVE_LEVEL, weapon);
        this.smartBackpack = new SmartBackpack();
        this.mentalHealthLevel = INITIAL_MENTAL_LEVEL;
        this.city = CityFactory.grassbay();
    }

    public SmartBackpack getSmartBackpack() {
        return smartBackpack;
    }

    public BigDecimal getBalance() {
        return smartBackpack.getWalletBalance();
    }

    public Integer getMentalHealthLevel() {
        return mentalHealthLevel;
    }

    public City getCity() {
        return city;
    }

    public void increaseMentalHealthLevel(Integer mentalHealth){
        this.mentalHealthLevel += mentalHealth;
    }
    public void decreaseMentalHealthLevel(Integer mentalHealth){
        this.mentalHealthLevel -= mentalHealth;
    }
}

