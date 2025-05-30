package starter.models;

public class TransactionModel {
    private String transactionDate;
    private String transactionAmmount;

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public String getTransactionAmmount() {
        return transactionAmmount;
    }

    public void setTransactionAmmount(String transactionAmmount) {
        this.transactionAmmount = transactionAmmount;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    private String transactionDescription;
}
