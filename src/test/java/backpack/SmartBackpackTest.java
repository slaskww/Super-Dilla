package backpack;


import drug.Drug;
import drug.DrugFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SmartBackpackTest {


    @Test
    public void shouldAddDrugToBackpack() {


        //Given
        SmartBackpack backpack = new SmartBackpack();
        Drug firstDrug = DrugFactory.amphetamine();
        Integer firstDrugVolume = 4;
        Drug secondDrug = DrugFactory.cocaine();
        Integer secondDrugVolume = 444;

        //When
        DealStatus statusAfterAddingFistDrug = backpack.addDrugToBackpack(firstDrug, firstDrugVolume);
        DealStatus statusAftetrAddingSecondDrug = backpack.addDrugToBackpack(secondDrug, secondDrugVolume);

        //Then
        assertThat(backpack.getGoods().get(firstDrug.getName())).isEqualTo(firstDrugVolume);
        assertThat(statusAfterAddingFistDrug).isEqualTo(DealStatus.MAKE_A_DEAL);
        assertThat(backpack.getGoods().get(secondDrug.getName())).isEqualTo(0);
        assertThat(statusAftetrAddingSecondDrug).isEqualTo(DealStatus.NOT_ENOUGH_MONEY_IN_WALLET);

    }

    @Test
    public void shouldRemoveDrugToBackpack() {

        //Given
        SmartBackpack backpack = new SmartBackpack();
        Drug drug = DrugFactory.amphetamine();
        Integer drugVolume = 4;


        //When
        Integer initialVolumeOfDrugInBackpack = backpack.getDrugVolume(drug.getName());
        backpack.addDrugToBackpack(drug, drugVolume);
        Integer volumeOfDrugInBackpackAfterAdding = backpack.getDrugVolume(drug.getName());
        backpack.removeDrugFromBackpack(drug, drugVolume);
        Integer volumeOfDrugInBackpackAfterRemoving = backpack.getDrugVolume(drug.getName());

        //Then
        assertThat(initialVolumeOfDrugInBackpack).isEqualTo(0);
        assertThat(volumeOfDrugInBackpackAfterAdding).isEqualTo(drugVolume);
        assertThat(volumeOfDrugInBackpackAfterRemoving).isEqualTo(0);


    }

    @Test
    public void shouldNotRemoveDrugToBackpackIfEmpty() {
        //Given
        SmartBackpack backpack = new SmartBackpack();
        Drug drug = DrugFactory.amphetamine();
        Integer drugVolume = 44;

        //When
        DealStatus statusAfterAttemptToRemove = backpack.removeDrugFromBackpack(drug, drugVolume);

        //Then
        assertThat(statusAfterAttemptToRemove
        ).isEqualTo(DealStatus.NOT_ENOUGH_GOODS_IN_BACKPACK);


    }

    @Test
    public void shouldUpdateWallet() {
        //Given
        SmartBackpack backpack = new SmartBackpack();
        BigDecimal initialWalleBalance = backpack.getWalletBalance();
        BigDecimal amountToAddToWallet = BigDecimal.valueOf(100);
        BigDecimal amountToGetFromWallet = BigDecimal.valueOf(-100);

        //When
        backpack.updateWallet(amountToAddToWallet);
        BigDecimal walletBalanceAfterAddingAmount = backpack.getWalletBalance();

        backpack.updateWallet(amountToGetFromWallet);
        BigDecimal walletBalanceAfterGettingAmount = backpack.getWalletBalance();

        //Then
        assertThat(walletBalanceAfterAddingAmount).isEqualTo(initialWalleBalance.add(amountToAddToWallet));
        assertThat(walletBalanceAfterGettingAmount).isEqualTo(walletBalanceAfterAddingAmount.subtract(BigDecimal.valueOf(100)));

    }


    @Test
    public void shouldGetDrugVolume() {
        ///Given
        SmartBackpack backpack = new SmartBackpack();

        Drug drug = DrugFactory.amphetamine();
        Integer initialVolumeOfDrugs = backpack.getDrugVolume(drug.getName());
        Integer volumeOfDrugsToAdd = 1;
        backpack.addDrugToBackpack(drug, volumeOfDrugsToAdd);

        //When
        Integer volumeOfDrugAfterAdding = backpack.getDrugVolume(drug.getName());

        //Then
        assertThat(volumeOfDrugAfterAdding).isEqualTo(initialVolumeOfDrugs + volumeOfDrugsToAdd);

    }

    @Test
    public void shouldPayForFacilities() {
        ///Given
        SmartBackpack backpack = new SmartBackpack();

        BigDecimal walletBalanceBeforePaying = backpack.getWalletBalance();
        BigDecimal priceForFacilities = BigDecimal.valueOf(100);

        //When
        backpack.payForFacilities(priceForFacilities);
        BigDecimal walletBalanceAfterPaying = backpack.getWalletBalance();

        //Then
        assertThat(walletBalanceAfterPaying).isEqualTo(walletBalanceBeforePaying.subtract(priceForFacilities));


    }

}
