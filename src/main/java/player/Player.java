package player;

import backpack.SmartBackpack;
import city.City;
import city.CityFactory;
import generalplayer.Person;
import generalplayer.PersonType;
import weapon.Weapon;
import weapon.WeaponFactory;

import java.math.BigDecimal;

public class Player extends Person {

    private SmartBackpack smartBackpack;
    private Integer mentalHealthLevel;
    private City city;
    private static PersonType PLAYER_PERSON_TYPE = PersonType.PLAYER;
    private static final Integer INITIAL_OFFENSIVE_LEVEL = 50;
    private static final Integer INITIAL_DEFENSIVE_LEVEL = 50;
    private final Integer INITIAL_MENTAL_LEVEL = 50;
    private static final Weapon BASIC_WEAPON = WeaponFactory.kitchenKnife();

    public Player(String name) {
        super(name, PLAYER_PERSON_TYPE, INITIAL_OFFENSIVE_LEVEL, INITIAL_DEFENSIVE_LEVEL, BASIC_WEAPON);
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

