package drug;

import java.math.BigDecimal;
import java.util.HashMap;

public class DrugShop { //implements Singleton

    private HashMap<Drug, BigDecimal> priceList;
    private static DrugShop shop = null;

    private DrugShop() {
    }

    public static DrugShop getDrugShop() {
        if (shop == null) {
            shop = new DrugShop();
        }
        return shop;
    }


}

