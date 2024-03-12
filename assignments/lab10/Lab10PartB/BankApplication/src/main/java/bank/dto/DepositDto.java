package bank.dto;

public class DepositDto {
    private long accountNumber;
    private double amount;

    public DepositDto(long accountNumber, double amount) {
        super();
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public DepositDto() {
        super();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
