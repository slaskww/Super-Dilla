import dialogAgent.ConsoleDialogAgent;
import dialogAgent.VisualConsoleAgent;
import game.GameEngine;
import player.Player;

public class DillaApp {

    public static void main(String[] args) {

        Player player = new Player("Player");
        GameEngine game = new GameEngine(player);
        game.prepareGame();
        VisualConsoleAgent visualConsoleAgent = VisualConsoleAgent.getVisualConsoleAgent();
        ConsoleDialogAgent agent = new ConsoleDialogAgent(player, visualConsoleAgent);
        game.setDialogAgent(agent);
        game.start();

    }
}
