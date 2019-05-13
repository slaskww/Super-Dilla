package city;

import city.facility.Facility;
import drug.DrugMarket;
import enemy.Enemy;

import java.util.List;

public class City {

    private String name;
    private DrugMarket market;
    private List<Enemy> enemies;
    private List<Facility> facilities;

    public City(String name, DrugMarket market, List<Enemy> enemies, List<Facility> facilities) {
        this.name = name;
        this.market = market;
        this.enemies = enemies;
        this.facilities = facilities;
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
