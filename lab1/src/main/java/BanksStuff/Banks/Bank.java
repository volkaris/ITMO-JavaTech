package BanksStuff.Banks;

import BanksStuff.ObserverStuff.IPublisher;
import BanksStuff.ObserverStuff.ISubscriber;
import BanksStuff.BankAccount.OperationResult.OperationResultType;
import BanksStuff.Users.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

/**
 * Realisation of bank interface
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Bank implements IPublisher, IBank {
    private final Hashtable<UUID, User> users = new Hashtable<>();
    private final ArrayList<ISubscriber> subs = new ArrayList<>();

    private final UUID identifier;
    @Getter
    private final double firstPercentage;
    @Getter
    private final double secondPercentage;
    @Getter
    private final double thirdPercentage;

    @Getter
    private final double commission;

    @Getter
    private final double creditLimit;

    private final double maxAmountForSuspiciousUser;
    public static bankBuilder bankBuilder = new bankBuilder();

    @Override
    public UUID getBankId() {
        return identifier;
    }

    public Bank addUser(User user) {

        users.put(user.getUserID(), user);
        return this;
    }

    public void withdrawTheCommission() {
        for (var user : users.values()) {
            user.withdrawTheCommission(commission);
        }
    }

    public void chargePercentageForDay() {
        for (var user : users.values()) {

            user.chargePercentageForDay();
        }
    }

    public void chargePercentageForMonth() {

        for (var user : users.values()) {
            user.chargePercentageForMonth();
        }
    }


    public OperationResultType cancelTransaction(UUID UserId, UUID accountID) {

        return users.get(UserId).cancelTransaction(accountID);
    }

    public OperationResultType substractMoney(UUID UserId, UUID accountID, double amount) {
        var user = users.get(UserId);

        return user.substractMoney(accountID, amount, maxAmountForSuspiciousUser);

    }

    public OperationResultType addMoney(UUID UserId, UUID accountID, double amount) {
        return users.get(UserId).addMoney(accountID, amount);
    }

    public OperationResultType transferMoney(UUID senderID, UUID senderAccountID,
                                             UUID receiverID, UUID receiverAccountID,
                                             double amount) {

        var senderAccount = users.get(senderID);

        var receiverAccount = users.get(receiverID).findAccount(receiverAccountID);

        return senderAccount.transferMoney(senderAccountID, receiverAccount, amount);

    }

    public User findUser(UUID userId) {
        if (users.containsKey(userId)) {
            return users.get(userId);
        }
        return null;
    }

    public String getHistory(UUID UserId, UUID accountID) {
        return users.get(UserId).getHistory(accountID);
    }


    @Override
    public void sendNotification() {
        for (var sub : subs) {
            sub.receiveNotification(this);
        }
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subs.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subs.remove(subscriber);
    }

    public static class bankBuilder  {
        private double firstPercentage;
        private double secondPercentage;
        private double thirdPercentage;
        private double commission;
        private double creditLimit;
        private double maxAmountForSuspiciousUser;

        public bankBuilder(Bank bank) {
            this.firstPercentage = bank.firstPercentage;
            this.secondPercentage = bank.secondPercentage;
            this.thirdPercentage = bank.thirdPercentage;
            this.commission = bank.commission;
            this.creditLimit = bank.creditLimit;
            this.maxAmountForSuspiciousUser = bank.maxAmountForSuspiciousUser;
        }

        public bankBuilder() {
        }

        public Bank.bankBuilder setFirstPercentage(double firstPercentage) {
            this.firstPercentage = firstPercentage;
            return this;
        }

        public Bank.bankBuilder setSecondPercentage(double secondPercentage) {
            this.secondPercentage = secondPercentage;
            return this;
        }

        public Bank.bankBuilder setThirdPercentage(double thirdPercentage) {
            this.thirdPercentage = thirdPercentage;
            return this;
        }

        public Bank.bankBuilder setCommission(double commission) {
            this.commission = commission;
            return this;
        }

        public Bank.bankBuilder setCreditLimit(double creditLimit) {
            this.creditLimit = creditLimit;
            return this;
        }

        public Bank.bankBuilder setMaxAmountForSuspiciousUser(double maxAmountForSuspiciousUser) {
            this.maxAmountForSuspiciousUser = maxAmountForSuspiciousUser;
            return this;
        }

        public Bank build() {
            return new Bank(
                    UUID.randomUUID(),
                    firstPercentage,
                    secondPercentage,
                    thirdPercentage,
                    commission,
                    creditLimit,
                    maxAmountForSuspiciousUser);
        }
    }
}
