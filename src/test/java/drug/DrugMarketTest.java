package drug;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DrugMarketTest {

    @Test
    public void shouldChangePrices(){

        //Given
        DrugMarket shop = DrugMarket.getDrugMarket();
        Map<DrugType, BigDecimal> priceList = shop.getPriceList();

        BigDecimal valueOfFirstElementBeforeChange = priceList.get(DrugType.AMPHETAMINE);
        BigDecimal valueOfSecondElementBeforeChange = priceList.get(DrugType.COCAINE);
        BigDecimal valueOfThirdElementBeforeChange = priceList.get(DrugType.ESCTASY);
        BigDecimal valueOfFourthElementBeforeChange = priceList.get(DrugType.HEROIN);
        BigDecimal valueOfFifthElementBeforeChange = priceList.get(DrugType.MARIJUANA);


        Double rate = 1.2;

        //When
        shop.changePrices(rate);
        priceList = shop.getPriceList();


        BigDecimal valueOfFirstElementAfterChange = priceList.get(DrugType.AMPHETAMINE);
        BigDecimal valueOfSecondElementAfterChange = priceList.get(DrugType.COCAINE);
        BigDecimal valueOfThirdElementAfterChange = priceList.get(DrugType.ESCTASY);
        BigDecimal valueOfFourthElementAfterChange = priceList.get(DrugType.HEROIN);
        BigDecimal valueOfFifthElementAfterChange = priceList.get(DrugType.MARIJUANA);

        //Then
        assertThat(valueOfFirstElementBeforeChange.multiply(BigDecimal.valueOf(rate))).isEqualTo(valueOfFirstElementAfterChange);
        assertThat(valueOfSecondElementBeforeChange.multiply(BigDecimal.valueOf(rate))).isEqualTo(valueOfSecondElementAfterChange);
        assertThat(valueOfThirdElementBeforeChange.multiply(BigDecimal.valueOf(rate))).isEqualTo(valueOfThirdElementAfterChange);
        assertThat(valueOfFourthElementBeforeChange.multiply(BigDecimal.valueOf(rate))).isEqualTo(valueOfFourthElementAfterChange);
        assertThat(valueOfFifthElementBeforeChange.multiply(BigDecimal.valueOf(rate))).isEqualTo(valueOfFifthElementAfterChange);


    }
}
