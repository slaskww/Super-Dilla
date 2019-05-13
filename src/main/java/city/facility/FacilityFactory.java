package city.facility;

import java.math.BigDecimal;

public class FacilityFactory {

    public static Facility hospital(){
        return new Facility("Private hospital", BigDecimal.valueOf(100), 50, 25, 65);
    }

    public static Facility pub(){
        return new Facility("Local pub", BigDecimal.valueOf(20), 10, 35, 45);
    }

    public static Facility gym(){
        return new Facility("Gym Big Muscle", BigDecimal.valueOf(70), 70, 70, 50);
    }

    public static Facility church(){
        return new Facility("Gym Big Muscle", BigDecimal.valueOf(10), 0, 0, 85);
    }

    public static Facility restaurant(){
        return new Facility("Restaurant Big Dumpling", BigDecimal.valueOf(70), 30, 30, 35);
    }

}
