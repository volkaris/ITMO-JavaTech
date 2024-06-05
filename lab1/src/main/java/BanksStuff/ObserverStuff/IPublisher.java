package BanksStuff.ObserverStuff;

/**
 * Observing pattern interface
 */
public interface IPublisher {
    void sendNotification();
    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscriber);
}
