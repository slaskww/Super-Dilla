package city;

import city.facility.Facility;
import city.facility.FacilityFactory;
import drug.DrugMarket;
import enemy.Enemy;
import enemy.EnemyFactory;
import generalplayer.PersonType;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class City {

    private String name;
    private DrugMarket market;
    private Bank bank;
    private List<Enemy> ordinaryEnemies;
    private Enemy superEnemy;
    private List<Facility> facilities;
    private BigDecimal costOfTheTicketToGetHere;


    public City(String name, Enemy boss, BigDecimal costOfTheTicketToGetHere) {
        this.name = name;
        this.market = DrugMarket.getDrugMarket();
        this.bank = Bank.getBankInstance();
        this.costOfTheTicketToGetHere = costOfTheTicketToGetHere;
        this.ordinaryEnemies = new LinkedList<>(Arrays.asList(EnemyFactory.youngDealer()
                ,EnemyFactory.youngDealer()
                ,EnemyFactory.oldDealer()
                ,EnemyFactory.oldDealer()
                ,EnemyFactory.policeOfficer()
                ,EnemyFactory.policeOfficer()
                ,EnemyFactory.noone()
                ,EnemyFactory.noone()
                ,EnemyFactory.noone()
                ,EnemyFactory.noone()
                ,EnemyFactory.noone()
                ,EnemyFactory.noone()));

        this.superEnemy = boss;
        this.facilities = Arrays.asList(FacilityFactory.church(), FacilityFactory.gym(), FacilityFactory.hospital(), FacilityFactory.pub());
    }

    public String getName() {
        return name;
    }


    public DrugMarket getMarket() {
        return market;
    }

    public List<Enemy> getEnemies() {
        return ordinaryEnemies;
    }

    public void deleteEnemy(int index){
        if (index < 0 || index >= ordinaryEnemies.size()){
            throw new IndexOutOfBoundsException();
        }
        ordinaryEnemies.remove(index);
    }

    public Enemy getBoss(){return this.superEnemy;}

    public List<Facility> getFacilities() {
        return facilities;
    }

    public boolean isAnyDangerousOrdinaryEnemy(){

        if (ordinaryEnemies.size() == 0){
            return false;
        }

        for (Enemy enemy :ordinaryEnemies){
            if (enemy.getPersonType() != PersonType.NOONE){
                return true;
            }
        }
        return false;
    }

    public Bank getBank() {
        return bank;
    }

    public BigDecimal getCostOfTheTicketToGetHere() {
        return costOfTheTicketToGetHere;
    }

}
