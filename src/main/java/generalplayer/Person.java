package generalplayer;

import weapon.Weapon;

public abstract class Person {

    private String name;
    private PersonType personType;
    private Integer offensiveLevel;
    private Integer defensiveLevel;
    private Weapon weapon;
    private boolean isAlive = true;

    public Person(String name, PersonType personType, Integer offensiveLevel, Integer defensiveLevel, Weapon weapon) {
        this.name = name;
        this.personType = personType;
        this.weapon = weapon;
        this.offensiveLevel = offensiveLevel + weapon.getOffensiveLevel();
        this.defensiveLevel = defensiveLevel + weapon.getDefensiveLevel();

    }

    public PersonType getPersonType() {
        return personType;
    }

    public String getName() {
        return name;
    }

    public Integer getOffensiveLevel() {
        return this.offensiveLevel;
    }

    public Integer getDefensiveLevel() {
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
        this.offensiveLevel += weapon.getOffensiveLevel();
        this.defensiveLevel += weapon.getDefensiveLevel();
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void boostOffensiveLevel(Integer offensiveLevel) {
        this.offensiveLevel = offensiveLevel;
    }

    public void boostDefensiveLevel(Integer defensiveLevel) {
        this.defensiveLevel = defensiveLevel;
    }


}
