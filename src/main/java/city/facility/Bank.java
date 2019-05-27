package city.facility;

import dialogAgent.VisualConsoleAgent;
import utils.Listener;
import java.math.BigDecimal;

public class Bank implements Listener {

static {
    userDeposit = BigDecimal.ZERO;
    interestRate = BigDecimal.valueOf(1.05);
}

    private static BigDecimal userDeposit;
    private static BigDecimal interestRate;
    private static Bank instanceOfBank;

    private Bank() {


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
}
