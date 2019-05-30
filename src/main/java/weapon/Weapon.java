package weapon;

import java.io.Serializable;
import java.math.BigDecimal;

public class Weapon implements Serializable {

    private String name;
    private WeaponType type;
    private Integer offensiveLevel;
    private Integer defensiveLevel;
    private BigDecimal price;
    private Double weight;
    private String description;

    public Weapon(String name, WeaponType type, Integer offensiveLevel, Integer defensiveLevel, BigDecimal price, Double weight, String description) {
        this.name = name;
        this.type = type;
        this.offensiveLevel = offensiveLevel;
        this.defensiveLevel = defensiveLevel;
        this.price = price;
        this.weight = weight;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public WeaponType getType() {
        return type;
    }

    public Integer getOffensiveLevel() {
        return offensiveLevel;
    }

    public Integer getDefensiveLevel() {
        return defensiveLevel;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Double getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }
}
