package dialogAgent;
import player.Player;
import java.util.Random;
import java.util.Scanner;


public class ConsoleDialogAgent implements DialogAgent {

    private VisualConsoleAgent visualAgent;
    private Player player;
    private Scanner input;
    private Random rand;
    public static int MAX_NUMBER_OF_EVENTS_PER_DAY = 3;
    public static Integer MILD_DEPRESSION = -1;
    public static final int SINGLE_ACTIVITY_TIME_SPAN = 1;
    public static final int FULL_DAY_ACTIVITY_TIME_SPAN = 3;
    public static final int ZERO_ACTIVITY_TIME_SPAN = 0;


    public ConsoleDialogAgent(Player player, VisualConsoleAgent visualAgent) {
        this.player = player;
        input = new Scanner(System.in);
        rand = new Random();
        this.visualAgent = visualAgent;
    }

    @Override
    public void spectate(EventType type) {


        switch (type) {
            case GAME_STARTED:
                visualAgent.startGameStatement();
                visualAgent.forceEnterAction();
                break;

            case FIRST_DAY:
                visualAgent.firstDayStatement(player);
                executeDay();
                break;

            case NEW_DAY:
                visualAgent.newDayStatement(player);
                player.getCity().getMarket().changePrices();
                executeDay();
                break;

            case FIGHT:
                visualAgent.fight(player);
                visualAgent.forceEnterAction();
                break;

        }
    }

    private void executeDay() {

        int numberOfEventsPerDay = 0;

        while (numberOfEventsPerDay < MAX_NUMBER_OF_EVENTS_PER_DAY && player.isAlive()) {
            visualAgent.showOptions();
            Integer chosenPlace = visualAgent.getChoice();
            numberOfEventsPerDay += goTo(chosenPlace);
            visualAgent.forceEnterAction();
        }

        if (!player.isAlive()) {
            return;
        }

        player.boostMentalLevel(MILD_DEPRESSION);
        visualAgent.statementAtTheEndOfTheDay();
     //   visualAgent.forceEnterAction();

    }


    private int goTo(Integer chosenPlace) {


        switch (chosenPlace) {
            case 1:
                visualAgent.handleMarket(player);
                break;
            case 2:
                visualAgent.handleHospital(player);
                break;
            case 3:
                visualAgent.handlePub(player);
                break;
            case 4:
                visualAgent.handleRestaurant(player);
                break;
            case 5:
                visualAgent.handleChurch(player);
                break;
            case 6:
                visualAgent.handleGym(player);
                break;
            case 7:
                visualAgent.handleChangingTheCity(player);
                break;
            case 8:
                player.kill();
                break;
            case 9:
                visualAgent.showBackpack(player);
                return ZERO_ACTIVITY_TIME_SPAN;
            case 10:
                return FULL_DAY_ACTIVITY_TIME_SPAN;

            case 11:
                visualAgent.handleBank(player);
                break;
            case 997:
                visualAgent.handleGunShop(player);
                break;

        }

        return SINGLE_ACTIVITY_TIME_SPAN;
    }


}
