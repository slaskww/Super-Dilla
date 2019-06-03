package player;

import backpack.SmartBackpack;
import city.City;
import city.CityFactory;
import enemy.Enemy;
import generalplayer.Person;
import generalplayer.PersonType;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Announcer;
import utils.Listener;
import weapon.Weapon;
import weapon.WeaponFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Player extends Person implements Listener, Serializable {

    private SmartBackpack smartBackpack;
    private City city;
    private static PersonType PLAYER_PERSON_TYPE = PersonType.PLAYER;
    private static final Integer INITIAL_OFFENSIVE_LEVEL = 100;
    private static final Integer INITIAL_DEFENSIVE_LEVEL = 100;
    private static final Integer INITIAL_MENTAL_LEVEL = 60;
    private static final Weapon BASIC_WEAPON = WeaponFactory.kitchenKnife();
    private Announcer announcer;
    private final Logger log = LogManager.getLogger(Player.class.getName());

    public Player(String name, Announcer announcer) {
        super(name, PLAYER_PERSON_TYPE, INITIAL_OFFENSIVE_LEVEL, INITIAL_DEFENSIVE_LEVEL, INITIAL_MENTAL_LEVEL ,BASIC_WEAPON);
        this.smartBackpack = new SmartBackpack();
        this.city = CityFactory.grassbay();
        this.announcer = announcer;
    }

    public Player(String name, Announcer announcer, Integer offensiveLevel, Integer defensiveLevel, Integer mentalLevel) {
        super(name, PLAYER_PERSON_TYPE, offensiveLevel, defensiveLevel, mentalLevel ,BASIC_WEAPON);
        this.smartBackpack = new SmartBackpack();
        this.city = CityFactory.grassbay();
        this.announcer = announcer;
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

    public void setCity(City city) {
        this.city = city;
    }

    public void setSmartBackpack(SmartBackpack smartBackpack) {
        this.smartBackpack = smartBackpack;
    }

    public String getPerson(){

        StringBuilder builder = new StringBuilder();
        builder.append("\tDefensive: ").append(super.getDefensiveLevel()).append("\n");
        builder.append("\tOffensive: ").append(super.getOffensiveLevel()).append("\n");
        builder.append("\tMental: ").append(super.getMentalLevel()).append("\n");
        builder.append("\tWeapon: ").append(super.getWeapon().getName()).append("\n");

        return builder.toString();
    }

    public String getName(){
        return super.getName();
    }

    public void changeCity(City newCity){
        city = newCity;
        String message = this.getName() + " moved to " + this.city + " with capital " + this.getBalance();
        announcer.informListeners(message);
    }


    public void reduceStrengthAfterFight(Enemy enemy){
        this.boostOffensiveLevel(-enemy.getOffensiveLevel());
        this.boostDefensiveLevel(-enemy.getDefensiveLevel());
        this.boostMentalLevel(-enemy.getMentalLevel());
    }


    @Override
    public void update(String message) {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Day ");
                messageBuilder.append(message);
                messageBuilder.append(", player=");
                messageBuilder.append(this.getName());
                messageBuilder.append(", city=");
                messageBuilder.append(this.city.getName());
                messageBuilder.append(", balance=");
                messageBuilder.append(this.getBalance().setScale(2, RoundingMode.HALF_UP));
                messageBuilder.append(", offensive=");
                messageBuilder.append(getOffensiveLevel());
                messageBuilder.append(", defensive=");
                messageBuilder.append(this.getDefensiveLevel());
                messageBuilder.append(", mental=");
                messageBuilder.append(this.getMentalLevel());
                messageBuilder.append(", weapon=");
                messageBuilder.append(this.getWeapon().getName());

      //  MessageCreator.appendMessage(messageBuilder.toString());
        log.log(Level.INFO, messageBuilder.toString());
    }

}

