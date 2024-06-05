package BanksStuff.CentralBank;


import BanksStuff.BankAccount.OperationResult.OperationResultType;
import BanksStuff.Banks.Bank;
import BanksStuff.Banks.IBank;

import java.util.Hashtable;
import java.util.UUID;

/**
 * Realisation of ICentralBank interface. Provides operation of notifying bank about commisson or percentage accrual
 */
public class CentralBank implements ICentralBank {
    private final Hashtable<UUID, IBank> banks = new Hashtable<>();

    public CentralBank addBank(IBank bank) {
        banks.put(bank.getBankId(), bank);
        return this;
    }


    public OperationResultType transferBetweenBank(UUID senderBankId, UUID senderUserId, UUID senderAccountId,
                                                   UUID receiverBankId, UUID receiverUserId, UUID receiverAccountId,
                                                   double amount) {

        var senderBank = findBank(senderBankId);
        var receiverBank = findBank(receiverBankId);

        if (senderBank == null || receiverBank == null) {
            return new OperationResultType().new Unsuccess("Bank wasn't found");
        }

        var senderUser = senderBank.findUser(senderUserId);
        var receiverUser = receiverBank.findUser(receiverUserId);


        if (senderUser == null || receiverUser == null) {
            return new OperationResultType().new Unsuccess("User wasn't found");
        }

        return senderUser.transferMoney(senderAccountId, receiverUser.findAccount(receiverAccountId), amount);


    }

    public Bank createBank(double firstPercentage,
                           double secondPercentage,
                           double thirdPercentage,
                           double commission,
                           double creditLimit,
                           double maxAmountForSuspiciousUser) {

        return Bank.bankBuilder.setFirstPercentage(firstPercentage).
                setSecondPercentage(secondPercentage).
                setThirdPercentage(thirdPercentage).
                setCommission(commission).
                setCreditLimit(creditLimit).
                setMaxAmountForSuspiciousUser(maxAmountForSuspiciousUser).build();
    }

    public void withdrawTheCommission() {
        for (var bank : banks.values()) {
            bank.withdrawTheCommission();
        }
    }

    public void chargePercentageForDay() {
        for (var bank : banks.values()) {

            bank.chargePercentageForDay();
        }
    }

    public void chargePercentageForMonth() {
        for (var bank : banks.values()) {
            bank.chargePercentageForMonth();
        }
    }

    private IBank findBank(UUID bankId) {
        if (banks.containsKey(bankId)) {
            return banks.get(bankId);

        }
        return null;
    }
}
