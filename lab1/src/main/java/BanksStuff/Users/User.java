package BanksStuff.Users;

import BanksStuff.BankAccount.Accounts.CommisionAccounts.ICommissionAccount;
import BanksStuff.BankAccount.Accounts.IBankAccount;
import BanksStuff.BankAccount.Accounts.IncomeAccounts.IIncomeAccount;
import BanksStuff.BankAccount.OperationResult.OperationResultType;
import BanksStuff.Banks.Bank;
import BanksStuff.Users.Builders.IUserBuilder;
import BanksStuff.Users.Builders.INameBuilder;
import BanksStuff.Users.Builders.ISurnameBuilder;
import BanksStuff.ObserverStuff.ISubscriber;
import lombok.Getter;

import java.util.Hashtable;
import java.util.Optional;
import java.util.UUID;

/**
 * User model. Provides methods for adding accounts and working with them
 */
public class User implements ISubscriber {
    @Getter
    private final UUID userID = UUID.randomUUID();

    private final String name;
    private final String surname;
    private final Optional<String> address;
    private final Optional<String> passportData;

    private final IUserState clientState;


    public static INameBuilder userBuilder = new UserBuilder();

    @Getter
    private Hashtable<UUID, ICommissionAccount> creditAccounts = new Hashtable<>();
    @Getter

    private Hashtable<UUID, IIncomeAccount> incomeAccounts = new Hashtable<>();

    @Getter
    private Hashtable<UUID, IBankAccount> standartAccounts = new Hashtable<>();

    private User(String name,
                 String surname,
                 Optional<String> address,
                 Optional<String> passportData,
                 IUserState clientState) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passportData = passportData;
        this.clientState = clientState;

    }


    public User addStandartAcoount(IBankAccount account) {
        standartAccounts.put(account.getIdentifier(), account);
        return this;
    }

    public User addIncomeAccount(IIncomeAccount account) {
        incomeAccounts.put(account.getIdentifier(), account);
        return this;
    }

    public User addCreditAccount(ICommissionAccount account) {
        creditAccounts.put(account.getIdentifier(), account);
        return this;
    }

    public OperationResultType cancelTransaction(UUID accountID) {
        var account = findAccount(accountID);
        if (account != null) {
            account.cancelTransaction();
            return new OperationResultType().new Success();
        }

        return new OperationResultType().new Unsuccess("Account wasn't found");
    }

    public OperationResultType substractMoney(UUID accountID,
                                              double amount,
                                              double maxAmountForSuspiciousUser) {


        if ((clientState instanceof IUserState.Unverified && amount <= maxAmountForSuspiciousUser) || clientState instanceof IUserState.Verified) {
            var account = findAccount(accountID);

            if (account == null) {
                return new OperationResultType().new Unsuccess("Account wasn't found");
            }

            return account.substractMoney(amount);
        }
        return new OperationResultType().new Unsuccess("You exceed limit for unverified user");
    }


    public OperationResultType addMoney(UUID accountID, double amount) {
        var account = findAccount(accountID);
        if (account != null) {
            return account.addMoney(amount);
        }
        return new OperationResultType().new Unsuccess("Account wasn't found");
    }


    public OperationResultType transferMoney(UUID accountID, IBankAccount bankAccount, double amount) {

        var receiverAccount = findAccount(accountID);

        return receiverAccount.transferMoney(bankAccount, amount);

    }


    public String getHistory(UUID accountID) {

        var account = findAccount(accountID);
        if (account != null) {
            return account.getHistory();
        }
        return " ";
    }

    public void withdrawTheCommission(double commission) {
        for (var commisionAccount : creditAccounts.values()) {
            commisionAccount.withdrawTheCommission(commission);
        }
    }

    public void chargePercentageForDay() {
        for (var incomeAccount : incomeAccounts.values()) {
            incomeAccount.accrueInterestForDay();
        }
    }

    public void chargePercentageForMonth() {
        for (var incomeAccount : incomeAccounts.values()) {
            incomeAccount.accrueInterestForMonth();
        }
    }

    public IBankAccount findAccount(UUID ID) {
        if (creditAccounts.containsKey(ID)) {
            return creditAccounts.get(ID);
        } else if (incomeAccounts.containsKey(ID)) {
            return incomeAccounts.get(ID);
        } else if (standartAccounts.containsKey(ID)) {
            return standartAccounts.get(ID);
        }
        return null;
    }

    @Override
    public void receiveNotification(Bank bank) {

        System.out.println("Understandable, new conditions are :" + "Percentage if balance under 50_000 " + bank.getFirstPercentage() + "\n" +
                "Percentage if balance between 50_000 and 100_000 " + bank.getSecondPercentage() + "\n" +
                "Percentage if balance greater 100_000 " + bank.getFirstPercentage() + "\n");
    }

    public static class UserBuilder implements IUserBuilder, INameBuilder, ISurnameBuilder {
        private String name;
        private String surname;
        private Optional<String> address;
        private Optional<String> passportData;

        public UserBuilder(User user) {

            name = user.name;
            surname = user.surname;
            address = user.address;
            passportData = user.passportData;
        }

        public UserBuilder() {
        }

        public ISurnameBuilder withName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public IUserBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        @Override
        public IUserBuilder withAddress(String address) {
            this.address = address.describeConstable();
            return this;
        }

        @Override
        public IUserBuilder withPassportData(String passportData) {
            this.passportData = passportData.describeConstable();
            return this;
        }

        @Override
        public User build() {

            IUserState state = (address.isEmpty() || passportData.isEmpty()) ? new IUserState.Unverified() : new IUserState.Verified();

            return new User(name, surname, address, passportData, state);
        }

    }

}


