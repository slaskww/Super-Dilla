package drug;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DrugMarketTest {

    @Test
    public void shouldChangePrices(){

        DrugMarket shop = DrugMarket.getDrugShop();
        Map<DrugType, BigDecimal> priceList = shop.getPriceList();

        BigDecimal valueOfFirstElementBeforeChange = priceList.get(DrugType.AMPHETAMINE);
        BigDecimal valueOfSecondElementBeforeChange = priceList.get(DrugType.COCAINE);
        BigDecimal valueOfThirdElementBeforeChange = priceList.get(DrugType.ESCTASY);
        BigDecimal valueOfFourthElementBeforeChange = priceList.get(DrugType.HEROIN);
        BigDecimal valueOfFifthElementBeforeChange = priceList.get(DrugType.MARIJUANA);

        Double rate = 1.2;
        shop.changePrices(rate);
        priceList = shop.getPriceList();

        BigDecimal valueOfFirstElementAfterChange = priceList.get(DrugType.AMPHETAMINE);
        BigDecimal valueOfSecondElementAfterChange = priceList.get(DrugType.COCAINE);
        BigDecimal valueOfThirdElementAfterChange = priceList.get(DrugType.ESCTASY);
        BigDecimal valueOfFourthElementAfterChange = priceList.get(DrugType.HEROIN);
        BigDecimal valueOfFifthElementAfterChange = priceList.get(DrugType.MARIJUANA);

        assertThat(valueOfFirstElementAfterChange).isEqualTo(BigDecimal.valueOf(120.0));
        assertThat(valueOfSecondElementAfterChange).isEqualTo(BigDecimal.valueOf(120.0));
        assertThat(valueOfThirdElementAfterChange).isEqualTo(BigDecimal.valueOf(120.0));
        assertThat(valueOfFourthElementAfterChange).isEqualTo(BigDecimal.valueOf(120.0));
        assertThat(valueOfFifthElementAfterChange).isEqualTo(BigDecimal.valueOf(120.0));


    }
}
