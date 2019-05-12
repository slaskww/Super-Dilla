package drug;

import java.math.BigDecimal;

public class DrugFactory {

    public static Drug amphetamine(){
        return new Drug(DrugType.AMPHETAMINE, BigDecimal.valueOf(50));
    }

    public static Drug ecstasy(){
        return new Drug(DrugType.ESCTASY, BigDecimal.valueOf(50));
    }

    public static Drug heroin(){
        return new Drug(DrugType.HEROIN, BigDecimal.valueOf(50));
    }

    public static Drug cocaine(){
        return new Drug(DrugType.COCAINE, BigDecimal.valueOf(50));
    }

    public static Drug marijuana(){
        return new Drug(DrugType.MARIJUANA, BigDecimal.valueOf(50));
    }
}
