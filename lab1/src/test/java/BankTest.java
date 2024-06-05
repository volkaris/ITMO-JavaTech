import BanksStuff.BankAccount.Accounts.CommisionAccounts.CreditAccount;
import BanksStuff.BankAccount.Accounts.IncomeAccounts.DepositAccount;
import BanksStuff.BankAccount.Accounts.StandartAccount.DebitAccount;
import BanksStuff.BankAccount.OperationResult.OperationResultType;
import BanksStuff.Banks.Bank;
import BanksStuff.Banks.TimeMachine.TARDIS;
import BanksStuff.CentralBank.CentralBank;
import BanksStuff.Users.User;
import org.junit.jupiter.api.Test;

import static BanksStuff.Banks.Bank.bankBuilder;
import static BanksStuff.Users.User.userBuilder;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    private Bank sber = bankBuilder.setFirstPercentage(3).setSecondPercentage(3.5).setThirdPercentage(4)
            .setCommission(20).setCreditLimit(10000).setMaxAmountForSuspiciousUser(1000)
            .build();

    private Bank tinkoff = bankBuilder.setFirstPercentage(3).setSecondPercentage(3.5).setThirdPercentage(4)
            .setCommission(20).setCreditLimit(10000).setMaxAmountForSuspiciousUser(1000)
            .build();

    @Test
    public void bankTests_allOperations() {


        User Ilya = userBuilder.withName("Ilya")
                .withSurname("Oreshin")
                .withAddress("Vyazma")
                .withPassportData("100500")
                .build();

        var IlyaDebitAccount = new DebitAccount();
        var IlyaCreditAccount = new CreditAccount(sber.getCreditLimit());
        var IlyaIncomeAccount = new DepositAccount(sber.getFirstPercentage(),
                sber.getSecondPercentage(),
                sber.getThirdPercentage(), 10);


        Ilya.addStandartAcoount(IlyaDebitAccount)
                .addCreditAccount(IlyaCreditAccount)
                .addIncomeAccount(IlyaIncomeAccount);


        User Kostya = userBuilder.withName("K")
                .withSurname("T")
                .withAddress("Pushkin")
                .withPassportData("88005553535")
                .build();

        var KostyaDebitAccount = new DebitAccount();
        var KostyaCreditAccount = new CreditAccount(sber.getCreditLimit());
        var KostyaIncomeAccount = new DepositAccount(sber.getFirstPercentage(),
                sber.getSecondPercentage(),
                sber.getThirdPercentage(), 10);


        Kostya.addStandartAcoount(KostyaDebitAccount)
                .addCreditAccount(KostyaCreditAccount)
                .addIncomeAccount(KostyaIncomeAccount);

        sber.addUser(Ilya).addUser(Kostya);


        assertInstanceOf(OperationResultType.Success.class, sber.addMoney(Ilya.getUserID(), IlyaDebitAccount.getIdentifier(), 1000));

        assertInstanceOf(OperationResultType.Success.class, sber.substractMoney(Ilya.getUserID(), IlyaDebitAccount.getIdentifier(), 500));

        assertInstanceOf(OperationResultType.Success.class, sber.substractMoney(Ilya.getUserID(), IlyaDebitAccount.getIdentifier(), 500));

        assertInstanceOf(OperationResultType.Unsuccess.class, sber.substractMoney(Ilya.getUserID(), IlyaDebitAccount.getIdentifier(), 500));

        assertInstanceOf(OperationResultType.Success.class, sber.addMoney(Ilya.getUserID(), IlyaDebitAccount.getIdentifier(), 1000));

        assertInstanceOf(OperationResultType.Success.class, sber.transferMoney(Ilya.getUserID(), IlyaDebitAccount.getIdentifier(), Kostya.getUserID(), KostyaDebitAccount.getIdentifier(), 1000));

        assertInstanceOf(OperationResultType.Success.class, sber.cancelTransaction(Ilya.getUserID(), IlyaDebitAccount.getIdentifier()));

        assertInstanceOf(OperationResultType.Unsuccess.class, sber.transferMoney(Kostya.getUserID(), KostyaDebitAccount.getIdentifier(), Ilya.getUserID(), IlyaDebitAccount.getIdentifier(), 1000));


    }


    @Test
    public void depositTests() {
        User Ilya = userBuilder.withName("Ilya")
                .withSurname("Oreshin")
                .withAddress("Vyazma")
                .withPassportData("100500")
                .build();

        var incomeAccount = new DepositAccount(sber.getFirstPercentage(),
                sber.getSecondPercentage(),
                sber.getThirdPercentage(), 10);
        incomeAccount.setCurrentDate(11);

        Ilya.addIncomeAccount(incomeAccount);

        sber.addUser(Ilya);

        sber.addMoney(Ilya.getUserID(), incomeAccount.getIdentifier(), 100_000);

        var TARDIS = new TARDIS(Ilya);
        TARDIS.SkipMonth();

    }


    @Test
    public void TransferBetweenBankTest() {

        User Ilya = userBuilder.withName("Ilya")
                .withSurname("Oreshin")
                .withAddress("Vyazma")
                .withPassportData("100500")
                .build();

        var IlyaDebitAccount = new DebitAccount();

        User Kostya = userBuilder.withName("K")
                .withSurname("T")
                .withAddress("Pushkin")
                .withPassportData("88005553535")
                .build();

        CentralBank ElviraNabuylina = new CentralBank();

        var KostyaDebitAccount = new DebitAccount();

        ElviraNabuylina.addBank(sber).addBank(tinkoff);


        Ilya.addStandartAcoount(IlyaDebitAccount);
        Kostya.addStandartAcoount(KostyaDebitAccount);


        sber.addUser(Ilya);

        tinkoff.addUser(Kostya);

        sber.addMoney(Ilya.getUserID(), IlyaDebitAccount.getIdentifier(), 1000);


        assertInstanceOf(OperationResultType.Success.class, ElviraNabuylina.transferBetweenBank(sber.getBankId(), Ilya.getUserID(), IlyaDebitAccount.getIdentifier(),
                tinkoff.getBankId(), Kostya.getUserID(), KostyaDebitAccount.getIdentifier(),
                1000));


        assertInstanceOf(OperationResultType.Success.class, sber.cancelTransaction(Ilya.getUserID(), IlyaDebitAccount.getIdentifier()));

    }
}