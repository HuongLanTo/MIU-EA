package bank.dto;

public class AccountDto {

    private long accountNumber;
    private String customerName;

    public AccountDto(long accountNumber, String customerName) {
        super();
        this.accountNumber = accountNumber;
        this.customerName = customerName;
    }

    public AccountDto() {
        super();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
