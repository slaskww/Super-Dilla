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
    private City city;
    private static PersonType PLAYER_PERSON_TYPE = PersonType.PLAYER;
    private static final Integer INITIAL_OFFENSIVE_LEVEL = 50;
    private static final Integer INITIAL_DEFENSIVE_LEVEL = 50;
    private static final Integer INITIAL_MENTAL_LEVEL = 50;
    private static final Weapon BASIC_WEAPON = WeaponFactory.kitchenKnife();

    public Player(String name) {
        super(name, PLAYER_PERSON_TYPE, INITIAL_OFFENSIVE_LEVEL, INITIAL_DEFENSIVE_LEVEL, INITIAL_MENTAL_LEVEL ,BASIC_WEAPON);
        this.smartBackpack = new SmartBackpack();
        this.city = CityFactory.grassbay();
    }

    public SmartBackpack getSmartBackpack() {
        return smartBackpack;
    }

    public BigDecimal getBalance() {
        return smartBackpack.getWalletBalance();
    }

    public City getCity() {
        return city;
    }

    public String getPerson(){

        StringBuilder builder = new StringBuilder();
        builder.append("\tDefensive: ").append(super.getDefensiveLevel()).append("\n");
        builder.append("\tOffensive: ").append(super.getOffensiveLevel()).append("\n");
        builder.append("\tMental: ").append(super.getMentalLevel()).append("\n");
        builder.append("\tWeapon: ").append(super.getWeapon().getName()).append("\n");

        return builder.toString();
    }

}

