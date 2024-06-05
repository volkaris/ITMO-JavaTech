package BanksStuff.Banks;

import BanksStuff.BankAccount.OperationResult.OperationResultType;
import BanksStuff.Users.User;

import java.util.UUID;

/**
 * Bank interface. Provide basic operations
 */
public interface IBank {


    UUID getBankId();
    Bank addUser(User user);

    void withdrawTheCommission();

    void chargePercentageForDay();

    void chargePercentageForMonth();

    OperationResultType cancelTransaction(UUID UserId, UUID accountID);

    public User findUser(UUID userId);
    OperationResultType substractMoney(UUID UserId,UUID accountID, double amount);

    OperationResultType addMoney(UUID UserId, UUID accountID, double amount);

    OperationResultType transferMoney(UUID senderID, UUID senderAccountID,
                                      UUID receiverID, UUID receiverAccountID,
                                      double amount);

    String getHistory(UUID UserId, UUID accountID);

}
