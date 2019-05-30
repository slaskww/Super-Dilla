package player;

import city.City;
import utils.Listener;
import world.World;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AchievementBoard implements Listener, Serializable {

    private Map<String, String> listOfActualBosses;
    private Map<String, String> listOfDefeatedBosses;

    public AchievementBoard(World world) {
       listOfActualBosses = world.getCities().stream().collect(Collectors.toMap(city -> city.getBoss().getName(), City::getName));
       listOfDefeatedBosses = new HashMap<>();
    }


    public void showAchievements(){

        System.out.print("LISTA POKONANYCH ARCYWROGOW: ");
        for (Map.Entry<String, String> entry : listOfDefeatedBosses.entrySet()){
            System.out.print(entry.getKey() + "(" + entry.getValue() + ") ");
        }
        System.out.println();

        System.out.print("LISTA ARCYWROGOW DO POKONANIA: ");
        for (Map.Entry<String, String> entry : listOfActualBosses.entrySet()){
            System.out.print(entry.getKey() + "(" + entry.getValue() + ") ");
        }
        System.out.println();
        System.out.println();


    }


    @Override
    public void update(String message) {

        String cityOfDefeatedBoss = listOfActualBosses.get(message);
        listOfDefeatedBosses.put(message, cityOfDefeatedBoss);
    }
}
