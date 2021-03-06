package drug;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Listener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class DrugMarket implements Listener, Serializable { //implements Singleton

    private final Logger log = LogManager.getLogger(DrugMarket.class.getName());
    private static Map<DrugType, BigDecimal> priceList;
    private static DrugMarket shop = null;
    private static Random rand = new Random();

    private DrugMarket() {
        priceList = new TreeMap<>();
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

    public static Map<DrugType, BigDecimal> getPriceList() {
        return priceList;
    }

    public static Map<DrugType, BigDecimal> getPriceListInOrder() {

       return priceList.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public static void setPriceList(Map<DrugType, BigDecimal> priceList) {
        priceList = priceList;
    }

    public static void changePrices() {


        Map<DrugType, BigDecimal> priceListAfterChange;

        priceListAfterChange = priceList.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> BigDecimal.valueOf(getRate()).multiply(e.getValue())));
     //   log.log(Level.INFO, priceListAfterChange.toString());
        priceList = priceListAfterChange;
    }

    public static void changePrices(Double rate) {


        Map<DrugType, BigDecimal> priceListAfterChange;

        priceListAfterChange = priceList.entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> BigDecimal.valueOf(rate).multiply(e.getValue())));
      //  log.log(Level.INFO, priceListAfterChange.toString());
        priceList = priceListAfterChange;
    }

    private Iterator<Map.Entry<DrugType, BigDecimal>> getPriceListIterator() {
        return priceList.entrySet().iterator();
    }

    public static double getRate() {
        return rand.nextDouble() + 0.6; //rate has range between 0.6 - 1.6
    }


    public List<DrugType> getDrugTypeListInOrder() {


       return getPriceListInOrder().entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    private static String priceListToString(Map<DrugType, BigDecimal> priceList){
        StringBuilder list = new StringBuilder();

        for (Map.Entry<DrugType, BigDecimal> entry: priceList.entrySet()){
            list.append("type=" + entry.getKey() + " price=" + entry.getValue().setScale(2, RoundingMode.HALF_UP) + ", ");
        }
        return list.toString();

    }

    @Override
    public void update(String message) {

        String messageToLog = "Prices on the market: " + "(day " + message +  ") "+  priceListToString(getPriceListInOrder());

        log.log(Level.INFO, messageToLog);
    }
}

