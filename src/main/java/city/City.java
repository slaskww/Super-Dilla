package city;

import city.facility.Facility;
import city.facility.FacilityFactory;
import drug.DrugMarket;
import enemy.Enemy;
import enemy.EnemyFactory;

import java.util.Arrays;
import java.util.List;

public class City {

    private String name;
    private DrugMarket market;
    private List<Enemy> enemies;
    private List<Facility> facilities;

    public City(String name, Enemy boss) {
        this.name = name;
        this.market = DrugMarket.getDrugMarket();
        this.enemies  = Arrays.asList(EnemyFactory.youngDealer(), EnemyFactory.oldDealer(), EnemyFactory.policeOfficer(), boss);
        this.facilities = Arrays.asList(FacilityFactory.church(), FacilityFactory.gym(), FacilityFactory.hospital(), FacilityFactory.pub());
    }

    public String getName() {
        return name;
    }

    public DrugMarket getMarket() {
        return market;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }


}
