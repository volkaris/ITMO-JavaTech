package BanksStuff.BankAccount.Accounts;

import BanksStuff.BankAccount.OperationResult.OperationResultType;

import java.util.UUID;

/**
 * Main interface for all accounts.
 */
public interface IBankAccount {
    UUID getIdentifier();

    void cancelTransaction();

    OperationResultType substractMoney(double amount);

    OperationResultType addMoney(double amount);

    OperationResultType transferMoney(IBankAccount to, double amount);

    String getHistory();
}
