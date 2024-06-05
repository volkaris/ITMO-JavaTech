package BanksStuff.BankAccount.Accounts.IncomeAccounts;

import BanksStuff.BankAccount.Accounts.IBankAccount;
import BanksStuff.BankAccount.OperationResult.OperationResultType;
import BanksStuff.BankAccount.OperationType.*;
import lombok.Setter;

import java.util.*;

/**
 * Realisation of class,that can accrue interest.
 */
public class DepositAccount implements IIncomeAccount {
    private final double firstPercentage;
    private final double secondPercentage;
    private final double thirdPercentage;
    private final Stack<ITransaction> transaction = new Stack<>();
    private double moneyAmount;
    @Setter
    private int currentDate;
    private final int endingDate;
    private final UUID identifier;
    private final ArrayList<Double> balanceInterest;

    public DepositAccount(double firstPercentage,
                          double secondPercentage,
                          double thirdPercentage,
                          int endingDate) {

        this.firstPercentage = firstPercentage;
        this.secondPercentage = secondPercentage;
        this.thirdPercentage = thirdPercentage;
        this.endingDate = endingDate;
        balanceInterest = new ArrayList<>();
        identifier = UUID.randomUUID();
        moneyAmount = 0;
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

        if (currentDate <= endingDate) {
            return new OperationResultType().new Unsuccess("Too early");
        }

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
        transaction.push(new Adding(this, amount));
        return new OperationResultType().new Success();
    }

    @Override
    public OperationResultType transferMoney(IBankAccount receiver, double amount) {
        if (moneyAmount - amount < 0) {
            return new OperationResultType().new Unsuccess("Not enough money");
        }
        moneyAmount -= amount;

        transaction.push(new Transferring(this, receiver, amount));

        receiver.addMoney(amount);

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
    public void accrueInterestForDay() {
        if (moneyAmount < 50_000) {
            balanceInterest.add(moneyAmount * (firstPercentage / 365 / 100));
        } else if (moneyAmount >= 50_000 && moneyAmount <= 100_000) {
            balanceInterest.add(moneyAmount * (secondPercentage / 365 / 100));
        } else if (moneyAmount > 100_00) {
            balanceInterest.add(moneyAmount * (thirdPercentage / 365 / 100));
        }
    }

    @Override
    public void accrueInterestForMonth() {
        for (var i : balanceInterest) {
            moneyAmount += i;
        }
        transaction.push(new AccrualOfInterest(this, moneyAmount));
        balanceInterest.clear();
    }
}
