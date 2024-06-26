package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Scanner implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scannerCode;

    @OneToOne
    Location location;
    
    @OneToOne
    Event event;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

//    public Integer getScannerCode() {
//        return scannerCode;
//    }
//
//    public void setScannerCode(Integer scannerCode) {
//        this.scannerCode = scannerCode;
//    }
//
//    public Location getLocation() {
//        return location;
//    }
//
//    public void setLocation(Location location) {
//        this.location = location;
//    }
//
//    public Event getEvent() {
//        return event;
//    }
//
//    public void setEvent(Event event) {
//        this.event = event;
//    }

//    public AccountType getAccountType() { return accountType; }
//
//    public void setAccountType(AccountType accountType) {
//        this.accountType = accountType;
//    }
}
