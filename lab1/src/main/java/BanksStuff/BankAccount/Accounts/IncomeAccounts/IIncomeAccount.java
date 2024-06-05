package BanksStuff.BankAccount.Accounts.IncomeAccounts;

import BanksStuff.BankAccount.Accounts.IBankAccount;
import BanksStuff.BankAccount.OperationResult.OperationResultType;

import java.util.UUID;

/**
 * Interface for classes,that can accrue interest. Also implements IBankAccount
 */
public interface IIncomeAccount extends IBankAccount {
    void accrueInterestForDay();
    void accrueInterestForMonth();

    UUID getIdentifier();

    void cancelTransaction();

    OperationResultType substractMoney(double amount);

    OperationResultType addMoney(double amount);

    OperationResultType transferMoney(IBankAccount to, double amount);

    String getHistory();
}
