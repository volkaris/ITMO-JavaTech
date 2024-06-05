package BanksStuff.BankAccount.OperationResult;

/**
 * Discriminated union for result of operation. Could be Success / Unsuccess
 */
public class OperationResultType {
    public final class Success extends OperationResultType {}
    public final class Unsuccess extends OperationResultType {

        String failReason;

        public Unsuccess(String failReason) {
            this.failReason = failReason;
        }
    }
}
