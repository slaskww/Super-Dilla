package game;

import player.Player;
import utils.Announcer;

import java.io.Serializable;

public class TimeInGame implements Serializable {

    public static Integer INITIAL_DAY = 1;
    public  Integer DAY = 1;

    public  Integer day;
    private  TimeInGame date = null;
    private  Announcer announcer;

    public TimeInGame(Announcer announc) {
        day = INITIAL_DAY;
        announcer = announc;
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

    public void setDay(Integer day){
        this.day = day;
    }


}
