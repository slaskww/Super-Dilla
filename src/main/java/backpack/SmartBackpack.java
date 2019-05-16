package backpack;

import drug.Drug;
import drug.DrugType;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class SmartBackpack {

    public final BigDecimal INITIAL_WALLET_BALANCE = BigDecimal.valueOf(1000);
    public final Integer INITIAL_DRUG_VOLUME = 0;
    private BigDecimal wallet;
    private Map<DrugType, Integer> goods;

    public SmartBackpack() {
        this.wallet = INITIAL_WALLET_BALANCE;
        goods = new TreeMap<>();
        goods.put(DrugType.MARIJUANA, INITIAL_DRUG_VOLUME);
        goods.put(DrugType.HEROIN, INITIAL_DRUG_VOLUME);
        goods.put(DrugType.ESCTASY, INITIAL_DRUG_VOLUME);
        goods.put(DrugType.COCAINE, INITIAL_DRUG_VOLUME);
        goods.put(DrugType.AMPHETAMINE, INITIAL_DRUG_VOLUME);
    }

    public DealStatus addDrugToBackpack(Drug drug, Integer volume){

        BigDecimal costOfDrug = drug.getPrice().multiply(BigDecimal.valueOf(volume));

        if (wallet.compareTo(costOfDrug) < 0){
            return DealStatus.NOT_ENOUGH_MONEY_IN_WALLET;
        } else {
            Integer oldVolume = goods.get(drug.getName());
            Integer newVolume = oldVolume + volume;
            goods.replace(drug.getName(),newVolume);
            updateWallet(BigDecimal.ZERO.subtract(costOfDrug));
            return DealStatus.MAKE_A_DEAL;
        }

    }

    public DealStatus removeDrugFromBackpack(Drug drug, Integer volume){

        if (this.goods.get(drug.getName()) < volume){
            return DealStatus.NOT_ENOUGH_GOODS_IN_BACKPACK;
        } else {
            Integer oldVolume = goods.get(drug.getName());
            Integer newVolume = oldVolume - volume;
            goods.replace(drug.getName(),newVolume);
            BigDecimal profitFromDrug = drug.getPrice().multiply(BigDecimal.valueOf(volume));
            updateWallet(profitFromDrug);
            return DealStatus.MAKE_A_DEAL;
        }

    }

    private void updateWallet(BigDecimal amount){
        this.wallet = wallet.add(amount);
    }

    public BigDecimal getWalletBalance() {
        return wallet;
    }

    public Integer getDrugVolume(DrugType type){
        return this.goods.get(type);
    }

    public PaymentForFacilitiesStatus payForFacilities(BigDecimal price){

        if (this.wallet.compareTo(price) > 0){
            this.wallet = wallet.subtract(price);
            return PaymentForFacilitiesStatus.TRANSACTION_ACCEPTED;
        } else{
            return PaymentForFacilitiesStatus.TRANSACTION_REJECTED;
        }
    }

    public Map<DrugType, Integer> getGoods() {
        return goods;
    }
}
