package utils;

import city.City;

import java.io.*;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class ToFileListener implements Listener {

   private File file = new File("Player_logs.txt");
   private PrintWriter printWriter;

    public ToFileListener() {

        try
        {
            printWriter = new PrintWriter(file);
            printWriter.println("hello");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void informListener(String name, BigDecimal balance, City city) {
        printWriter.println("player " + name + " moved to " + city + " with capital " + balance);
        printWriter.close();
    }
}
