package BanksStuff.ObserverStuff;
import BanksStuff.Banks.Bank;

/**
 * Observing pattern interface
 */
public interface ISubscriber {
    void receiveNotification(Bank bank) ;
}
