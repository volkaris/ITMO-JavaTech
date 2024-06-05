package Russland.Oreshin;

import BanksStuff.BankAccount.Accounts.CommisionAccounts.CreditAccount;
import BanksStuff.BankAccount.Accounts.CommisionAccounts.ICommissionAccount;
import BanksStuff.BankAccount.Accounts.IBankAccount;
import BanksStuff.Banks.Bank;


import BanksStuff.Users.User;

import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {


        Hashtable<String, Integer> hashtable = new Hashtable<>();

        // Adding elements to the hashtable
        hashtable.put("B", 2);
        hashtable.put("C", 3);

        // Getting values from the hashtable


        // Removing elements from the hashtable
        hashtable.remove("B");



        var sber = Bank.bankBuilder.setFirstPercentage(3).
                setSecondPercentage(3.5).
                setThirdPercentage(4).
                setCommission(100).
                setCreditLimit(1000).
                setMaxAmountForSuspiciousUser(10000).build();

        ICommissionAccount bankAccount1 = new CreditAccount(1000);

        System.out.println(bankAccount1 instanceof IBankAccount);


        IBankAccount bankAccount2 = new CreditAccount(sber.getCreditLimit());

        User first = User.userBuilder
                .withName("abobus")
                .withSurname("impostor").
                withAddress("Vyazma").
                withPassportData("24 02 2022").build();


       /* first.AddMoney(100_000);
        first.ChargePercentageForDay();
        first.SubstractMoney(50_000);
        first.ChargePercentageForMonth();
        *//*Client second=builder
                .WithName("sdas")
                .WithSurname("dsad").
                WithAddress("da").
                WithPassportData("24 02 2022").WithAccount(bankAccount2).Build();
*//*


        sber.AddClient(first);
*/

       /* bankAccount1.AddMoney(100);
        bankAccount1.TransferMoney(bankAccount2, 60);

        bankAccount1.CancelTransaction();


        bankAccount1.AddMoney(200);
        bankAccount1.TransferMoney(bankAccount2, 50);

        bankAccount1.CancelTransaction();

        bankAccount2.AddMoney(100);

        bankAccount2.TransferMoney(bankAccount1, 60);

        bankAccount2.CancelTransaction();

        System.out.println(bankAccount1.GetHistory());
        System.out.println(bankAccount2.GetHistory());
        IBankAccount third = new DebitAccount();

      *//*   Client client= Client.ClientBuilder.builder
                 .WithName()
                 .WithSurname().
                 WithAddress().
                 WithPassportData().
                 Build();*//*

        System.out.println(bankAccount1.GetHistory());*/


    }
}