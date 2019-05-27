package city;

import player.Player;

public interface Robberable { //classes that implement this interface can be robbed

   RobberyStatus rob(Player player);

}
