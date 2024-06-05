package BanksStuff.BankAccount.OperationType;


/**
 * Interface for transactions. All transactions must cancelable
 */
public interface ITransaction {
    public void cancelOperation();
}
