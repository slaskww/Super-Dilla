package city.facility;

import java.math.BigDecimal;

public class FacilityFactory {

    public static Facility hospital() {
        return new Facility("Private hospital", BigDecimal.valueOf(200), 50, 25, 25);
    }

    public static Facility pub() {
        return new Facility("Local pub", BigDecimal.valueOf(50), 10, 35, 45);
    }

    public static Facility gym() {
        return new Facility("Gym Big Muscle", BigDecimal.valueOf(70), 70, 70, 50);
    }

    public static Facility church() {
        return new Facility("Church", BigDecimal.valueOf(30), 0, 0, 65);
    }

    public static Facility restaurant() {
        return new Facility("Restaurant Big Dumpling", BigDecimal.valueOf(110), 30, 30, 35);
    }

}
