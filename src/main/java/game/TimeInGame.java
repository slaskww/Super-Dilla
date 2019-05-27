package game;

import player.Player;
import utils.Announcer;

public class TimeInGame {

    public static Integer INITIAL_DAY = 1;
    public static Integer DAY = 1;

    public static Integer day;
    private static TimeInGame date = null;
    private static Announcer announcer;

    private TimeInGame(Announcer announcer) {
        this.day = INITIAL_DAY;
        this.announcer = announcer;
    }

    public Integer getDay() {
      return day;
    }

    public void showDay(){
        System.out.println("\n* * *\nDay:" + day + "\n");
    }

    public void showFullHeader(Player player){
        System.out.format("\nDAY: %-2s | NICKNAME: %-8s | DEFENSE: %-3d | OFFENSE: %-3d | MENTAL: %-3d | WEAPON: %-3s | BALANCE: %-3.2f\n\n"
                ,day
                ,player.getName()
                ,player.getDefensiveLevel()
                ,player.getOffensiveLevel()
                ,player.getMentalLevel()
                ,player.getWeapon().getName()
                ,player.getBalance());
    }

    public void setNextDay(){
        day += DAY;
        announcer.informListeners(day.toString());
    }

    public static TimeInGame getDateInstance(Announcer announcer){
        if (date == null) {
            date = new TimeInGame(announcer);
        }
        return date;
    }

}
