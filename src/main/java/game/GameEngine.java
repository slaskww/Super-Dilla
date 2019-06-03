package game;

import dialogAgent.DialogAgent;
import dialogAgent.EventType;
import drug.DrugMarket;
import drug.DrugType;
import player.AchievementBoard;
import player.Player;
import utils.Announcer;
import utils.Listener;
import utils.SaveAndLoadGameUtil;
import world.World;
import world.WorldBuilder;

import java.math.BigDecimal;
import java.util.Map;

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

        if (SaveAndLoadGameUtil.isLoadingThePreviousGameStateChosen()) {
            SaveAndLoadGameUtil.loadGameFromFile(this);

        }

        if (time.day.equals(TimeInGame.INITIAL_DAY)) {
            dialogAgent.spectate(EventType.GAME_STARTED);
            time.showFullHeader(player);
            achievementBoard.showAchievements();
            dialogAgent.spectate(EventType.FIRST_DAY);
            endOfTheDayAnnouncer.informListeners(time.getDay().toString());
        }


        while (time.getDay() < MAX_NUMBER_OF_DAYS && player.isAlive()) {


            time.setNextDay();
            time.showFullHeader(player); //time + skills
            achievementBoard.showAchievements();
            dialogAgent.spectate(EventType.NEW_DAY);

            if (!player.isAlive()) { //player is not alive if mental level = 0 or if it was decided to finish playing

                if (player.getMentalLevel() != 0) { //save the current state of the game if a player decided to finish playing
                    player.resuscitate();
                    SaveAndLoadGameUtil.saveGameToFile(world, player, time, achievementBoard);
                    player.kill();
                }
                break;
            }

            dialogAgent.spectate(EventType.FIGHT);
            // MessageCreator.executeLog(); //
            endOfTheDayAnnouncer.informListeners(time.getDay().toString());

        }

        if (player.isAlive()) {
            dialogAgent.spectate(EventType.COMPLETE_THE_GAME);
        } else {
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


    public void loadGame(World world, Integer day, AchievementBoard board, Player playerAsAPerson,  Map<DrugType, BigDecimal>  pricesOnMarket) {
        this.world = world;
        this.time.setDay(day);
        this.achievementBoard = board;
        this.player.setCity(playerAsAPerson.getCity());
        this.player.setSmartBackpack(playerAsAPerson.getSmartBackpack());
        this.player.setName(playerAsAPerson.getName());
        this.player.setDefensiveLevel(playerAsAPerson.getDefensiveLevel());
        this.player.setOffensiveLevel(playerAsAPerson.getOffensiveLevel());
        this.player.setMentalLevel(playerAsAPerson.getMentalLevel());
        this.player.setWeapon(playerAsAPerson.getWeapon());
        DrugMarket.getDrugMarket().setPriceList(pricesOnMarket);

    }
}
