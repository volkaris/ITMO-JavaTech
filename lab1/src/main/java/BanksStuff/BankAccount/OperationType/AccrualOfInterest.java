package BanksStuff.BankAccount.OperationType;

import BanksStuff.BankAccount.Accounts.IBankAccount;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AccrualOfInterest implements ITransaction {

    IBankAccount bankAccount;

    double howMuch;
    @Override
    public String toString() {
        return "Accrual of interest to the account";
    }
    @Override
    public void cancelOperation() {
        bankAccount.substractMoney(howMuch);
    }
}