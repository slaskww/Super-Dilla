package game;

public class TimeInGame {

    public static Integer INITIAL_DAY = 1;
    public static Integer DAY = 1;

    public static Integer day;
    private static TimeInGame date = null;

    private TimeInGame() {
        this.day = INITIAL_DAY;
    }

    public Integer getDay() {
      return day;
    }

    public void showDay(){
        System.out.println("\n* * *\nDay:" + day + "\n");
    }

    public void setNextDay(){
        day += DAY;
    }

    public static TimeInGame getDateInstance(){
        if (date == null) {
            date = new TimeInGame();
        }
        return date;
    }

}
