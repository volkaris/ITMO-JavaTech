package BanksStuff.BankAccount.OperationType;

import BanksStuff.BankAccount.Accounts.IBankAccount;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class Adding implements ITransaction {

    IBankAccount bankAccount;

    double howMuch;

    @Override
    public String toString() {
        return "Adding";
    }

    @Override
    public void cancelOperation() {

        bankAccount.substractMoney(howMuch);

    }
}
