package drug;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class DrugMarket { //implements Singleton

    private final Logger log = LogManager.getLogger(DrugMarket.class.getName());
    private Map<DrugType, BigDecimal> priceList;
    private static DrugMarket shop = null;
    Random rand = new Random();

    private DrugMarket() {
        this.priceList = new TreeMap<>();
        priceList.put(DrugType.MARIJUANA, BigDecimal.valueOf(40));
        priceList.put(DrugType.HEROIN, BigDecimal.valueOf(50));
        priceList.put(DrugType.ESCTASY, BigDecimal.valueOf(50));
        priceList.put(DrugType.COCAINE, BigDecimal.valueOf(200));
        priceList.put(DrugType.AMPHETAMINE, BigDecimal.valueOf(30));

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
        log.log(Level.INFO, priceListAfterChange.toString());
        this.priceList = priceListAfterChange;
    }

    public void changePrices(Double rate) {


        Map<DrugType, BigDecimal> priceListAfterChange;

        priceListAfterChange = priceList.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> BigDecimal.valueOf(rate).multiply(e.getValue())));
        log.log(Level.INFO, priceListAfterChange.toString());
        this.priceList = priceListAfterChange;
    }

    private Iterator<Map.Entry<DrugType, BigDecimal>> getPriceListIterator() {
        return priceList.entrySet().iterator();
    }

    public double getRate() {
        return rand.nextDouble() + 0.6; //rate has range between 0.6 - 1.6
    }


    public List<DrugType> getDrugTypeListInOrder() {

        List<DrugType> list = new ArrayList<>();

        for (Map.Entry<DrugType, BigDecimal> entry :priceList.entrySet()){
            list.add(entry.getKey());
        }

        return list;
    }

    private String priceListToString(Map<DrugType, BigDecimal> priceList){
        StringBuilder list = new StringBuilder();

        for (Map.Entry<DrugType, BigDecimal> entry: priceList.entrySet()){
            list.append("type: " + entry.getKey() + " price: " + entry.getValue().setScale(0, RoundingMode.HALF_UP) + " ");
        }
        return list.toString();

    }
}

