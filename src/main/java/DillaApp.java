import dialogAgent.ConsoleDialogAgent;
import dialogAgent.VisualConsoleAgent;
import game.GameEngine;
import player.Player;
import utils.Announcer;
import utils.ToFileListener;

public class DillaApp {

    public static void main(String[] args) {

        Announcer announcer = new Announcer();
        announcer.addListener(new ToFileListener());
        Player player = new Player("Player", announcer);
        GameEngine game = new GameEngine(player);
        game.prepareGame();
        VisualConsoleAgent visualConsoleAgent = VisualConsoleAgent.getVisualConsoleAgent();
        ConsoleDialogAgent agent = new ConsoleDialogAgent(player, visualConsoleAgent);
        game.setDialogAgent(agent);
        game.start();

    }
}
