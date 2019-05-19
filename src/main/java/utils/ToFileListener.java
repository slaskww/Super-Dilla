package utils;

import city.City;

import java.io.*;
import java.math.BigDecimal;
import java.util.Formatter;

public class ToFileListener implements Listener {

    public ToFileListener() {}

    @Override
    public void informListener(String name, BigDecimal balance, City city) {

       try (FileWriter output = new FileWriter("Player_logs.txt", true)){
           output.write("player " + name + " moved to " + city.getName() + " with capital " + balance);
           output.write(System.lineSeparator());
       }

       catch (IOException e)
       {
           e.printStackTrace();
       }

    }
}
