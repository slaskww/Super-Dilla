package generalplayer;

import weapon.Weapon;

public abstract class Person {

    private String name;
    private Integer offensiveLevel;
    private Integer defensiveLevel;
    private Weapon weapon;
    private boolean isAlive = true;

    public Person(String name, Integer offensiveLevel, Integer defensiveLevel, Weapon weapon) {
        this.name = name;
        this.offensiveLevel = offensiveLevel;
        this.defensiveLevel = defensiveLevel;
        this.weapon = weapon;
    }


    public String getName() {
        return name;
    }

    public Integer getOffensiveLevel() {
        return this.offensiveLevel;
    }

    public Integer getDeffensiveLevel() {
        return defensiveLevel;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
