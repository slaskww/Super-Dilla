package drug;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class DrugMarket { //implements Singleton

    private Map<DrugType, BigDecimal> priceList;
    private static DrugMarket shop = null;
    Random rand = new Random();

    private DrugMarket() {
        this.priceList = new HashMap<>();
        priceList.put(DrugType.MARIJUANA, BigDecimal.valueOf(100));
        priceList.put(DrugType.HEROIN, BigDecimal.valueOf(100));
        priceList.put(DrugType.ESCTASY, BigDecimal.valueOf(100));
        priceList.put(DrugType.COCAINE, BigDecimal.valueOf(100));
        priceList.put(DrugType.AMPHETAMINE, BigDecimal.valueOf(100));

    }

    public static DrugMarket getDrugMarket() {
        if (shop == null) {
            shop = new DrugMarket();
        }
        return shop;
    }


    public Map<DrugType, BigDecimal> getPriceList() {
        return this.priceList;
    }

    public void changePrices() {


        Map<DrugType, BigDecimal> priceListAfterChange;

        priceListAfterChange = priceList.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> BigDecimal.valueOf(getRate()).multiply(e.getValue())));

        this.priceList = priceListAfterChange;
    }

    public void changePrices(Double rate) {


        Map<DrugType, BigDecimal> priceListAfterChange;

        priceListAfterChange = priceList.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> BigDecimal.valueOf(rate).multiply(e.getValue())));

        this.priceList = priceListAfterChange;
    }

    private Iterator<Map.Entry<DrugType, BigDecimal>> getPriceListIterator() {
        return priceList.entrySet().iterator();
    }

    public double getRate(){
        return  rand.nextDouble() + 0.5; //rate has range between 0.5 - 1.5
    }

}
