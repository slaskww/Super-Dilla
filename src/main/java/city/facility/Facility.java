package city.facility;

import city.Robberable;
import generalplayer.Person;
import player.Player;

import java.math.BigDecimal;

public class Facility implements Robberable {

    private static BigDecimal INITIAL_BALANCE_IN_CASHBOX = BigDecimal.valueOf(200);
    private final Double ROE = 0.2; //Return On Equity
    private String name;
    private BigDecimal price;
    private BigDecimal cashBox = INITIAL_BALANCE_IN_CASHBOX; //money in cash box can be stolen by a robber
    private Integer defensiveBenefitFromUsing;
    private Integer offensiveBenefitFromUsing;
    private Integer mentalBenefitFromUsing;



    public Facility(String name, BigDecimal price, Integer defensiveBenefitFromUsing, Integer offensiveBenefitFromUsing, Integer mentalBenefitFromUsing) {
        this.name = name;
        this.price = price;
        this.defensiveBenefitFromUsing = defensiveBenefitFromUsing;
        this.offensiveBenefitFromUsing = offensiveBenefitFromUsing;
        this.mentalBenefitFromUsing = mentalBenefitFromUsing;
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

    public void addBenefitFromUsing(Person person){
        person.boostDefensiveLevel(defensiveBenefitFromUsing);
        person.boostOffensiveLevel(offensiveBenefitFromUsing);
        person.boostMentalLevel(mentalBenefitFromUsing);
        this.cashBox = (cashBox.add(price)).multiply(BigDecimal.valueOf(ROE));
    }

    @Override
    public void rob(Player player) {

    }
}
