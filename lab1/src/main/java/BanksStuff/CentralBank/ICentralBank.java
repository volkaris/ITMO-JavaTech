package BanksStuff.CentralBank;

import BanksStuff.Banks.Bank;

public interface ICentralBank {
    public Bank createBank(double firstPercentage,
                           double secondPercentage,
                           double thirdPercentage,
                           double commission,
                           double creditLimit,
                           double maxAmountForSuspiciousUser);

    public void withdrawTheCommission();

    public void chargePercentageForDay();

    public void chargePercentageForMonth();
}
