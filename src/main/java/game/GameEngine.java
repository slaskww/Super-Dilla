package game;

import dialogAgent.DialogAgent;
import dialogAgent.EventType;
import player.Player;
import world.World;
import world.WorldBuilder;

public class GameEngine {
    private int MAX_NUMBER_OF_DAYS = 60;
    private World world;
    private Player player;
    private DialogAgent dialogAgent;
    private TimeInGame time = TimeInGame.getDateInstance();

    public GameEngine(Player player) {
        this.player = player;
    }

    public void prepareGame() {

        WorldBuilder builder = new WorldBuilder();
        builder
                .addCrystalRiver()
                .addDopeTown()
                .addGrassbay()
                .addWhiteMountain();

        world = builder.build();

    }

    public void start() {

        dialogAgent.spectate(EventType.GAME_STARTED);
        time.showDay();
        dialogAgent.spectate(EventType.FIRST_DAY);

        while (time.getDay() < MAX_NUMBER_OF_DAYS && player.isAlive()){

         //   System.out.println(player.getPerson());
            player.boostMentalLevel(-1);
            time.setNextDay();
            time.showDay();
            dialogAgent.spectate(EventType.NEW_DAY);
        }
    }

    public void setDialogAgent(DialogAgent dialogAgent) {
        this.dialogAgent = dialogAgent;
    }
}
