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
    private TimeInGame time;
    private Announcer endOfTheDayAnnouncer;

    public GameEngine(Player player, Announcer announcer, TimeInGame time) {
        this.player = player;
        this.endOfTheDayAnnouncer = announcer;
        this.time = time;
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
            time.showFullHeader(player); //time + skills
            dialogAgent.spectate(EventType.NEW_DAY);

            if (!player.isAlive()){
                break;}

            dialogAgent.spectate(EventType.FIGHT);
           // MessageCreator.executeLog(); //
            endOfTheDayAnnouncer.informListeners(time.getDay().toString());

        }

       if (player.isAlive()){
           dialogAgent.spectate(EventType.COMPLETE_THE_GAME);
       } else{
       dialogAgent.spectate(EventType.DEATH);
       }
    }

    public void setDialogAgent(DialogAgent dialogAgent) {
        this.dialogAgent = dialogAgent;
    }
}
