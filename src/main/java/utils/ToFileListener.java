package utils;

import city.City;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;



public class ToFileListener implements Listener {

    static final Logger log = LogManager.getLogger(ToFileListener.class.getName());

    public ToFileListener() {
    }

    @Override
    public void informListener(String name, BigDecimal balance, City city) {

        try (FileWriter output = new FileWriter("Player_logs.txt", true)) {
            String message = "player " + name + " moved to " + city.getName() + " with capital " + balance;
            output.write(message);
            log.log(Level.INFO, message);

            output.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
