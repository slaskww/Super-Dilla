package world;

import city.CityFactory;

public class WorldBuilder {

    private World world;

    public WorldBuilder() {
        this.world = new World();
    }

    public WorldBuilder addGrassbay(){
        this.world.addCity(CityFactory.grassbay());
        return this;
    }

    public WorldBuilder addDopeTown(){
        this.world.addCity(CityFactory.dopeTown());
        return this;
    }

    public WorldBuilder addWhiteMountain(){
        this.world.addCity(CityFactory.whiteMountain());
        return this;
    }

    public WorldBuilder addCrystalRiver(){
        this.world.addCity(CityFactory.crystalRiver());
        return this;
    }

    public World build(){
        return this.world;
    }

}
