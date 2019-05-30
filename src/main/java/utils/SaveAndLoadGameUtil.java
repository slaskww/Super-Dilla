package utils;

import dialogAgent.VisualConsoleAgent;
import game.GameEngine;
import game.TimeInGame;
import player.AchievementBoard;
import player.Player;
import world.World;

import java.io.*;

public class SaveAndLoadGameUtil {

    static {
        fileWithSavedState = new File("game_save.bin");
    }

    private static File fileWithSavedState;

    public static void saveGameToFile(World world, Player player, TimeInGame time, AchievementBoard achievementBoard) {

        try (FileOutputStream fOutput = new FileOutputStream(fileWithSavedState); ObjectOutputStream objOutput = new ObjectOutputStream(fOutput)){


            Integer day = time.getDay();

            objOutput.writeObject(world);
            objOutput.writeObject(player);
            objOutput.writeObject(day);
            objOutput.writeObject(achievementBoard);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream in writeToFile");
            e.printStackTrace();
        }

    }


    public static void loadGameFromFile(GameEngine gameEngine) {

        try (FileInputStream fInput = new FileInputStream(fileWithSavedState); ObjectInputStream oInput = new ObjectInputStream(fInput)) {
            World world = (World) oInput.readObject();
            Player player = (Player) oInput.readObject();
            Integer day = (Integer) oInput.readObject();
            AchievementBoard board = (AchievementBoard) oInput.readObject();

            gameEngine.loadGame(world, day, board, player);
        } catch (IOException e) {
            System.out.println("File not found");

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");

        }

    }

    public static boolean isLoadingThePreviousGameStateChosen() {
        return VisualConsoleAgent.isLoadingThePreviousGameStateChosen();
    }


}
