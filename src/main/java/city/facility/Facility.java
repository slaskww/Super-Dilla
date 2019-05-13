package city.facility;

import generalplayer.Person;

import java.math.BigDecimal;

public class Facility {

    private String name;
    private BigDecimal price;
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

    public Person addBenefitFromUsing(Person person){
        person.boostDefensiveLevel(defensiveBenefitFromUsing);
        person.boostOffensiveLevel(offensiveBenefitFromUsing);
        person.boostOffensiveLevel(mentalBenefitFromUsing);

        return person;
    }

}
