import dialogAgent.ConsoleDialogAgent;
import dialogAgent.DialogAgent;
import game.GameEngine;
import player.Player;

public class DillaApp {

    public static void main(String[] args) {

        Player player = new Player("Player");
        GameEngine game = new GameEngine(player);
        game.prepareGame();
        ConsoleDialogAgent agent = new ConsoleDialogAgent();
        game.setDialogAgent(agent);
        game.start();

    }
}
