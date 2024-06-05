package BanksStuff.BankAccount.Accounts.StandartAccount;

import BanksStuff.BankAccount.Accounts.IBankAccount;
import BanksStuff.BankAccount.OperationResult.OperationResultType;
import BanksStuff.BankAccount.OperationType.Adding;
import BanksStuff.BankAccount.OperationType.ITransaction;
import BanksStuff.BankAccount.OperationType.Substraction;
import BanksStuff.BankAccount.OperationType.Transferring;

import java.util.Stack;
import java.util.UUID;


/**
 * Standard account.
 */
public class DebitAccount implements IBankAccount {
    private final UUID identifier;
    private double moneyAmount;
    private final Stack<ITransaction> transaction = new Stack<>();
    public DebitAccount() {
        moneyAmount = 0;
        identifier = UUID.randomUUID();
    }
    @Override
    public UUID getIdentifier() {
        return identifier;
    }
    @Override
    public void cancelTransaction() {
        var cancelledOperation = transaction.pop();

        cancelledOperation.cancelOperation();
    }
    @Override
    public OperationResultType substractMoney(double amount) {
        if (moneyAmount - amount < 0) {
            return new OperationResultType().new Unsuccess("Not enough money");
        }
        moneyAmount -= amount;
        transaction.push(new Substraction(this, amount));

        return new OperationResultType().new Success();
    }
    @Override
    public OperationResultType addMoney(double amount) {
        moneyAmount += amount;
        transaction.push(new Adding(this,amount));
        return new OperationResultType().new Success();
    }
    @Override
    public OperationResultType transferMoney(IBankAccount to, double amount) {
        if (moneyAmount - amount < 0) {
            return new OperationResultType().new Unsuccess("Not enough money");
        }
        moneyAmount -= amount;

        transaction.push(new Transferring(this,to,amount));

        to.addMoney(amount);

        return new OperationResultType().new Success();
    }
    @Override
    public String getHistory() {
        StringBuilder builder = new StringBuilder();

        for (var i : transaction) {
            builder.append(i.toString());
        }

        return builder.toString();
    }
}
