package weapon;

import java.math.BigDecimal;

public class Weapon {

    private String name;
    private WeaponType type;
    private Integer offensiveLevel;
    private Integer defensiveLevel;
    private BigDecimal price;
    private Double weight;

    public Weapon(String name, WeaponType type, Integer offensiveLevel, Integer defensiveLevel, BigDecimal price, Double weight) {
        this.name = name;
        this.type = type;
        this.offensiveLevel = offensiveLevel;
        this.defensiveLevel = defensiveLevel;
        this.price = price;
        this.weight = weight;
    }
}
