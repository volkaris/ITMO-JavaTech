package BanksStuff.BankAccount.Accounts.CommisionAccounts;

import BanksStuff.BankAccount.Accounts.IBankAccount;
import BanksStuff.BankAccount.OperationResult.OperationResultType;

import java.util.UUID;

/**
 * Interface for classes,that can take commission if balance is negative. Also implements IBankAccount
 */
public interface ICommissionAccount extends IBankAccount {
    void withdrawTheCommission(double commission);

    UUID getIdentifier();

    void cancelTransaction();

    OperationResultType substractMoney(double amount);

    OperationResultType addMoney(double amount);

    OperationResultType transferMoney(IBankAccount to, double amount);

    String getHistory();
}
