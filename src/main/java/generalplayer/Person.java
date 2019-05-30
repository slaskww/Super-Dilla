package generalplayer;

import weapon.Weapon;
import weapon.WeaponFactory;

import java.io.Serializable;

public abstract class Person implements Serializable {

    private String name;
    private PersonType personType;
    private Integer offensiveLevel;
    private Integer defensiveLevel;
    private Integer mentalLevel;
    private Weapon weapon;
    private boolean isAlive = true;

    public Person(String name, PersonType personType, Integer offensiveLevel, Integer defensiveLevel,Integer mentalLevel ,Weapon weapon) {
        this.name = name;
        this.personType = personType;
        this.weapon = weapon;
        this.offensiveLevel = offensiveLevel + weapon.getOffensiveLevel();
        this.defensiveLevel = defensiveLevel + weapon.getDefensiveLevel();
        this.mentalLevel = mentalLevel;

    }

//    public Person(){
//        this.name = "empty";
//        this.personType = PersonType.PLAYER;
//        this.weapon = WeaponFactory.flower();
//        this.offensiveLevel = 0;
//        this.defensiveLevel = 0;
//        this.mentalLevel = 0;
//    }

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

    public Integer getMentalLevel() {
        return mentalLevel;
    }



    public Weapon getWeapon() {
        return weapon;
    }

    public boolean isAlive() {
        killIfMentalIsZero();
        return isAlive;
    }

    public void addWeapon(Weapon weapon) {

        if (this.weapon != null){
            this.dropWeapon();
        }

        this.weapon = weapon;
        this.offensiveLevel += weapon.getOffensiveLevel();
        this.defensiveLevel += weapon.getDefensiveLevel();
    }

    public void dropWeapon() {
        this.offensiveLevel -= weapon.getOffensiveLevel();
        this.defensiveLevel -= weapon.getDefensiveLevel();
        this.weapon = null;
    }


    public void kill() {
        this.isAlive = false;
    }

    public void resuscitate(){
        this.isAlive = true;
    }

    public void boostOffensiveLevel(Integer offensiveLevel) {
        this.offensiveLevel += offensiveLevel;
    }

    public void boostDefensiveLevel(Integer defensiveLevel) {
        this.defensiveLevel += defensiveLevel;
    }

    public void boostMentalLevel(Integer mentalLevel) {
        this.mentalLevel += mentalLevel;
    }

    public String getPerson(){

        StringBuilder builder = new StringBuilder();
        builder.append("\tName: ").append(name).append("\n");
        builder.append("\tType: ").append(personType).append("\n");
        builder.append("\tDefensive: ").append(defensiveLevel).append("\n");
        builder.append("\tOffensive: ").append(offensiveLevel).append("\n");
        builder.append("\tMental: ").append(mentalLevel).append("\n");
        builder.append("\tWeapon: ").append(weapon.getName()).append("\n");

        return builder.toString();
    }

    public void killIfMentalIsZero(){

        if (mentalLevel == 0){
            this.kill();
        }
    }

    public boolean isWeapon(){
        return weapon != null;
    }

    public Integer getOverallSkill(){
        return offensiveLevel + defensiveLevel + mentalLevel;
    }

    public Person getInstanceOfPerson(){
        return this;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setOffensiveLevel(Integer offensiveLevel) {
        this.offensiveLevel = offensiveLevel;
    }

    public void setDefensiveLevel(Integer defensiveLevel) {
        this.defensiveLevel = defensiveLevel;
    }

    public void setMentalLevel(Integer mentalLevel) {
        this.mentalLevel = mentalLevel;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
