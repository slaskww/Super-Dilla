package city.facility;

import city.Robberable;
import city.RobberyStatus;
import generalplayer.Person;
import player.Player;

import java.math.BigDecimal;
import java.util.Random;

public class Facility implements Robberable {

    private static BigDecimal INITIAL_AMOUNT_IN_CASHBOX = BigDecimal.valueOf(200);
    private static Integer RESISTANCE_BOOSTING_RATIO = 4;
    private Random rand;
    private final Double ROE = 0.2; //Return On Equity
    private String name;
    private BigDecimal price;
    private BigDecimal cashBox = INITIAL_AMOUNT_IN_CASHBOX; //money in cash box can be stolen by a robber
    private Integer defensiveBenefitFromUsing;
    private Integer offensiveBenefitFromUsing;
    private Integer mentalBenefitFromUsing;
    private Double resistanceToRobbery;


    public Facility(String name, BigDecimal price, Integer defensiveBenefitFromUsing, Integer offensiveBenefitFromUsing, Integer mentalBenefitFromUsing) {
        this.name = name;
        this.price = price;
        this.defensiveBenefitFromUsing = defensiveBenefitFromUsing;
        this.offensiveBenefitFromUsing = offensiveBenefitFromUsing;
        this.mentalBenefitFromUsing = mentalBenefitFromUsing;
        this.rand = new Random();
        setResistanceToRobbery();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getMentalBenefitFromUsing() {
        return mentalBenefitFromUsing;
    }

    public Integer getDefensiveBenefitFromUsing() {
        return defensiveBenefitFromUsing;
    }

    public Integer getOffensiveBenefitFromUsing() {
        return offensiveBenefitFromUsing;
    }

    public void addBenefitFromUsing(Person person) {
        person.boostDefensiveLevel(defensiveBenefitFromUsing);
        person.boostOffensiveLevel(offensiveBenefitFromUsing);
        person.boostMentalLevel(mentalBenefitFromUsing);
        this.cashBox = (cashBox.add(price)).multiply(BigDecimal.valueOf(ROE));
    }


    private void setResistanceToRobbery() {
        this.resistanceToRobbery = (defensiveBenefitFromUsing + offensiveBenefitFromUsing + mentalBenefitFromUsing) * (rand.nextInt(RESISTANCE_BOOSTING_RATIO) + 1) * rand.nextDouble();

    }

    @Override
    public RobberyStatus rob(Player player) {

        if (player.getOverallSkill() > this.resistanceToRobbery) {
            player.getSmartBackpack().updateWallet(this.cashBox);
            return RobberyStatus.FACILITY_WAS_ROBBED;
        } else {
            player.kill();
            return RobberyStatus.FACILITY_WAS_NOT_ROBBED;
        }

    }

    public Double getResistanceToRobbery() {
        return resistanceToRobbery;
    }
}
