package game;

import player.Player;
import utils.Announcer;

public class TimeInGame {

    public static Integer INITIAL_DAY = 1;
    public static Integer DAY = 1;

    public static Integer day;
    private static TimeInGame date = null;
    private static Announcer announcer  = new Announcer();

    private TimeInGame() {
        this.day = INITIAL_DAY;
    }

    public Integer getDay() {
      return day;
    }

    public void showDay(){
        System.out.println("\n* * *\nDay:" + day + "\n");
    }

    public void showFullHeader(Player player){
        System.out.format("\nDay: %-2s | Twoje imie: %-8s | Obrona: %-3d | Atak: %-3d | Poziomm mentalny: %-3d |\n\n", day, player.getName() ,player.getDefensiveLevel(), player.getOffensiveLevel(), player.getMentalLevel());
    }

    public void setNextDay(){
        day += DAY;
        announcer.informListeners(day.toString());
    }

    public static TimeInGame getDateInstance(){
        if (date == null) {
            date = new TimeInGame();
        }
        return date;
    }

}
