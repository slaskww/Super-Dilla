package utils;

import city.City;

import java.io.*;
import java.math.BigDecimal;
import java.util.Formatter;

public class ToFileListener implements Listener {

    public ToFileListener() {}

    @Override
    public void informListener(String name, BigDecimal balance, City city) {

       try (Formatter output = new Formatter("Player_logs.txt")){
           output.format("player %s moved to %s with capital %.2f", name,city.getName(),balance);
       }

       catch (FileNotFoundException e)
       {
           e.printStackTrace();
       }

    }
}
