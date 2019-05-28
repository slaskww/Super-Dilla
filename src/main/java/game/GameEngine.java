package game;

import dialogAgent.DialogAgent;
import dialogAgent.EventType;
import player.AchievementBoard;
import player.Player;
import utils.Announcer;
import utils.Listener;
import world.World;
import world.WorldBuilder;

public class GameEngine implements Listener {
    private int MAX_NUMBER_OF_DAYS = 60;
    private World world;
    private Player player;
    private DialogAgent dialogAgent;
    private TimeInGame time;
    private Announcer endOfTheDayAnnouncer;
    private AchievementBoard achievementBoard = null;

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
        this.achievementBoard = new AchievementBoard(world);

    }

    public void start() {

        dialogAgent.spectate(EventType.GAME_STARTED);
        time.showFullHeader(player);
        achievementBoard.showAchievements();
        dialogAgent.spectate(EventType.FIRST_DAY);
        endOfTheDayAnnouncer.informListeners(time.getDay().toString());


        while (time.getDay() < MAX_NUMBER_OF_DAYS && player.isAlive()){
            time.setNextDay();
            time.showFullHeader(player); //time + skills
            achievementBoard.showAchievements();
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

    @Override
    public void update(String message) {
        achievementBoard.update(message);
    }
}
