import city.facility.Bank;
import dialogAgent.ConsoleDialogAgent;
import dialogAgent.VisualConsoleAgent;
import drug.DrugMarket;
import game.GameEngine;
import game.TimeInGame;
import player.Player;
import utils.Announcer;
import utils.ToFileListener;

public class DillaApp {

    public static void main(String[] args) {

        Announcer timeInGameAnnouncer = new Announcer();
        timeInGameAnnouncer.addListener(Bank.getBankInstance());
        TimeInGame timeInGame = TimeInGame.getDateInstance(timeInGameAnnouncer);

        Announcer changeTheCityAnnouncer = new Announcer();
        changeTheCityAnnouncer.addListener(new ToFileListener());

        Player player = new Player("Player", changeTheCityAnnouncer);
        Announcer endOfTheDayAnnouncer = new Announcer();
        endOfTheDayAnnouncer.addListener(player);
        endOfTheDayAnnouncer.addListener(DrugMarket.getDrugMarket());
        GameEngine game = new GameEngine(player, endOfTheDayAnnouncer, timeInGame);
        game.prepareGame();
        VisualConsoleAgent visualConsoleAgent = VisualConsoleAgent.getVisualConsoleAgent();
        ConsoleDialogAgent agent = new ConsoleDialogAgent(player, visualConsoleAgent);
        game.setDialogAgent(agent);
        game.start();

    }
}
