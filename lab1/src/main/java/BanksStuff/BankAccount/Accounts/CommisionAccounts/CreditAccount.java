package BanksStuff.BankAccount.Accounts.CommisionAccounts;

import BanksStuff.BankAccount.Accounts.IBankAccount;
import BanksStuff.BankAccount.OperationResult.OperationResultType;
import BanksStuff.BankAccount.OperationType.Adding;
import BanksStuff.BankAccount.OperationType.ITransaction;
import BanksStuff.BankAccount.OperationType.Substraction;
import BanksStuff.BankAccount.OperationType.Transferring;

import java.util.Stack;
import java.util.UUID;

/**
 * Realisation of class,that can take commission if balance is negative. Also implements IBankAccount
 */
public class CreditAccount implements ICommissionAccount {
    private final UUID identifier;
    private double moneyAmount;
    private final double creditLimit;
    private final Stack<ITransaction> transaction = new Stack<>();

    public CreditAccount(double creditLimit) {
        this.creditLimit = creditLimit;
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

        if (creditLimit + moneyAmount < amount) {
            return new OperationResultType().new Unsuccess("Credit limit exceeded");
        }

        moneyAmount -= amount;
        transaction.push(new Substraction(this, amount));
        return new OperationResultType().new Success();
    }

    @Override
    public OperationResultType addMoney(double amount) {
        moneyAmount += amount;
        transaction.push(new Adding(this, amount));
        return new OperationResultType().new Success();
    }

    @Override
    public OperationResultType transferMoney(IBankAccount to, double amount) {
        if (creditLimit + moneyAmount < amount) {
            return new OperationResultType().new Unsuccess("Credit limit exceeded");
        }
        moneyAmount -= amount;
        to.addMoney(amount);
        transaction.push(new Transferring(this, to, amount));
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

    @Override
    public void withdrawTheCommission(double commission) {
        if (moneyAmount < 0) {
            moneyAmount -= commission;
        }
    }
}
