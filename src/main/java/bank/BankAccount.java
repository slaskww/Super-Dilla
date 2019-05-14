package bank;

import java.math.BigDecimal;

public class BankAccount {

    private BigDecimal balance;
    // private List<AccountHistoryObject> accountHistory;


    public BankAccount(BigDecimal balance) {
        this.balance = balance;
    }

    public void deposit(BigDecimal amount){
        this.balance = balance.add(amount);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public WithdrawalStatus withdraw(BigDecimal amount){


        if (this.balance.compareTo(amount) > 0){
            this.balance = balance.subtract(amount);
            return WithdrawalStatus.TRANSACTION_ACCEPTED;
        } else{
            return WithdrawalStatus.TRANSACTION_REJECTED;
        }
    }
}
