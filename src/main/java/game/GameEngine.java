package game;

import dialogAgent.DialogAgent;
import dialogAgent.EventType;
import player.Player;
import utils.Announcer;
import utils.MessageCreator;
import world.World;
import world.WorldBuilder;

public class GameEngine {
    private int MAX_NUMBER_OF_DAYS = 60;
    private World world;
    private Player player;
    private DialogAgent dialogAgent;
    private TimeInGame time = TimeInGame.getDateInstance();
    private Announcer endOfTheDayAnnouncer;

    public GameEngine(Player player, Announcer announcer) {
        this.player = player;
        this.endOfTheDayAnnouncer = announcer;
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
        time.showFullHeader(player);
        dialogAgent.spectate(EventType.FIRST_DAY);
        endOfTheDayAnnouncer.informListeners(time.getDay().toString());


        while (time.getDay() < MAX_NUMBER_OF_DAYS && player.isAlive()){
            time.setNextDay();
            time.showFullHeader(player); //timee + skills
            dialogAgent.spectate(EventType.NEW_DAY);

            if (!player.isAlive()){break;}
            dialogAgent.spectate(EventType.FIGHT);
           // MessageCreator.executeLog(); //
            endOfTheDayAnnouncer.informListeners(time.getDay().toString());

        }
    }

    public void setDialogAgent(DialogAgent dialogAgent) {
        this.dialogAgent = dialogAgent;
    }
}
