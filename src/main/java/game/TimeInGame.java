package game;

public class TimeInGame {

    public static Integer INITIAL_DAY = 0;
    public static Integer DAY = 1;

    public static Integer day;
    private static TimeInGame date = null;

    private TimeInGame() {
        this.day = INITIAL_DAY;
    }

    public static Integer getDay() {
        if (date == null) {
            date = new TimeInGame();
        }
        Integer currentDay = day;
        day = currentDay + DAY; //set date for the next day
        return currentDay;
    }

}
