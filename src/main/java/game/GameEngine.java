package game;

import dialogAgent.ConsoleDialogAgent;
import dialogAgent.DialogAgent;
import dialogAgent.EventType;
import player.Player;
import world.World;
import world.WorldBuilder;

public class GameEngine {
    private int MAX_NUMBER_OF_DAYS = 60;
    private int daysFromStart = 0;
    private World world;
    private Player player;
    private DialogAgent dialogAgent;

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

        while (daysFromStart == MAX_NUMBER_OF_DAYS || !player.isAlive()){

            daysFromStart++;
            System.out.println(daysFromStart);

        }
    }

    public void setDialogAgent(DialogAgent dialogAgent) {
        this.dialogAgent = dialogAgent;
    }
}
