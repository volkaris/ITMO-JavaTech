package BanksStuff.BankAccount.OperationType;

import BanksStuff.BankAccount.Accounts.IBankAccount;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class Transferring implements ITransaction {

    IBankAccount from;

    IBankAccount to;

    double howMuch;

    @Override
    public String toString() {
        return "Transferring {" +
                "from=" + from +
                ", to=" + to +
                '}';
    }

    @Override
    public void cancelOperation() {
        from.addMoney(howMuch);
        to.substractMoney(howMuch);
    }
}