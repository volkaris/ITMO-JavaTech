package BanksStuff.BankAccount.OperationType;

import BanksStuff.BankAccount.Accounts.IBankAccount;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class Substraction implements ITransaction {

    IBankAccount bankAccount;

    double howMuch;

    @Override
    public String toString() {
        return "Substraction";
    }

    @Override
    public void cancelOperation() {
        bankAccount.addMoney(howMuch);
    }
}