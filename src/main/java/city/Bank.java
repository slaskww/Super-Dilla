package city;

import dialogAgent.VisualConsoleAgent;
import player.Player;
import utils.Listener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Random;

public class Bank implements Listener, Robberable, Serializable {

static {
    userDeposit = BigDecimal.ZERO;
    interestRate = BigDecimal.valueOf(1.05);
    rand = new Random();


}

    private static BigDecimal userDeposit;
    private static BigDecimal interestRate;
    private static Bank instanceOfBank;
    private static Double resistanceToRobbery;
    private static final Double INITIAL_RESISTANCE_TO_ROBBERY = 200.00;
    private static final BigDecimal INITIAL_AMOUNT_IN_BANK_SAFE = BigDecimal.valueOf(2000);
    private static Integer RESISTANCE_BOOSTING_RATIO = 3;
    private static BigDecimal bankSafe; //safe can be robbed
    private static Random rand;
    private static final Double ROE = 0.2; //Return On Equity
    private static String name = "UNITED BANK OF GRASSLAND";


    private Bank() {
        bankSafe = INITIAL_AMOUNT_IN_BANK_SAFE;
        resistanceToRobbery = INITIAL_RESISTANCE_TO_ROBBERY;
        setResistanceToRobbery();

    }


    public static Bank getBankInstance(){
        if (instanceOfBank == null){
            instanceOfBank = new Bank();
        }
        return instanceOfBank;
    }

    public  void deposit(BigDecimal amount){

        if (amount.compareTo(BigDecimal.ZERO) == 0){
            return;
        }

        if (amount.compareTo(BigDecimal.ZERO) > 0)
        {userDeposit = userDeposit.add(amount);
        }else
        {
            VisualConsoleAgent.bankDepositError();
        }
    }

    public  BigDecimal withdraw(BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) < 0)
        {VisualConsoleAgent.bankDepositError();
        return BigDecimal.ZERO;
        }else if (amount.compareTo(userDeposit) > 0)
        {
            VisualConsoleAgent.bankWithdrawalError();
            return BigDecimal.ZERO;
        }
        userDeposit = userDeposit.subtract(amount);
        return amount;
    }

    private  void calculateInterest(){
        if (userDeposit.compareTo(BigDecimal.ZERO) > 0){
            userDeposit = userDeposit.multiply(interestRate);
        }

    }

    public  BigDecimal getUserBalance(){
        return userDeposit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    @Override
    public void update(String message) {
        calculateInterest();
    }


    private static void setResistanceToRobbery(){
        resistanceToRobbery = INITIAL_RESISTANCE_TO_ROBBERY + (resistanceToRobbery) * (rand.nextInt(RESISTANCE_BOOSTING_RATIO) + 1) * rand.nextDouble();

    }

    @Override
    public RobberyStatus rob(Player player) {

        if (player.getOverallSkill() > resistanceToRobbery){
            player.getSmartBackpack().updateWallet(bankSafe);
            setResistanceToRobbery();
            return RobberyStatus.FACILITY_WAS_ROBBED;
        } else {
            player.kill();
            return RobberyStatus.FACILITY_WAS_NOT_ROBBED;
        }

    }

    public String getName() {
        return name;
    }

    public static Double getResistanceToRobbery() {
        return resistanceToRobbery;
    }
}
